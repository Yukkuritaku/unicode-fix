package com.yukkuritaku.unicodefix.asm;

import com.yukkuritaku.unicodefix.asm.utils.TransformerClass;
import com.yukkuritaku.unicodefix.asm.utils.TransformerMethod;
import com.yukkuritaku.unicodefix.tweaker.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

public class ScaledResolutionTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{TransformerClass.ScaledResolution.getTransformerName()};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods){
            if (TransformerMethod.init_ScaledResolution.matches(methodNode)){
                Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()){
                    AbstractInsnNode node = iterator.next();
                    if (node instanceof InsnNode && node.getOpcode() == Opcodes.ISUB){
                        InsnNode insnNode = (InsnNode) node;
                        methodNode.instructions.insertBefore(insnNode, new InsnNode(Opcodes.IADD));
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }
}
