package com.yukkuritaku.unicodefix.asm.utils;

import com.yukkuritaku.unicodefix.tweaker.UnicodeFixTransformer;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public enum TransformerMethod {
    /**
     * Constructor
     */
    init("<init>", "<init>", "<init>", "()V"),

    //Minecraft
    getMinecraft("getMinecraft", "func_71410_x", "A",
            "()Lnet/minecraft/client/Minecraft;", "()Lave;"),
    //ScaledResolution
    init_ScaledResolution("<init>", "<init>", "<init>",
            "(" + TransformerClass.Minecraft.getDescriptor() + ")V"),

    //Locale
    checkUnicode("checkUnicode", "func_135024_b", "b", "()V"),
    loadLocaleDataFiles("loadLocaleDataFiles",
            "func_135022_a",
            "a",
            "(Lnet/minecraft/client/resources/IResourceManager;Ljava/util/List;)V",
            "(Lbni;Ljava/util/List;)V"),
    isUnicode("isUnicode", "func_135025_a", "a", "()Z"),

    //LanguageManager
    isCurrentLocaleUnicode("isCurrentLocaleUnicode", "func_135042_a", "a", "()Z"),

    //GameSettings
    setOptionValue("setOptionValue",
            "func_74306_a",
            "a",
            "(" +TransformerClass.GameSettings$Options.getDescriptor() + "I)V"),

    //FontRenderer

    //GuiLanguage$List
    elementClicked("elementClicked", "func_148144_a", "a", "(IZII)V"),
    ;

    private final String methodName;
    private final String descriptor;
    private final String[] throwableExceptions;


    TransformerMethod(String mcpMethod,
                      String seargeMethod,
                      String notchMethod,
                      String seargeDescriptor){
        this(mcpMethod, seargeMethod, notchMethod, seargeDescriptor, seargeDescriptor, new String[]{});
    }

    TransformerMethod(String mcpMethod,
                      String seargeMethod,
                      String notchMethod,
                      String seargeDescriptor,
                      String notchDescriptor){
        this(mcpMethod, seargeMethod, notchMethod, seargeDescriptor, notchDescriptor, new String[]{});
    }

    TransformerMethod(String mcpMethod,
                      String seargeMethod,
                      String notchMethod,
                      String seargeDescriptor,
                      @Nullable String... throwableExceptions){
        this(mcpMethod, seargeMethod, notchMethod, seargeDescriptor, seargeDescriptor, throwableExceptions);
    }

    TransformerMethod(String mcpMethod,
                      String seargeMethod,
                      String notchMethod,
                      String seargeDescriptor,
                      String notchDescriptor,
                      @Nullable String... throwableExceptions){
        if (UnicodeFixTransformer.deobfuscated){
            this.methodName = mcpMethod;
            this.descriptor = seargeDescriptor;
        }else {
            if (UnicodeFixTransformer.notchMappings){
                this.methodName = notchMethod;
                this.descriptor = notchDescriptor;
            }else {
                this.methodName = seargeMethod;
                this.descriptor = seargeDescriptor;
            }
        }
        this.throwableExceptions = throwableExceptions;

    }

    public String getMethodName() {
        return methodName;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public MethodNode createMethodNode(){
        return new MethodNode(Opcodes.ACC_PUBLIC, methodName, descriptor, null, throwableExceptions);
    }

    public boolean matches(MethodInsnNode node){
        return this.methodName.equals(node.name) && this.descriptor.equals(node.desc);
    }

    public boolean matches(MethodNode node){
        return this.methodName.equals(node.name) && (this.descriptor.equals(node.desc) || this == init);
    }
}
