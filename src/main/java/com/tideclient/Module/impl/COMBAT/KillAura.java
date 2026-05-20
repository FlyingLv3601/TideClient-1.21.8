package com.tideclient.Module.impl.COMBAT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import com.tideclient.Module.Settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import static com.tideclient.util.Rotate.rotateTo;

public class KillAura extends Module {

    public final BooleanSetting players = new BooleanSetting("Players", false);
    public final BooleanSetting mobs = new BooleanSetting("Mobs", true);
    public final BooleanSetting faceTarget = new BooleanSetting("Face Target", false);
    public final BooleanSetting silentRotate = new BooleanSetting("Silent Rotate", true);

    private int tick = 0;
    private final int attackDelay = 13;
    private int attacks = 0;

    private final MinecraftClient mc = MinecraftClient.getInstance();

    public KillAura() {
        super("KillAura", Categories.COMBAT);
        addSettings(players, mobs, faceTarget, silentRotate);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;

        Entity target = getClosestEntity();
        if (target == null) {
            tick = 0;
            return;
        }

        if (faceTarget.isEnabled()) {
            faceTarget(target);
        }
        else if (silentRotate.isEnabled()) {
            rotateTo(target);
        } else if(silentRotate.isEnabled() && faceTarget.isEnabled()){
            return;
        }

        tick++;

        if(attacks == 0){
            attackEntity(target);
            attacks++;
        }else {
            if (tick >= attackDelay) {
                attackEntity(target);
                tick = 0;
                attacks++;
            }
        }
    }

    public Entity getClosestEntity() {
        if (mc.player == null || mc.world == null) return null;

        Entity closest = null;
        double minDist = 5.0;

        for (Entity ent : mc.world.getEntities()) {
            if (!(ent instanceof LivingEntity living) ||
                    ent == mc.player ||
                    !living.isAlive()) {
                continue;
            }

            boolean isPlayer = living instanceof PlayerEntity;

            if ((isPlayer && players.isEnabled()) || (!isPlayer && mobs.isEnabled())) {
                double dist = mc.player.distanceTo(living);
                if (dist < minDist) {
                    minDist = dist;
                    closest = living;
                }
            }
        }
        return closest;
    }

    private void faceTarget(Entity entity) {
        if (entity == null) return;

        double x = entity.getX() - mc.player.getX();
        double y = (entity.getY() + entity.getStandingEyeHeight() * 0.9) - (mc.player.getY() + mc.player.getStandingEyeHeight());
        double z = entity.getZ() - mc.player.getZ();

        double dist = Math.sqrt(x * x + y * y + z * z);

        float yaw = (float) (Math.atan2(z, x) * (180.0 / Math.PI)) - 90.0f;
        float pitch = (float) -(Math.atan2(y, dist) * (180.0 / Math.PI));

        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }

    private void attackEntity(Entity entity) {
        if (entity == null || mc.player.distanceTo(entity) > 5.0f) return;

        mc.player.swingHand(Hand.MAIN_HAND);
        mc.interactionManager.attackEntity(mc.player, entity);
    }
}