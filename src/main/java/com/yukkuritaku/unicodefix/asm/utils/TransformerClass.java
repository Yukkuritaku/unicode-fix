package com.yukkuritaku.unicodefix.asm.utils;

import com.yukkuritaku.unicodefix.tweaker.UnicodeFixTransformer;

public enum TransformerClass {
    Minecraft("net/minecraft/client/Minecraft", "bib"),
    ScaledResolution("net/minecraft/client/gui/ScaledResolution", "bit"),
    Locale("net/minecraft/client/resources/Locale", "cfb"),
    GameSettings("net/minecraft/client/settings/GameSettings", "bid"),
    GameSettings$Options("net/minecraft/client/settings/GameSettings$Options", "bid$a"),
    FontRenderer("net/minecraft/client/gui/FontRenderer", "bip"),
    LanguageManager("net/minecraft/client/resources/LanguageManager", "cfa"),
    GuiLanguage$List("net/minecraft/client/gui/GuiLanguage$List", "blc$a"),
    ;
    private final String className;
    private final String seargeClass;

    TransformerClass(String seargeClass, String notchClass) {
        this.seargeClass = seargeClass;
        if (UnicodeFixTransformer.deobfuscated || !UnicodeFixTransformer.notchMappings){
            this.className = seargeClass;
        }else {
            this.className = notchClass;
        }
    }

    public String getDescriptor(){
        return "L" + this.className + ";";
    }

    public String getNameRaw(){
        return this.className;
    }

    public String getName(){
        return "L" + this.className + ";";
    }

    public String getTransformerName(){
        return this.seargeClass.replaceAll("/", ".");
    }
}
