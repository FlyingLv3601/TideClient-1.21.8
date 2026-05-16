package com.tideclient.Module.impl.CLIENT.HudComp;

import com.tideclient.GUI.comp.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com .tideclient.Module.Module;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tideclient.GUI.comp.Color.hudColor;
import static com.tideclient.Module.ModuleManager.modules;
import static com.tideclient.util.TextRenderUtil.TextRender;


public class HudComponents {
    static MinecraftClient mc = MinecraftClient.getInstance();
    static int w = mc.getWindow().getScaledWidth();
    static int h = mc.getWindow().getScaledHeight();
    static List<String> ModuleList = new ArrayList<>();


    public static void HudInit(boolean status, DrawContext ctx){
        if(status){
            TextRender(ctx, logo(), 5,3,hudColor);
            TextRender(ctx, fps(), 5,12,hudColor);

            int textWidthSpeed = mc.textRenderer.getWidth(speed());
            TextRender(ctx, speed(), (w - textWidthSpeed) - 5, h - 20,hudColor);

            int textWidthPosition = mc.textRenderer.getWidth(position());
            TextRender(ctx, position(), (w - textWidthPosition) - 5, h - 10,hudColor);
        }else{
            return;
        }
    }


    public static void getActiveModule(DrawContext ctx){
        int y = 5;
        for(Module module : modules){
            if(!module.getStatus()) continue;
            int textModuleListWidth = mc.textRenderer.getWidth(module.getName());

            TextRender(ctx, module.getName(), (w - textModuleListWidth)  , y - 5,hudColor);
            y += 15;
        }
    }


    public static String logo(){return "TideClient v0.1";}
    public static String fps() {return "Fps: " + String.valueOf(mc.getCurrentFps());}

    public static String position(){
        int x = (int)mc.player.getX();
        int y = (int)mc.player.getY();
        int z = (int)mc.player.getZ();
        return "X: " + x + " Y: " + y + " Z: " + z;
    }

    public static String speed() {
        double dx = mc.player.getX() - mc.player.lastRenderX;
        double dz = mc.player.getZ() - mc.player.lastRenderZ;
        double bps = Math.hypot(dx, dz) * 20;
        return "Speed: " + String.format("%.1f", bps);
    }





    public static List<String> moduleList(boolean stat){
        return modules.stream().filter(module -> module.getStatus() == stat).map(Module -> Module.getName()).collect(Collectors.toList());
    }

}
