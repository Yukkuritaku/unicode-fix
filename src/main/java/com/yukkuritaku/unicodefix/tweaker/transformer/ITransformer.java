package com.yukkuritaku.unicodefix.tweaker.transformer;

import org.objectweb.asm.tree.ClassNode;

public interface ITransformer {

    String[] getClassName();
    void transform(ClassNode classNode, String name);

    default boolean nameMatches(String method, String... names){
        for (String name : names) if(method.equals(name)) return true;
        return false;
    }
}
