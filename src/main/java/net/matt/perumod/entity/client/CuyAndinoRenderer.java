package net.matt.perumod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.custom.CuyAndinoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CuyAndinoRenderer extends MobRenderer<CuyAndinoEntity, CuyAndinoModel<CuyAndinoEntity>> {
private static final float SHADOW_SIZE = 0.3f;

public CuyAndinoRenderer(EntityRendererProvider.Context pContext) {
    super(pContext, new CuyAndinoModel<>(pContext.bakeLayer(ModModelLayers.CUY_ANDINO_LAYER)), 1f);
    this.shadowRadius = SHADOW_SIZE;
}

@Override
public ResourceLocation getTextureLocation(CuyAndinoEntity pEntity) {
    return new ResourceLocation(PeruMod.MOD_ID, "textures/entity/cuy_andino.png");
}

@Override
public void render(CuyAndinoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                   MultiBufferSource pBuffer, int pPackedLight) {
    if (pEntity.isBaby()) {
        pMatrixStack.scale(0.5f, 0.5f, 0.5f);
    }

    super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
