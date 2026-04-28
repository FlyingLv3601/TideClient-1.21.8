package com.tideclient.Module;

import com.tideclient.Module.Config.CONFIG;
import com.tideclient.Module.impl.COMBAT.AutoTotem;
import com.tideclient.Module.impl.CLIENT.Hud;
import com.tideclient.Module.impl.MOVEMENT.Sprint;
import com.tideclient.Module.impl.RENDER.Fullbright;
import com.tideclient.Module.impl.RENDER.Trail;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static final List<Module> modules = new ArrayList<>();

    public static void init(){
        modules.add(new Sprint());
        modules.add(new Hud());
        modules.add(new Fullbright());
        modules.add(new AutoTotem());
        modules.add(new Trail());

        CONFIG.load(modules);
    }

    public static void onTick(){
        for(Module module : modules){
            if(module.getStatus()){
                module.onTick();
            }
        }
    }

    public static List<Module> getModuleByCategory(Categories cat){
        return modules.stream()
                .filter(module -> module.getCategory() == cat)
                .collect(Collectors.toList());
    }

    public static void toggleModule(Module module){
        module.toggle();
        CONFIG.save(modules);
    }
}