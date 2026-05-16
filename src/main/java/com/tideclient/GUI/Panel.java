package com.tideclient.GUI;

import com.tideclient.GUI.comp.Color;
import com.tideclient.Module.Categories;
import com.tideclient.Module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

import static com.tideclient.util.TextRenderUtil.TextRender;

public class Panel {
    private final Categories cat;
    private final int x, y;
    private final List<ModuleButton> buttons = new ArrayList<>();

    public Panel(Categories cat, int x, int y) {
        this.cat = cat;
        this.x = x;
        this.y = y;
        for (var m : ModuleManager.getModuleByCategory(cat)) {
            buttons.add(new ModuleButton(m));
        }
    }

    public void render(DrawContext ctx, int mx, int my) {
        int headerHeight = 16;
        int width = 100;

        ctx.fill(x, y, x + width, y + headerHeight, Color.catColor);
        TextRender(ctx, cat.name(), x + 4, y + 4, 0xFFFFFFFF);
        ctx.drawBorder(x, y, width, headerHeight, Color.outline);

        int cy = y + headerHeight;
        for (var b : buttons) {
            b.render(ctx, x, cy, mx, my);
            cy += b.fullHeight();
        }
    }

    public boolean click(double mx, double my, int b) {
        if (mx < x || mx >= x+100 || my < y || my >= y+16) {
            int cy = y + 16;
            for (var btn : buttons) {
                if (btn.click(mx, my, b, x, cy)) return true;
                cy += btn.fullHeight();
            }
        }
        return false;
    }

    public void release(double mx, double my, int b) {
        int cy = y + 16;
        for (var btn : buttons) {
            btn.release(mx, my, b, x, cy);
            cy += btn.fullHeight();
        }
    }
}