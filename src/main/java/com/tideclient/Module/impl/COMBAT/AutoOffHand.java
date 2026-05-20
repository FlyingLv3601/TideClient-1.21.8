package com.tideclient.Module.impl.COMBAT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import com.tideclient.Module.Settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import static com.tideclient.util.ChatMessage.*;
import static com.tideclient.util.InventoryUtil.moveItem;
import static com.tideclient.util.ItemSearch.findItem;

public class AutoOffHand extends Module {



    private int delay = 2;
    private int tick = 0;

    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final int OFFHAND_SLOT = 45;

    Item crystal = Items.END_CRYSTAL;
    Item gapples = Items.ENCHANTED_GOLDEN_APPLE;

    public final BooleanSetting Crystals = new BooleanSetting("Crystals", false);
    public final BooleanSetting Gapples = new BooleanSetting("Gapples", true);

    public AutoOffHand() {
        super("AutoOffHand", Categories.COMBAT);
        addSettings(Crystals, Gapples);

    }

    String name = getName();

    public void onEnable() {
        moduleStatusLog(name, true);
    }

    public void onDisable(){
        moduleStatusLog(name, false);
    }

    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        if (++tick < delay) return;
        tick = 0;

        if(Crystals.isEnabled() && !Gapples.isEnabled()) {
            int slot = findItem(crystal);
            if (mc.player.getOffHandStack().getItem() == crystal) return;
            if (slot == -1) return;
            Gapples.setStatus(false);
            moveItem(slot, 45);
        }else if(!Crystals.isEnabled() && Gapples.isEnabled()){
            int slot = findItem(gapples);
            if (mc.player.getOffHandStack().getItem() == gapples) return;
            if (slot == -1) return;
            Crystals.setStatus(false);
            moveItem(slot, 45);
        }else{
            return;
        }

    }




}