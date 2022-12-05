package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Locale;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Locale.class)
public class LocaleMixin {

    @Redirect(method = "loadLocaleDataFiles", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/Locale;checkUnicode()V"))
    private void onLoadLocaleDataFiles(Locale instance){}

    @Inject(method = "isUnicode", at = @At("RETURN"), cancellable = true)
    private void onReturnIsUnicode(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(Minecraft.getMinecraft().gameSettings.forceUnicodeFont);
    }

}
