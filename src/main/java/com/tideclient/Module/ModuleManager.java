package com.tideclient.Module;

import com.tideclient.Module.impl.CLIENT.Hud;
import com.tideclient.Module.impl.MOVEMENT.Sprint;
import com.tideclient.Module.impl.RENDER.Fullbright;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();
    //add here modules "modules.add(ModuleObjecr);"
    public static void init(){
        modules.add(new Sprint());
        modules.add(new Hud());
        modules.add(new Fullbright());
    }

    public static void onTick(){
        for(Module module : modules){
            if(module.getStatus()){
                module.onTick();
            }
        }
    }
    public static List<Module> getModuleByCategory(Categories cat){
        return modules.stream().filter(module -> module.getCategory() == cat).collect(Collectors.toList());
    }
}
