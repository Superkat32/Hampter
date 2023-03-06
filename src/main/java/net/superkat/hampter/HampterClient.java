package net.superkat.hampter;

import net.fabricmc.api.ClientModInitializer;

public class HampterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HampterMain.LOGGER.info("HampterClient ready!");
    }
}
