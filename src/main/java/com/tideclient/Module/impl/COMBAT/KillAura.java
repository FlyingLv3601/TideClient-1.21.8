package com.tideclient.Module.impl.COMBAT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import com.tideclient.Module.Settings.BooleanSetting;
import com.tideclient.util.CriticalUtil;
import com.tideclient.util.RotateToEntiy;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import java.util.Random;
import static com.tideclient.util.ChatMessage.moduleStatusLog;
import static com.tideclient.util.RotateToEntiy.*;

public class KillAura extends Module {

    public final BooleanSetting players = new BooleanSetting("Players", false);
    public final BooleanSetting mobs = new BooleanSetting("Mobs", true);
    public final BooleanSetting silentRotate = new BooleanSetting("Silent Rotate", true);
    public final BooleanSetting FaceTarget = new BooleanSetting("FaceTarget", false);
    public final BooleanSetting logs = new BooleanSetting("Logs", false);

    private final Random random = new Random();
    private int tick = 0;
    private int attackDelay;

    private final MinecraftClient mc = MinecraftClient.getInstance();

    private double maxDist = 4.8;

    public KillAura() {
        super("KillAura", Categories.COMBAT);
        addSettings(players, mobs, silentRotate,FaceTarget,logs);
    }

    @Override
    public void onEnable() {
        attackDelay = 20;
        tick = 0;
        moduleStatusLog(getName(), true);
    }

    @Override
    public void onDisable() {
        tick = 0;
        moduleStatusLog(getName(), false);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;

        Entity target = getClosestEntity();
        if (target == null) {
            tick = 0;
            return;
        }

        tick++;

        if (tick >= attackDelay) {
            attackEntity(target);
            tick = 0;
            attackDelay = 10 + random.nextInt(10);
        }
    }

    private Entity getClosestEntity() {
        if (mc.player == null || mc.world == null) return null;

        Entity closest = null;


        for (Entity ent : mc.world.getEntities()) {
            if (!(ent instanceof LivingEntity living) || ent == mc.player || !living.isAlive()) continue;

            boolean isPlayer = living instanceof PlayerEntity;
            if ((isPlayer && !players.isEnabled()) || (!isPlayer && !mobs.isEnabled())) continue;

            double dist = mc.player.distanceTo(living);
            if (dist <= maxDist) {
                closest = living;
            }
        }
        return closest;
    }

    private void attackEntity(Entity entity) {
        if (entity == null || mc.player.distanceTo(entity) > maxDist) return;

        mc.player.swingHand(Hand.MAIN_HAND);
        mc.interactionManager.attackEntity(mc.player, entity);
    }


    private void fastRotate(Entity target) {
        if (target == null) return;
        int rotateDelay = random.nextInt(1);
        if(tick >= rotateDelay){
            float prevYaw = mc.player.getYaw();
            float prevPitch = mc.player.getPitch();

            RotateToEntiy.RoatetToEntity(target);
            float yaw = RotateToEntiy.getYaw();
            float pitch = RotateToEntiy.getPitch();



            mc.player.setBodyYaw(yaw);
            mc.player.setYaw(yaw);
            mc.player.setPitch(pitch);

            mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(yaw, pitch, mc.player.isOnGround(), false));

            mc.player.setYaw(prevYaw);
            mc.player.setPitch(prevPitch);
        }
    }
}