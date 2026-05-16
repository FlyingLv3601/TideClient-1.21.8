package com.tideclient.util;

import com.tideclient.GUI.comp.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class TextRenderUtil {
    public static void TextRender(DrawContext ctx, String text, int x, int y){
        MinecraftClient client = MinecraftClient.getInstance();
        ctx.drawText(client.textRenderer, text, x, y, Color.activeBtn, false);
    }

    public static void TextRender(DrawContext ctx, String text, int x, int y, int color){
        MinecraftClient client = MinecraftClient.getInstance();
        ctx.drawText(client.textRenderer, text, x, y, color, false);
    }

}
