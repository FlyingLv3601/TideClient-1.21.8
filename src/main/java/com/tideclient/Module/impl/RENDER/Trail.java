package com.tideclient.Module.impl.RENDER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;

public class Trail extends Module {

    MinecraftClient mc = MinecraftClient.getInstance();
    boolean status;

    public Trail(){
        super("Trail", Categories.RENDER);
    }
    public void onEnable() {status = true;}

    public void onDisable() {status = false;}

    public void onTick() {
        if(status){
            if(mc.player == null && mc.world == null) return;
            if(mc.player.getVelocity().lengthSquared() == 0) return;

            mc.worldRenderer.addParticle(ParticleTypes.CHERRY_LEAVES, mc.player.getX(), mc.player.getY() + 0.25, mc.player.getZ(), 0, 0, 0);
        }
    }

}
