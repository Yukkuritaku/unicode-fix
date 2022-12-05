package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiLanguage.class)
public interface GuiLanguageAccessor {

    @Accessor("game_settings_3") GameSettings getGameSettings();
}
