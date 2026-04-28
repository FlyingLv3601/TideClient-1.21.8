package com.tideclient.Module.impl.COMBAT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotem extends Module {

    public AutoTotem() {
        super("AutoTotem", Categories.COMBAT);
    }

    private int delay = 2;
    private int tick = 0;

    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final int OFFHAND_SLOT = 45;

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;

        if (++tick < delay) return;
        tick = 0;
        if (mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) return;

        int slot = findTotem();
        if (slot == -1) return;

        moveTotem(slot);
    }

    private int findTotem() {
        for (int i = 0; i < 36; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);

            if (stack.getItem() == Items.TOTEM_OF_UNDYING) {
                return (i < 9) ? i + 36 : i;
            }
        }
        return -1;
    }

    private void moveTotem(int slot) {
        int syncId = mc.player.currentScreenHandler.syncId;
        mc.interactionManager.clickSlot(syncId, slot, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(syncId, OFFHAND_SLOT, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(syncId, slot, 0, SlotActionType.PICKUP, mc.player);
    }
}