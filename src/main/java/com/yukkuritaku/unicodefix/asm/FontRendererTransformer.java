package com.yukkuritaku.unicodefix.asm;

import com.yukkuritaku.unicodefix.asm.utils.TransformerClass;
import com.yukkuritaku.unicodefix.asm.utils.TransformerMethod;
import com.yukkuritaku.unicodefix.tweaker.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

public class FontRendererTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{TransformerClass.FontRenderer.getTransformerName() };
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods){
            if (TransformerMethod.renderStringAtPos.matches(methodNode)){
                Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()){
                    AbstractInsnNode node = iterator.next();
                    if (node instanceof MethodInsnNode &&
                            node.getOpcode() == Opcodes.INVOKEVIRTUAL &&
                    ((MethodInsnNode) node).owner.equals("java/lang/String") &&
                    ((MethodInsnNode) node).name.equals("toLowerCase") &&
                    ((MethodInsnNode) node).desc.equals("()Ljava/lang/String;")){
                        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
                        methodNode.instructions.insertBefore(methodInsnNode,
                                new FieldInsnNode(Opcodes.GETSTATIC,
                                        "java/util/Locale",
                                        "ENGLISH",
                                        "Ljava/util/Locale;"));
                        methodNode.instructions.set(methodInsnNode,
                                new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                        "java/lang/String",
                                        "toLowerCase",
                                        "(Ljava/util/Locale;)Ljava/lang/String;",
                                        false));
                        break;
                    }
                }
            }
        }
    }
}
