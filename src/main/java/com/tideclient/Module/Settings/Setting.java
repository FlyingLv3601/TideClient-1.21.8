package com.tideclient.Module.Settings;

import net.minecraft.client.gui.DrawContext;

public abstract class Setting {
    protected String name;

    public Setting(String name) {
        this.name = name;
    }

    public abstract void render(DrawContext ctx, int x, int y, int mouseX, int mouseY);
    public abstract boolean click(double mouseX, double mouseY, int button, int x, int y);
    public abstract int getHeight();

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return true;
    }
}