package com.tideclient;

import com.tideclient.GUI.ClickGuiScreen;
import com.tideclient.Module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TideClient implements ClientModInitializer {
	public static final String MOD_ID = "tideclient";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static KeyBinding OPEN_GUI;
	@Override
	public void onInitializeClient() {



		OPEN_GUI = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.hanaclient.opengui",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_R,
				"key.categories.hanaclient"
		));


		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) return;

			ModuleManager.onTick();

			if (OPEN_GUI.wasPressed()) {
				client.setScreen(new ClickGuiScreen());
			}
		});

		ModuleManager.init();




		LOGGER.info("Hello Fabric world!");
	}
}