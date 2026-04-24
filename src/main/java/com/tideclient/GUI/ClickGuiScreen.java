package com.tideclient.GUI;

import com.tideclient.Module.Categories;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {

    private final List<Panel> panels = new ArrayList<>();

    public ClickGuiScreen() {
        super(Text.literal("ClickGUI"));

        int x = 20;
        for (Categories category : Categories.values()) {
            panels.add(new Panel(category, x, 20));
            x += 120;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0x00000000);
        for (Panel panel : panels) panel.render(context, mouseX, mouseY);
        super.render(context, mouseX, mouseY, delta);
    }


    @Override
    public boolean mouseClicked(double mx, double my, int b) {
        for (Panel panel : panels) if (panel.click(mx, my, b)) return true;

        return super.mouseClicked(mx, my, b);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int b) {
        for (Panel panel : panels) panel.release(mx, my, b);
        return super.mouseReleased(mx, my, b);
    }
}