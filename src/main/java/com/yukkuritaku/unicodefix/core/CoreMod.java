package com.yukkuritaku.unicodefix.core;

import com.yukkuritaku.unicodefix.UnicodeFixMod;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.Name(UnicodeFixMod.MOD_ID)
public class CoreMod implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        UnicodeFixMod.getLogger().info("Initialize Mixin!");
        MixinBootstrap.init();
        Mixins.addConfiguration(UnicodeFixMod.MOD_ID + ".mixins.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        UnicodeFixMod.getLogger().info("Mixin Initialize Done!");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
