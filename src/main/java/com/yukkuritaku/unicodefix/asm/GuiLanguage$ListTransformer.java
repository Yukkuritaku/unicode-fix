package com.yukkuritaku.unicodefix.asm;

import com.yukkuritaku.unicodefix.asm.utils.TransformerClass;
import com.yukkuritaku.unicodefix.asm.utils.TransformerMethod;
import com.yukkuritaku.unicodefix.tweaker.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

public class GuiLanguage$ListTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return new String[]{TransformerClass.GuiLanguage$List.getTransformerName()};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods){
            if (TransformerMethod.elementClicked.matches(methodNode)){
                Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()){
                    AbstractInsnNode node = iterator.next();
                    if (node instanceof MethodInsnNode && node.getOpcode() == Opcodes.INVOKEVIRTUAL){
                        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
                        if (TransformerMethod.isCurrentLocaleUnicode.matches(methodInsnNode)){
                            // find: GuiLanguage.this.fontRendererObj.setUnicodeFlag(GuiLanguage.this.languageManager.isCurrentLocaleUnicode() || GuiLanguage.this.game_settings_3.forceUnicodeFont);
                            // replace to: GuiLanguage.this.mc.fontRendererObj.setUnicodeFlag(this.forceUnicodeFont);
                            methodNode.instructions.remove(methodInsnNode.getPrevious().getPrevious().getPrevious());
                            methodNode.instructions.remove(methodInsnNode.getPrevious().getPrevious());
                            methodNode.instructions.remove(methodInsnNode.getPrevious());
                            methodNode.instructions.remove(methodInsnNode.getNext());
                            methodNode.instructions.remove(methodInsnNode);
                            break;
                        }
                    }
                }
            }
        }
    }
}
