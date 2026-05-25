package com.tideclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

import java.util.Random;

public class RotateToEntiy {
    private static float yaw;
    private static float pitch;
    private static final Random random = new Random();


    public static void RoatetToEntity(Entity entity) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (entity == null) return;

        double x = entity.getX() - mc.player.getX();
        double y = (entity.getY() + entity.getStandingEyeHeight() * 0.9) - (mc.player.getY() + mc.player.getStandingEyeHeight());
        double z = entity.getZ() - mc.player.getZ();

        double dist = Math.sqrt(x * x + y * y + z * z);
        yaw = (float) (Math.atan2(z, x) * (180.0 / Math.PI)) - 90.0f;
        pitch = (float) -(Math.atan2(y, dist) * (180.0 / Math.PI));


    }

    public static float getYaw(){
        return yaw * random.nextFloat(1);
    }

    public static float getPitch(){
        return pitch * random.nextFloat(1);
    }
}
