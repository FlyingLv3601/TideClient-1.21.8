package com.tideclient.GUI;

import com.tideclient.GUI.comp.Color;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class ModuleButton {
    private final Module mod;

    public ModuleButton(Module mod) {
        this.mod = mod;
    }

    public void render(DrawContext ctx, int bx, int by, int mx, int my) {
        int color = mod.getStatus() ? Color.activeBtn : Color.noneActiveBtn;
        boolean Stat;
        int col;
        if(mod.getStatus()){
            Stat = true;
            col = Color.trueStatus;
        }else{
            Stat = false;
            col = Color.falseStatus;
        }
        ctx.fill(bx, by, bx + 100, by + 14, color);
        ctx.drawText(MinecraftClient.getInstance().textRenderer, mod.getName(), bx + 4, by + 3, 0xFFFFFFFF, false);
        ctx.drawText(MinecraftClient.getInstance().textRenderer, String.valueOf(Stat), bx + 70, by + 3, col, false);
    }

    public boolean click(double mx, double my, int b, int bx, int by) {
        if (mx < bx || mx >= bx + 100 || my < by || my >= by + 14) {
            return false;
        }

        if (b == 0) {
            mod.toggle();
            return true;
        }
        return false;
    }

    public void release(double mx, double my, int b, int bx, int by) {}

    public int fullHeight() {
        return 14;
    }
}