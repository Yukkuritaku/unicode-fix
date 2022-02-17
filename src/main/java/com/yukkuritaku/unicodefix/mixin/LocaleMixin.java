package com.yukkuritaku.unicodefix.mixin;

import net.minecraft.client.resources.Locale;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Locale.class)
public class LocaleMixin {

    @Shadow private boolean field_135029_d;

    @Redirect(method = "checkUnicode",
            at = @At(value = "FIELD",
            target = "Lnet/minecraft/client/resources/Locale;field_135029_d:Z",
            ordinal = 1))
    private void injectUnicode(Locale instance, boolean value){
        this.field_135029_d = false;
    }
}
