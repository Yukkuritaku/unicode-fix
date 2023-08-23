package com.yukkuritaku.unicodefix.asm;

import com.yukkuritaku.unicodefix.asm.utils.TransformerClass;
import com.yukkuritaku.unicodefix.asm.utils.TransformerField;
import com.yukkuritaku.unicodefix.asm.utils.TransformerMethod;
import com.yukkuritaku.unicodefix.tweaker.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

public class LocaleTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return new String[] {TransformerClass.Locale.getTransformerName()};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods){
            //loadLocaleDataFilesを探す
            if (TransformerMethod.loadLocaleDataFiles.matches(methodNode)){
                Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()){
                    AbstractInsnNode node = iterator.next();
                    // remove method: checkUnicode
                    if (node instanceof MethodInsnNode && node.getOpcode() == Opcodes.INVOKESPECIAL){
                        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
                        if (TransformerMethod.checkUnicode.matches(methodInsnNode)){
                            /*methodNode.instructions.insertBefore(node,
                                    new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    "com/yukkuritaku/unicodefix/asm/hooks/LocaleHook",
                                            "checkUnicode",
                                            "()V", false));*/
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
            //Find method: isUnicode
            if (TransformerMethod.isUnicode.matches(methodNode)){
                Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()){
                    AbstractInsnNode node = iterator.next();
                    //find return field: unicode
                    //replace with: Minecraft.getMinecraft().gameSettings.forceUnicodeFont
                    if (node instanceof FieldInsnNode && node.getOpcode() == Opcodes.GETFIELD){
                        FieldInsnNode fieldInsnNode = (FieldInsnNode) node;
                        if (TransformerField.unicode.matches(fieldInsnNode)){
                            methodNode.instructions.insertBefore(node, replaceIsUnicode());
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
        }
    }

    private InsnList replaceIsUnicode(){
        InsnList list = new InsnList();
        //Minecraft.getMinecraft().
        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                TransformerClass.Minecraft.getNameRaw(),
                TransformerMethod.getMinecraft.getMethodName(),
                TransformerMethod.getMinecraft.getDescriptor(),
                false));
        //gameSettings.
        list.add(new FieldInsnNode(Opcodes.GETFIELD,
                TransformerClass.Minecraft.getNameRaw(),
                TransformerField.gameSettings.getFieldName(),
                TransformerField.gameSettings.getDescriptor()));
        //forceUnicodeFont
        list.add(new FieldInsnNode(Opcodes.GETFIELD,
                TransformerClass.GameSettings.getNameRaw(),
                TransformerField.forceUnicodeFont.getFieldName(),
                TransformerField.forceUnicodeFont.getDescriptor()));
        return list;
    }
}
