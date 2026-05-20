package com.tideclient.Module.Settings;

import com.tideclient.GUI.comp.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import static com.tideclient.util.TextRenderUtil.TextRender;

public class BooleanSetting extends Setting {

    private boolean enabled;

    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.enabled = defaultValue;
    }

    public void render(DrawContext ctx, int x, int y, int mouseX, int mouseY) {
        ctx.fill(x, y, x + 100, y + 14, Color.expModule);

        TextRender(ctx, name, x + 4, y + 3, 0xFFFFFFFF);

        //String value = enabled ? "⬜" : "-";
        //TODO: change off and on text to square
        //TextRender(ctx, "⬜", x + 70, y + 3, color);
        int bx = x + 82;
        int color = enabled ? Color.trueStatus : Color.falseStatus;
        ctx.fill(bx, y + 3, bx + 9, y + 12, enabled ? 0xBF919191 : 0x40919191);

    }

    public boolean click(double mouseX, double mouseY, int button, int x, int y) {
        if (mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 14) {
            if (button == 0) {
                enabled = !enabled;
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return 14;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setStatus(boolean enabled) {
        this.enabled = enabled;
    }


    public void toggle() {
        this.enabled = !this.enabled;
    }
}