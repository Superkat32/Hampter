package net.superkat.hampter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.superkat.hampter.entity.ModEntities;
import net.superkat.hampter.entity.custom.HampterEntity;
import net.superkat.hampter.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class HampterMain implements ModInitializer {
	public static final String MOD_ID = "hampter";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final DefaultParticleType PAPER_AIRPLANE = FabricParticleTypes.simple();
//	public static final Item HAMPTER_SPAWN_EGG = new SpawnEggItem(ModEntities.HAMPTER, 0x725b4a, 0x48372c, new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item HAMPTER_SPAWN_EGG = new SpawnEggItem(ModEntities.HAMPTER, 0x725b4a, 0xa89d88, new FabricItemSettings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {

		//GeckoLib
		GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(ModEntities.HAMPTER, HampterEntity.setAttributes());

        //WorldGen (Needed to spawn in Hampter)
        ModWorldGen.generateWorldGen();

		//Spawn egg item
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hampter_spawn_egg"), HAMPTER_SPAWN_EGG);

		//Particle
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "paper_airplane"), PAPER_AIRPLANE);

		LOGGER.info("Hello, Fabric world!");
	}
}