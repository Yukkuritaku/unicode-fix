package com.yukkuritaku.unicodefix.asm;

import com.yukkuritaku.unicodefix.asm.utils.TransformerClass;
import com.yukkuritaku.unicodefix.asm.utils.TransformerField;
import com.yukkuritaku.unicodefix.asm.utils.TransformerMethod;
import com.yukkuritaku.unicodefix.tweaker.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

public class LanguageManagerTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{TransformerClass.LanguageManager.getTransformerName()};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods){
            //find method: isCurrentLocaleUnicode

            if (TransformerMethod.isCurrentLocaleUnicode.matches(methodNode)){
                Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()){
                    AbstractInsnNode node = iterator.next();
                    //find return: currentLocale.isUnicode()
                    //replace to: Minecraft.getMinecraft().gameSettings.forceUnicodeFont
                    if (node instanceof FieldInsnNode){
                        FieldInsnNode fieldInsnNode = (FieldInsnNode) node;
                        if (TransformerField.CURRENT_LOCALE.matches(fieldInsnNode)){
                            iterator.remove();
                        }
                    }
                    if (node instanceof MethodInsnNode) {
                        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
                        if (TransformerMethod.isUnicode.matches(methodInsnNode)) {
                            methodNode.instructions.insertBefore(node, replaceIsUnicode());
                            iterator.remove();
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
