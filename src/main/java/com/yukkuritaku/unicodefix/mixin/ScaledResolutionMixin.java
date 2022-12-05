package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ScaledResolution.class)
public class ScaledResolutionMixin {

    @Shadow private int scaleFactor;

    @Redirect(method = "<init>",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/gui/ScaledResolution;scaleFactor:I",
                    ordinal = 6))
    private int onInit(ScaledResolution instance){
        return 0;
    }

    @Redirect(method = "<init>",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/gui/ScaledResolution;scaleFactor:I",
                    ordinal = 7
            ))
    private int onSubScaleFactor(ScaledResolution instance){

        return ++this.scaleFactor;
    }

}
