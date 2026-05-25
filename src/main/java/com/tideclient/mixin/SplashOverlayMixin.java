package com.tideclient.mixin;

import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
@Mixin(SplashOverlay.class)
public abstract class SplashOverlayMixin {

    @Mutable
    @Shadow
    @Final
    private static int MOJANG_RED;

/*    @Mutable
    @Shadow
    @Final
    public static Identifier LOGO;*/

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onStaticInit(CallbackInfo ci) {
        MOJANG_RED = ColorHelper.getArgb(255, 120, 120, 120);
        //LOGO = Identifier.of("tideclient", "logo.png");
    }


}