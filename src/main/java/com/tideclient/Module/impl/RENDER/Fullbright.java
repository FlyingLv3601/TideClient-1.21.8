package com.tideclient.Module.impl.RENDER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Fullbright extends Module {

    private static boolean status;

    public Fullbright(){
        super("fullbright", Categories.RENDER);
    }


    public void onEnable(){status = true;FullBrightModule(status);}
    public void onDisable(){status = false;FullBrightModule(status);}
    public void onTick(){}


    public static void FullBrightModule(boolean Status){
        MinecraftClient mc = MinecraftClient.getInstance();
        if(Status){
            mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 100000000));
        }else{
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
}
