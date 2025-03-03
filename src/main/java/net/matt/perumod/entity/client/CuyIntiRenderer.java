package net.matt.perumod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.custom.CuyIntiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CuyIntiRenderer  extends MobRenderer<CuyIntiEntity, CuyIntiModel<CuyIntiEntity>> {
    private static final float SHADOW_SIZE = 0.3f;

    public CuyIntiRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CuyIntiModel<>(pContext.bakeLayer(ModModelLayers.CUY_INTI_LAYER)), 1f);
        this.shadowRadius = SHADOW_SIZE;
    }

    @Override
    public ResourceLocation getTextureLocation(CuyIntiEntity pEntity) {
        return new ResourceLocation(PeruMod.MOD_ID, "textures/entity/cuy_inti.png");
    }

    @Override
    public void render(CuyIntiEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
