package com.yukkuritaku.unicodefix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = UnicodeFixMod.MOD_ID,
        name = UnicodeFixMod.NAME,
        version = UnicodeFixMod.VERSION,
        clientSideOnly = true,
        acceptedMinecraftVersions = "[1.12.2]"
)
public class UnicodeFixMod {

    public static final String MOD_ID = "unicodefix";
    public static final String NAME = "Unicode Fix";
    public static final String VERSION = "1.0";

    private static Logger logger = LogManager.getLogger();

    public static Logger getLogger() {
        return logger;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }
}
