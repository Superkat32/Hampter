package net.superkat.hampter.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.superkat.hampter.HampterMain;
import net.superkat.hampter.entity.custom.HampterEntity;

public class ModEntities {
    public static final EntityType<HampterEntity> HAMPTER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(HampterMain.MOD_ID, "hampter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HampterEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
}
