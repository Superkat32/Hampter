package net.superkat.hampter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.superkat.hampter.entity.ModEntities;
import net.superkat.hampter.entity.custom.HampterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class HampterMain implements ModInitializer {
	public static final String MOD_ID = "hampter";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		//GeckoLib
		GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(ModEntities.HAMPTER, HampterEntity.setAttributes());

		LOGGER.info("Hello, Fabric world!");
	}
}