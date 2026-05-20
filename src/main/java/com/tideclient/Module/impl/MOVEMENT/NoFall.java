package com.tideclient.Module.impl.MOVEMENT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module {
    public NoFall(){
        super("NoFall", Categories.MOVEMENT);
    }

    MinecraftClient mc = MinecraftClient.getInstance();


    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {
        if(mc.player == null) return;

        if(mc.player.fallDistance > 3) mc.player.networkHandler.sendPacket((new PlayerMoveC2SPacket.OnGroundOnly(true, true)));
    }
}
