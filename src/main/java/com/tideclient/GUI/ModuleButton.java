package com.tideclient.GUI;

import com.tideclient.GUI.comp.Color;
import com.tideclient.Module.Module;
import com.tideclient.Module.Settings.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.util.List;

import static com.tideclient.util.TextRenderUtil.TextRender;

public class ModuleButton {

    private final Module mod;

    private final int buttonW = 100;
    private final int buttonBaseH = 14;
    private boolean expanded = false;

    public ModuleButton(Module mod) {
        this.mod = mod;
    }

    public void render(DrawContext ctx, int bx, int by, int mx, int my) {
        int color = mod.getStatus() ? Color.activeBtn : Color.noneActiveBtn;

        ctx.fill(bx, by, bx + buttonW, by + buttonBaseH, color);

        TextRender(ctx, mod.getName(), bx + 4, by + 3, 0xFFFFFFFF);

        if (expanded) {
            int yOffset = by + buttonBaseH;

            for (Setting setting : mod.getSettings()) {
                if (!setting.isVisible()) continue;

                setting.render(ctx, bx, yOffset, mx, my);
                yOffset += setting.getHeight();
            }
        }
    }

    public boolean click(double mx, double my, int b, int bx, int by) {
        if (mx >= bx && mx < bx + buttonW && my >= by && my < by + buttonBaseH) {
            if (b == 0) {
                mod.toggle();
                return true;
            }
            if (b == 1) {
                expanded = !expanded;
                return true;
            }
        }

        if (expanded) {
            int yOffset = by + buttonBaseH;

            for (Setting setting : mod.getSettings()) {
                if (!setting.isVisible()) continue;

                if (setting.click(mx, my, b, bx, yOffset)) {
                    return true;
                }
                yOffset += setting.getHeight();
            }
        }

        return false;
    }

    public int fullHeight() {
        if (!expanded) {
            return buttonBaseH;
        }

        int totalHeight = buttonBaseH;

        for (Setting setting : mod.getSettings()) {
            if (setting.isVisible()) {
                totalHeight += setting.getHeight();
            }
        }
        return totalHeight;
    }

    public void release(double mx, double my, int b, int bx, int by) {}
}