package net.superkat.hampter.entity.client;

import net.minecraft.util.Identifier;
import net.superkat.hampter.HampterMain;
import net.superkat.hampter.entity.custom.HampterEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HampterModel extends AnimatedGeoModel<HampterEntity> {
    private static final Identifier modelResource = new Identifier(HampterMain.MOD_ID, "geo/hampter.geo.json");
    private static final Identifier textureResource = new Identifier(HampterMain.MOD_ID, "textures/entity/hamptertexture.png");
    private static final Identifier animationResource = new Identifier(HampterMain.MOD_ID, "animations/hampter.animation.json");

    @Override
    public Identifier getModelResource(HampterEntity object) {
        return modelResource;
    }

    @Override
    public Identifier getTextureResource(HampterEntity object) {
        return textureResource;
    }

    @Override
    public Identifier getAnimationResource(HampterEntity animatable) {
        return animationResource;
    }
}
