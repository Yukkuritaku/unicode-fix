package com.yukkuritaku.unicodefix.asm.utils;

import com.yukkuritaku.unicodefix.tweaker.UnicodeFixTransformer;

public enum TransformerClass {
    Minecraft("net/minecraft/client/Minecraft", "ave"),
    ScaledResolution("net/minecraft/client/gui/ScaledResolution", "avr"),
    Locale("net/minecraft/client/resources/Locale", "bnt"),
    GameSettings("net/minecraft/client/settings/GameSettings", "avh"),
    GameSettings$Options("net/minecraft/client/settings/GameSettings$Options", "avh$a"),
    FontRenderer("net/minecraft/client/gui/FontRenderer", "avn"),
    LanguageManager("net/minecraft/client/resources/LanguageManager", "bns"),
    GuiLanguage$List("net/minecraft/client/gui/GuiLanguage$List", "axl$a"),
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
