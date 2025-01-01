package com.yukkuritaku.unicodefix.asm.modfixes.custommainmenu;

import com.yukkuritaku.unicodefix.asm.utils.TransformerClass;
import com.yukkuritaku.unicodefix.asm.utils.TransformerMethod;
import com.yukkuritaku.unicodefix.tweaker.transformer.ITransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

public class TextResourceLocationTransformer implements ITransformer {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String[] getClassName() {
        return new String[]{TransformerClass.TextResourceLocation.getTransformerName()};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode node : classNode.methods){
            if (TransformerMethod.get.matches(node)){
                Iterator<AbstractInsnNode> iterator = node.instructions.iterator();
                while (iterator.hasNext()){
                    AbstractInsnNode insnNode = iterator.next();
                    if (insnNode instanceof MethodInsnNode && insnNode.getOpcode() == Opcodes.INVOKESPECIAL &&
                    ((MethodInsnNode) insnNode).owner.equals("java/io/InputStreamReader") &&
                    ((MethodInsnNode) insnNode).name.equals("<init>") &&
                    ((MethodInsnNode) insnNode).desc.equals("(Ljava/io/InputStream;)V")){
                        LOGGER.info("GTNH Custom Main Menu (https://github.com/GTNewHorizons/Custom-Main-Menu) Detected, fix the splash text bugs, transform!");
                        LOGGER.info("If you're interesting for the this bug, see: https://github.com/Yukkuritaku/unicode-fix/issues/2");
                        node.instructions.insertBefore(insnNode, new FieldInsnNode(Opcodes.GETSTATIC,
                                "java/nio/charset/StandardCharsets",
                                "UTF_8",
                                "Ljava/nio/charset/Charset;"));
                        node.instructions.set(insnNode, new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                "java/io/InputStreamReader",
                                "<init>",
                                "(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V",
                                false));
                    }
                }
            }
        }
    }
}
