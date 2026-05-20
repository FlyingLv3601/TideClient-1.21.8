package com.tideclient.Module.impl.PLAYER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;

public class AutoRespawn extends Module {
    public AutoRespawn(){
        super("AutoRespawn", Categories.PLAYER);
    }

    boolean isEnabled;

    @Override
    public void onEnable() {isEnabled = true;}

    @Override
    public void onDisable() {isEnabled = false;}

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(isEnabled && mc.player != null){
            if(mc.player.isDead()){
                mc.player.requestRespawn();
            }
        }
    }
}
