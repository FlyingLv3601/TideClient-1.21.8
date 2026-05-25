package com.tideclient.util;


import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class CriticalUtil {
    private static MinecraftClient mc = MinecraftClient.getInstance();
    public static void doCriticalHit() {
        double x = mc.player.getX();
        double y = mc.player.getY();
        double z = mc.player.getZ();
        //double height = 0.0625 + Math.random.nextDouble() * 0.03;


    }
}
