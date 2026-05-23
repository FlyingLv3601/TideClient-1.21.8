package com.tideclient.Module.impl.COMBAT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import com.tideclient.util.InventoryUtil;
import com.tideclient.util.ItemSearch;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

import static com.tideclient.util.ChatMessage.moduleStatusLog;

public class AutoWindCharge extends Module {
    public AutoWindCharge(){
        super("AutoWindCharge", Categories.COMBAT);
    }

    boolean status;
    private static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onDisable() {
        moduleStatusLog(getName(), false);status = false;
    }

    @Override
    public void onEnable() {
        moduleStatusLog(getName(), true);
        status = true;
    }

    @Override
    public void onTick() {
        if(status){
            if(mc.player != null && mc.world != null){
                if(mc.player.fallDistance > 3 || mc.player.isJumping()){
                    findDistanceToGround();
                }
            }
        }

    }



    private static void findDistanceToGround(){
        float startPitch = mc.player.getPitch();
        int distance;
        int lastSelectedSlot = mc.player.getInventory().getSelectedSlot();
        BlockPos playerPos= mc.player.getBlockPos();

        for(int i = 1; i <= 60; i++){
            BlockPos checkPos = playerPos.down(i);
            BlockState state = mc.world.getBlockState(checkPos);
            if(state.isSolidBlock(mc.world, checkPos)){
                distance = (int) mc.player.getY() - checkPos.getY();
                if(distance <= 2.85){
                    useWindCharge(lastSelectedSlot, startPitch);
                }
                return;
            }
        }

    }

    public static void useWindCharge(int lastSelectedSlot, float startPitch){
        int slot = ItemSearch.findItem(Items.WIND_CHARGE);
        if(slot == -1){
            return;
        }
        mc.player.setPitch(89);
        InventoryUtil.moveItem(slot, 0);
        mc.player.getInventory().setSelectedSlot(0);
        mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
        mc.player.getInventory().setSelectedSlot(lastSelectedSlot);
        mc.player.setPitch(startPitch);
    }



}
