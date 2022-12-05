package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.LanguageManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LanguageManager.class)
public class LanguageManagerMixin {

    @Inject(method = "isCurrentLocaleUnicode", at = @At("RETURN"), cancellable = true)
    public void onReturnIsCurrentLocaleUnicode(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(Minecraft.getMinecraft().gameSettings.forceUnicodeFont);
    }
}
