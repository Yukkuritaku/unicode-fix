package com.yukkuritaku.unicodefix.asm.utils;

import com.yukkuritaku.unicodefix.tweaker.UnicodeFixTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldInsnNode;

public enum TransformerField {
    //Locale
    unicode("unicode", "field_135029_d", "d", "Z"),
    //LanguageManager
    CURRENT_LOCALE("CURRENT_LOCALE", "field_135049_a", "a", TransformerClass.Locale.getDescriptor()),
    //Minecraft
    gameSettings("gameSettings",
            "field_71474_y",
            "t",
            TransformerClass.GameSettings.getDescriptor()),
    //GameSettings
    forceUnicodeFont("forceUnicodeFont", "field_151455_aw", "aK", "Z"),

    ;

    private final String fieldName;
    private final String descriptor;

    TransformerField(String mcpField, String seargeField, String notchField, String descriptor){
        this.descriptor = descriptor;
        if (UnicodeFixTransformer.deobfuscated){
            this.fieldName = mcpField;
        }else {
            if (UnicodeFixTransformer.notchMappings){
                this.fieldName = notchField;
            }else {
                this.fieldName = seargeField;
            }
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public FieldInsnNode putField(TransformerClass transformerClass){
        return new FieldInsnNode(Opcodes.PUTFIELD, transformerClass.getNameRaw(), fieldName, descriptor);
    }

    public FieldInsnNode getField(TransformerClass transformerClass){
        return new FieldInsnNode(Opcodes.GETFIELD, transformerClass.getNameRaw(), fieldName, descriptor);
    }

    public boolean matches(FieldInsnNode node){
        return this.fieldName.equals(node.name) && this.descriptor.equals(node.desc);
    }
}
