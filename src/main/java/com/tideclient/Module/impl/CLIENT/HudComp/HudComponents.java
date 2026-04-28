package com.tideclient.Module.impl.CLIENT.HudComp;

import com.tideclient.GUI.comp.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com .tideclient.Module.Module;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tideclient.Module.ModuleManager.modules;


public class HudComponents {
    static MinecraftClient mc = MinecraftClient.getInstance();
    static int w = mc.getWindow().getScaledWidth();
    static int h = mc.getWindow().getScaledHeight();
    static List<String> ModuleList = new ArrayList<>();


    public static void HudInit(boolean status, DrawContext ctx){
        if(status){
            textRenderEngine(ctx, logo(), 5,3);
            textRenderEngine(ctx, fps(), 5,12);

            int textWidthSpeed = mc.textRenderer.getWidth(speed());
            textRenderEngine(ctx, speed(), (w - textWidthSpeed) - 5, h - 20);

            int textWidthPosition = mc.textRenderer.getWidth(position());
            textRenderEngine(ctx, position(), (w - textWidthPosition) - 5, h - 10);
        }else{

        }
    }


    public static void getActiveModule(DrawContext ctx){
        int y = 3;
        for(Module module : modules){
            if(!module.getStatus()) continue;
            int textModuleListWidth = mc.textRenderer.getWidth(module.getName());

            textRenderEngine(ctx, module.getName(), (w - textModuleListWidth)  , y - 5);
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




    public static void textRenderEngine(DrawContext ctx, String text, int x, int y){
        MinecraftClient client = MinecraftClient.getInstance();
        ctx.drawText(client.textRenderer, text, x, y, Color.activeBtn, true);
    }

    public static List<String> moduleList(boolean stat){
        return modules.stream().filter(module -> module.getStatus() == stat).map(Module -> Module.getName()).collect(Collectors.toList());
    }

}
