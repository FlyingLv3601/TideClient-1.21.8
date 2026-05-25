package com.tideclient.Module.impl.RENDER;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.EntityType;

import java.util.HashSet;
import java.util.Set;

import static com.tideclient.util.ChatMessage.moduleStatusLog;

public class KillEffect extends Module {

    private final Set<Integer> lastEntities = new HashSet<>();
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public KillEffect() {
        super("KillEffect", Categories.RENDER);
    }
    @Override
    public void onEnable() {
        moduleStatusLog(getName(), true);
    }

    @Override
    public void onDisable() {
        moduleStatusLog(getName(), false);
        lastEntities.clear();
    }


    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) {
            lastEntities.clear();
            return;
        }

        Set<Integer> currentEntities = new HashSet<>();

        for (Entity entity : mc.world.getEntities()) {
            if (entity.isAlive() && entity != mc.player) {
                currentEntities.add(entity.getId());
            }
        }

        for (Integer id : lastEntities) {
            if (!currentEntities.contains(id)) {
                Entity deadEntity = mc.world.getEntityById(id);
                double x, y, z;

                if (deadEntity != null) {
                    x = deadEntity.getX();
                    y = deadEntity.getY();
                    z = deadEntity.getZ();
                } else {
                    continue;
                }

                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, mc.world);
                lightning.refreshPositionAfterTeleport(x, y + 0.5, z);
                lightning.setCosmetic(true);
                mc.world.addEntity(lightning);
            }
        }

        lastEntities.clear();
        lastEntities.addAll(currentEntities);
    }

}