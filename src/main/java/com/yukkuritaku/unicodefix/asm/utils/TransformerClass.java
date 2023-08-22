package com.yukkuritaku.unicodefix.asm.utils;

import com.yukkuritaku.unicodefix.tweaker.UnicodeFixTransformer;

public enum TransformerClass {
    Minecraft("net/minecraft/client/Minecraft", "bao"),
    ScaledResolution("net/minecraft/client/gui/ScaledResolution", "bca"),
    Locale("net/minecraft/client/resources/Locale", "brs"),
    GameSettings("net/minecraft/client/settings/GameSettings", "bbj"),
    GameSettings$Options("net/minecraft/client/settings/GameSettings$Options", "bbm"),
    FontRenderer("net/minecraft/client/gui/FontRenderer", "bbu"),
    LanguageManager("net/minecraft/client/resources/LanguageManager", "bbr"),
    GuiLanguage$List("net/minecraft/client/gui/GuiLanguage$List", "bdk"),
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
