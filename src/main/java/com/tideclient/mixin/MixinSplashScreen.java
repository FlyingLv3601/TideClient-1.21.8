package com.tideclient.mixin;

import com.tideclient.GUI.comp.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public abstract class MixinSplashScreen {
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        int w = context.getScaledWindowWidth();
        int h = context.getScaledWindowHeight();

        context.fill(0, 0, w, h, Color.catColor);

        context.getMatrices().pushMatrix();
        context.getMatrices().translate(w / 2f, h / 2f);

        context.getMatrices().scale(2.0f, 2.0f);

        Text text = Text.literal("Tide Client");
        int textWidth = client.textRenderer.getWidth(text);
        int textHeight = client.textRenderer.fontHeight;

        context.getMatrices().popMatrix();
    }
}