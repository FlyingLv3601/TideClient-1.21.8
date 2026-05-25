package com.tideclient.Module.impl.MOVEMENT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import net.minecraft.client.MinecraftClient;

import static com.tideclient.util.ChatMessage.moduleStatusLog;

public class Sprint extends Module {
    public Sprint(){
        super("Sprint", Categories.MOVEMENT);
    }




    @Override
    public void onEnable() {
        moduleStatusLog(getName(), true);
    }

    @Override
    public void onDisable() {
        moduleStatusLog(getName(), false);
    }

    public void onTick(){SetSprint(true);}


    public void SetSprint(boolean status){
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player != null){
            if(status){
                if(mc.player.forwardSpeed > 0)mc.player.setSprinting(true);
            }
            else{mc.player.setSprinting(false);}
        }else{
            return;
        }
    }


}
