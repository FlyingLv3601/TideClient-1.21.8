package com.tideclient.Module.impl.RENDER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import static com.tideclient.util.ChatMessage.moduleStatusLog;

public class Fullbright extends Module {

    private static boolean status;
    private static MinecraftClient mc = MinecraftClient.getInstance();

    public Fullbright(){
        super("fullbright", Categories.RENDER);
    }



    @Override
    public void onEnable() {
        moduleStatusLog(getName(), true);
    }

    @Override
    public void onDisable() {
        moduleStatusLog(getName(), false);
        FullBrightModule(false);
    }
    @Override
    public void onTick(){
        if(!mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION) && status){
            FullBrightModule(true);
        }
    }


    public static void FullBrightModule(boolean Status){
        MinecraftClient mc = MinecraftClient.getInstance();
        if(Status){
            mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 100000000));
        }else{
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
}
