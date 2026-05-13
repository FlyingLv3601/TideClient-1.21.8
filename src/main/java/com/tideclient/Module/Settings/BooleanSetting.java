package com.tideclient.Module.Settings;

import com.tideclient.GUI.comp.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class BooleanSetting extends Setting {

    private boolean enabled;

    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.enabled = defaultValue;
    }

    public void render(DrawContext ctx, int x, int y, int mouseX, int mouseY) {
        ctx.fill(x, y, x + 100, y + 14, 0xFF2B2B2B);

        ctx.drawText(MinecraftClient.getInstance().textRenderer, name, x + 4, y + 3, 0xFFFFFFFF, false);

        String value = enabled ? "§aON" : "§cOFF";
        //TODO: change off and on text to square
        int color = enabled ? Color.trueStatus : Color.falseStatus;

        ctx.drawText(MinecraftClient.getInstance().textRenderer, value, x + 70, y + 3, color, false);
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}