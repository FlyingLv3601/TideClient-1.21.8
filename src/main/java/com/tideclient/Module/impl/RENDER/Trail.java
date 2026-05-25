package com.tideclient.Module.impl.RENDER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import com.tideclient.Module.Settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;

import static com.tideclient.util.ChatMessage.moduleStatusLog;

public class Trail extends Module {

    MinecraftClient mc = MinecraftClient.getInstance();
    boolean status;

    public final BooleanSetting Cherry = new BooleanSetting("Cherry", true);
    public final BooleanSetting Campfire = new BooleanSetting("campfire", false);


    SimpleParticleType cherry = ParticleTypes.CHERRY_LEAVES;
    SimpleParticleType campfire = ParticleTypes.CAMPFIRE_COSY_SMOKE;



    public Trail(){
        super("Trail", Categories.RENDER);
        addSettings(Cherry, Campfire);
    }
    @Override
    public void onEnable() {
        moduleStatusLog(getName(), true);
        status = true;
    }

    @Override
    public void onDisable() {
        moduleStatusLog(getName(), false);
        status = false;
    }

    public void onTick() {
        if(mc.player == null && mc.world == null) return;
        if(mc.player.getVelocity().lengthSquared() == 0) return;
        if(status){
            if(Cherry.isEnabled()){
                renderTrail(cherry);
            }else if(Campfire.isEnabled()){
                renderTrail(campfire);
            }
        }
    }

    public void renderTrail(SimpleParticleType t){
        mc.worldRenderer.addParticle(t, mc.player.getX(), mc.player.getY() + 0.25, mc.player.getZ(), 0, 0, 0);
    }
}
