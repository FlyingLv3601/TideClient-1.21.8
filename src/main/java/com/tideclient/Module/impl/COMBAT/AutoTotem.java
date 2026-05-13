package com.tideclient.Module.impl.COMBAT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

import static com.tideclient.util.ChatMessage.whenDisable;
import static com.tideclient.util.ChatMessage.whenEnabled;
import static com.tideclient.util.ItemSearch.findItem;

public class AutoTotem extends Module {

    public AutoTotem() {
        super("AutoTotem", Categories.COMBAT);
    }

    private int delay = 2;
    private int tick = 0;

    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final int OFFHAND_SLOT = 45;

    String name = getName();

    public void onEnable() {
        whenEnabled(name);
    }

    public void onDisable(){
        whenDisable(name);
    }

    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;

        if (++tick < delay) return;
        tick = 0;
        if (mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) return;

        int slot = findItem(Items.TOTEM_OF_UNDYING);
        if (slot == -1) return;

        moveTotem(slot);
    }



    private void moveTotem(int slot) {
        int syncId = mc.player.currentScreenHandler.syncId;
        mc.interactionManager.clickSlot(syncId, slot, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(syncId, OFFHAND_SLOT, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(syncId, slot, 0, SlotActionType.PICKUP, mc.player);
    }
}