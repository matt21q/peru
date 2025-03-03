package net.matt.perumod.entity.client.baby;

import com.mojang.blaze3d.vertex.PoseStack;
import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.client.ModModelLayers;
import net.matt.perumod.entity.custom.baby.BBCuyAndinoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BBCuyAndinoRenderer extends MobRenderer<BBCuyAndinoEntity, BBCuyAndinoModel<BBCuyAndinoEntity>> {
    private static final float SHADOW_SIZE = 0.3f;

    public BBCuyAndinoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BBCuyAndinoModel<>(pContext.bakeLayer(ModModelLayers.BB_CUY_ANDINO_LAYER)), 0.5f);
        this.shadowRadius = SHADOW_SIZE;
    }

    @Override
    public ResourceLocation getTextureLocation(BBCuyAndinoEntity pEntity) {
        return new ResourceLocation(PeruMod.MOD_ID, "textures/entity/bb_cuy_andino.png");
    }

    @Override
    public void render(BBCuyAndinoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}

