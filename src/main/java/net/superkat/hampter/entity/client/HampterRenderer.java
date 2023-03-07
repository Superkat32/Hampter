package net.superkat.hampter.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.superkat.hampter.HampterMain;
import net.superkat.hampter.entity.custom.HampterEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HampterRenderer extends GeoEntityRenderer<HampterEntity> {
    public HampterRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new HampterModel());
    }

    @Override
    public Identifier getTextureResource(HampterEntity instance) {
        return new Identifier(HampterMain.MOD_ID, "textures/entity/hamptertexture.png");
    }

    @Override
    public RenderLayer getRenderType(HampterEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.4f, 1.4f, 1.4f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }



}
