package net.superkat.hampter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.superkat.hampter.entity.ModEntities;
import net.superkat.hampter.entity.client.HampterRenderer;

public class HampterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Hampter Entity
        EntityRendererRegistry.register(ModEntities.HAMPTER, HampterRenderer::new);

        //Particle
        ParticleFactoryRegistry.getInstance().register(HampterMain.PAPER_AIRPLANE, PaperAirplaneParticle.Factory::new);

        HampterMain.LOGGER.info("HampterClient ready!");
    }
}
