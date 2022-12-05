package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameSettings.class)
public class GameSettingsMixin {

    @Shadow public boolean forceUnicodeFont;

    @Redirect(method = "setOptionValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;setUnicodeFlag(Z)V"))
    private void onSetOptionValue(FontRenderer instance, boolean unicodeFlagIn){
        instance.setUnicodeFlag(this.forceUnicodeFont);
    }
}
