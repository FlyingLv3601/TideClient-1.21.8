package com.tideclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatMessage {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void moduleStatusLog(String name, boolean status) {
        if (mc.player != null) {
            if(status) {
                chat(name, " enabled");
            }else{
                chat(name, " disabled");
            }
        }
    }

    public static void chat(String name, String isToggled) {
        if (mc.player != null) mc.player.sendMessage(Text.literal("§9[TideClient] §f" + name + " §" + isToggled),false);
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