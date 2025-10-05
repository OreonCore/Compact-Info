package com.compactinfo;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public final class Keybinds {
    private static KeyBinding toggleHudKey;
    private static KeyBinding openHudConfigKey;

    public static void register() {
        // Кейбінд для перемикання HUD (пустий за замовчуванням)
        toggleHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.compactinfo.toggle_hud",         // локалізаційний ключ
                InputUtil.Type.KEYSYM,
                InputUtil.UNKNOWN_KEY.getCode(),      // немає клавіші за замовчуванням
                "category.compactinfo"                // категорія
        ));

        // Кейбінд для відкриття конфігураційного екрану (пустий за замовчуванням)
        openHudConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.compactinfo.open_hud_config",    // локалізаційний ключ
                InputUtil.Type.KEYSYM,
                InputUtil.UNKNOWN_KEY.getCode(),      // немає клавіші за замовчуванням
                "category.compactinfo"                // категорія
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Перемикання HUD
            while (toggleHudKey.wasPressed()) {
                HudOverlay.toggle();
            }

            // Відкриття екрану конфігу
            while (openHudConfigKey.wasPressed()) {
                MinecraftClient.getInstance().setScreen(
                        new HudConfigScreen(MinecraftClient.getInstance().currentScreen)
                );
            }
        });
    }
}
