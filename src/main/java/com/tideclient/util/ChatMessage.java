package com.tideclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatMessage {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void whenEnabled(String name) {
        if (mc.player != null) {
            mc.player.sendMessage(
                    Text.literal("§9[TideClient] §f" + name + " §aenabled"),
                    false
            );
        }
    }

    public static void whenDisable(String name) {
        if (mc.player != null) {
            mc.player.sendMessage(
                    Text.literal("§9[TideClient] §f" + name + " §cdisabled"),
                    false
            );
        }
    }


    public static void statusDebug(Boolean status) {
        if (mc.player != null) {
            mc.player.sendMessage(
                    Text.literal("§9[TideClient] §f" + status),
                    false
            );
        }
    }
}