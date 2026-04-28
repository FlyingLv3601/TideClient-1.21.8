package com.tideclient.Module;

public abstract class Module {
    private final String name;
    private final Categories category;
    private boolean isEnabled = false;

    public Module(String name, Categories category){
        this.name = name;
        this.category = category;
    }

    public boolean toggle(){
        isEnabled = !isEnabled;
        if(isEnabled) onEnable(); else onDisable();
        return isEnabled;
    }

    public void onEnable(){}
    public void onDisable(){}
    public void onTick(){}

    public String getName(){return name;}
    public Categories getCategory(){return category;}
    public boolean getStatus(){return isEnabled;}
}