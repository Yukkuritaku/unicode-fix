package com.yukkuritaku.unicodefix;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;

import java.nio.charset.StandardCharsets;

@Mod(modid = UnicodeFixMod.MOD_ID,
        name = UnicodeFixMod.NAME,
        version = UnicodeFixMod.VERSION,
        acceptedMinecraftVersions = "[1.7.10]")
public class UnicodeFixMod {

    public static final String MOD_ID = "unicodefix";
    public static final String NAME = "Unicode Fix";
    public static final String VERSION = "1.3";

    @Mod.EventHandler
    private void onPost(final FMLPostInitializationEvent event){
        LogManager.getLogger().warn("File Encoding: {}", SystemUtils.FILE_ENCODING);
        LogManager.getLogger().warn("Charsets: {}", Charsets.UTF_8);
        LogManager.getLogger().warn("StandardCharsets: {}", StandardCharsets.UTF_8);
    }
}
