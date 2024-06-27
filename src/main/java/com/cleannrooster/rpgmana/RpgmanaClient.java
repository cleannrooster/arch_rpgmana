package com.cleannrooster.rpgmana;

import com.cleannrooster.rpgmana.client.InGameHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class RpgmanaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new InGameHud());

    }
}
