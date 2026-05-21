package com.tideclient.util;

import com.tideclient.GUI.comp.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class TextRenderUtil {
    private static MinecraftClient mc = MinecraftClient.getInstance();
    public static void TextRender(DrawContext ctx, String text, int x, int y){
        ctx.drawText(mc.textRenderer, text, x, y, Color.activeBtn, false);
    }

    public static void TextRender(DrawContext ctx, String text, int x, int y, int color){
        ctx.drawText(mc.textRenderer, text, x, y, color, false);
    }

}