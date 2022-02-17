package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Redirect(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;setUnicodeFlag(Z)V"))
    private void changeUnicode(FontRenderer instance, boolean unicodeFlagIn){
        instance.setUnicodeFlag(false);
    }
}
