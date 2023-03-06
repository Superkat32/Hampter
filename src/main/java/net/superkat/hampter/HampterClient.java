package net.superkat.hampter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.superkat.hampter.entity.ModEntities;
import net.superkat.hampter.entity.client.HampterRenderer;

public class HampterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HampterMain.LOGGER.info("HampterClient ready!");
        EntityRendererRegistry.register(ModEntities.HAMPTER, HampterRenderer::new);
    }
}
