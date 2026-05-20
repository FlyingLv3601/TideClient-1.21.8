package com.tideclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Rotate {

    public static boolean enabled = false;

    public static void rotateTo(Entity target) {
        if (!enabled) return;
        if (target == null) return;

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        double dx = target.getX() - mc.player.getX();
        double dy = target.getY() + 1.5 - (mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose()));
        double dz = target.getZ() - mc.player.getZ();

        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90;
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)));

        float maxDelta = 30;
        float currentYaw = mc.player.getYaw();
        float currentPitch = mc.player.getPitch();

        float deltaYaw = Math.abs(yaw - currentYaw);
        float deltaPitch = Math.abs(pitch - currentPitch);

        if (deltaYaw > maxDelta) {
            yaw = currentYaw + (deltaYaw > 180 ? -maxDelta : maxDelta) * Math.signum(yaw - currentYaw);
        }
        if (deltaPitch > maxDelta) {
            pitch = currentPitch + maxDelta * Math.signum(pitch - currentPitch);
        }

        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(yaw, pitch, mc.player.isOnGround(), true));
    }
}