package com.tideclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.screen.slot.SlotActionType;

public class InventoryUtil {
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static void moveItem(int slot, int HandSlot) {
        int syncId = mc.player.currentScreenHandler.syncId;
        mc.interactionManager.clickSlot(syncId, slot, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(syncId, HandSlot, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(syncId, slot, 0, SlotActionType.PICKUP, mc.player);
    }
}
