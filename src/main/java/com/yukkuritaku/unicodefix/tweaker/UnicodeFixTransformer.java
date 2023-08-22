package com.yukkuritaku.unicodefix.tweaker;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.yukkuritaku.unicodefix.asm.*;
import com.yukkuritaku.unicodefix.tweaker.transformer.ITransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

public class UnicodeFixTransformer implements IClassTransformer {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final boolean deobfuscated = (boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
    public static final boolean notchMappings = !deobfuscated;
    private final Multimap<String, ITransformer> transformerMap = ArrayListMultimap.create();

    public UnicodeFixTransformer(){
        registerTransformer(new LocaleTransformer());
        registerTransformer(new GameSettingsTransformer());
        registerTransformer(new LanguageManagerTransformer());
        registerTransformer(new GuiLanguage$ListTransformer());
        registerTransformer(new ScaledResolutionTransformer());
    }

    private void registerTransformer(ITransformer transformer){
        for (String cls : transformer.getClassName()){
            transformerMap.put(cls, transformer);
        }
    }

    private void outputBytecode(String transformedName, ClassWriter writer){
        try{
            File bytecodeDir = new File("unicode-fix-bytecode");
            if (!bytecodeDir.exists()) return;
            File bytecodeOutput = new File(bytecodeDir, transformedName + ".class");
            if (!bytecodeOutput.exists()) bytecodeOutput.createNewFile();
            try(FileOutputStream os = new FileOutputStream(bytecodeOutput)){
                os.write(writer.toByteArray());
            }
        } catch (Exception e){
            LOGGER.error("An error occurred writing bytecode of transformed class \"{}\" to file", transformedName, e);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null){
            return null;
        }
        Collection<ITransformer> transformers = transformerMap.get(transformedName);
        if (transformers.isEmpty()){
            return basicClass;
        }
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, ClassReader.EXPAND_FRAMES);
        MutableInt classWriterFlags = new MutableInt(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        transformers.forEach(transformer -> {
            LOGGER.info("Applying transformer {} on {}", transformer.getClass().getName(), transformedName);
            transformer.transform(node, transformedName);
        });
        ClassWriter writer = new ClassWriter(classWriterFlags.getValue());
        try{
            node.accept(writer);
        }catch (Exception e){
            LOGGER.error("An exception occurred while transforming {}", transformedName);
            e.printStackTrace();
            outputBytecode(transformedName, writer);
            return basicClass;
        }
        outputBytecode(transformedName, writer);
        return writer.toByteArray();
    }
}
