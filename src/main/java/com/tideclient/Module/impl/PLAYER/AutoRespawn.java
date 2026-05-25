package com.tideclient.Module.impl.PLAYER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;

import static com.tideclient.util.ChatMessage.moduleStatusLog;

public class AutoRespawn extends Module {
    public AutoRespawn(){
        super("AutoRespawn", Categories.PLAYER);
    }

    boolean isEnabled;

    @Override
    public void onEnable() {
        moduleStatusLog(getName(), true);
    }

    @Override
    public void onDisable() {
        moduleStatusLog(getName(), false);
    }

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
