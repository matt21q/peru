package net.matt.perumod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.custom.CuyPeruEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CuyPeruRenderer extends MobRenderer<CuyPeruEntity, CuyPeruanoModel<CuyPeruEntity>> {
    private static final float SHADOW_SIZE = 0.4f;

    public CuyPeruRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CuyPeruanoModel<>(pContext.bakeLayer(ModModelLayers.CUY_PERUANO_LAYER)), 1f);
        this.shadowRadius = SHADOW_SIZE;
    }

    @Override
    public ResourceLocation getTextureLocation(CuyPeruEntity pEntity) {
        return new ResourceLocation(PeruMod.MOD_ID, "textures/entity/cuy_peruano.png");
    }

    @Override
    public void render(CuyPeruEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
