package net.superkat.hampter.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.superkat.hampter.entity.ModEntities;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                BiomeKeys.PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SUNFLOWER_PLAINS,
                BiomeKeys.FOREST, BiomeKeys.DARK_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST,
                    BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.WINDSWEPT_FOREST,
                BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.SPARSE_JUNGLE,
                BiomeKeys.DESERT, BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS, BiomeKeys.WOODED_BADLANDS,
                BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_SAVANNA,
                BiomeKeys.TAIGA, BiomeKeys.SNOWY_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
                BiomeKeys.BEACH, BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_SLOPES, BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP,
                BiomeKeys.MEADOW, BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.STONY_PEAKS, BiomeKeys.STONY_SHORE), SpawnGroup.CREATURE,
                ModEntities.HAMPTER, 75, 1, 10);

        SpawnRestriction.register(ModEntities.HAMPTER, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AmbientEntity::canMobSpawn);
    }

}
