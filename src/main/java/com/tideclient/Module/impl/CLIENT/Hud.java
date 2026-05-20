package com.tideclient.Module.impl.CLIENT;

import com.tideclient.Module.Categories;
import com.tideclient.Module.Module;
import com.tideclient.Module.impl.CLIENT.HudComp.HudComponents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import static com.tideclient.Module.impl.CLIENT.HudComp.HudComponents.getActiveModule;
import static com.tideclient.util.ChatMessage.*;

public class Hud extends Module {
    private static boolean shouldRender = false;


    public Hud() {
        super("Hud", Categories.CLIENT);

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (shouldRender) {
                HudComponents.HudInit(true, drawContext);
                getActiveModule(drawContext);
            }
        });
    }

    String name = getName();

    @Override
    public void onEnable() {
        shouldRender = true;
        moduleStatusLog(name, true);
    }

    @Override
    public void onDisable() {
        shouldRender = false;
        moduleStatusLog(name, false);
    }
}