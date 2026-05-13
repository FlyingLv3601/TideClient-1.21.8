package com.tideclient.Module;

import com.tideclient.Module.Settings.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {
    private final String name;
    private final Categories category;
    private boolean isEnabled = false;
    private static MinecraftClient mc = MinecraftClient.getInstance();
    private final List<Setting> settings = new ArrayList<>();



    public Module(String name, Categories category){
        this.name = name;
        this.category = category;
    }

    public boolean toggle(){
        isEnabled = !isEnabled;
        if(isEnabled) onEnable(); else onDisable();
        return isEnabled;
    }

    public void onEnable() {}
    public void onDisable(){}
    public void onTick(){}

    public String getName(){return name;}
    public Categories getCategory(){return category;}
    public boolean getStatus(){return isEnabled;}


    protected void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public List<Setting> getSettings() {
        return settings;
    }
}