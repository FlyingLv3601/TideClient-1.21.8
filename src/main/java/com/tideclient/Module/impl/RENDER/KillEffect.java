package com.tideclient.Module.impl.RENDER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;

public class KillEffect extends Module {

    boolean status;

    public KillEffect(){
        super("KillEffect", Categories.RENDER);
        ServerLivingEntityEvents.AFTER_DEATH.register(((entity, damageSource) -> {
            if(status){
                if(entity.getWorld() instanceof ServerWorld serverWorld){
                    LightningEntity lightning = EntityType.LIGHTNING_BOLT.spawn(serverWorld, entity.getBlockPos(), SpawnReason.TRIGGERED);
                    if (lightning != null) {
                        lightning.setCosmetic(true);
                    }
                }
            }
        }));

    }

    public void onEnable(){ status = true;}
    public void onDisable(){status = false;}
    public void onTick(){}


//Works only in single player ill fix it later (I hope)
}
