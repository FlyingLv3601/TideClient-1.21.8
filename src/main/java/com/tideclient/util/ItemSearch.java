package com.tideclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSearch {
    static MinecraftClient mc = MinecraftClient.getInstance();

    public static int findItem(Item item) {
        for (int i = 0; i < 36; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);

            if (stack.getItem() == item) {
                return (i < 9) ? i + 36 : i;
            }
        }
        return -1;
    }
}