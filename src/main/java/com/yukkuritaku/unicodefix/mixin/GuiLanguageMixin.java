package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiLanguage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiLanguage.List.class)
public abstract class GuiLanguageMixin {

    @Redirect(method = "elementClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;setUnicodeFlag(Z)V"))
    private void onElementClicked(FontRenderer instance, boolean unicodeFlagIn){
        instance.setUnicodeFlag(((GuiLanguageAccessor) this).getGameSettings().forceUnicodeFont);
    }
}
