package net.matt.perumod.event;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.entity.ModBlockEntityTypes;
import net.matt.perumod.entity.client.CuyAndinoModel;
import net.matt.perumod.entity.client.CuyIntiModel;
import net.matt.perumod.entity.client.CuyPeruanoModel;
import net.matt.perumod.entity.client.ModModelLayers;

import net.matt.perumod.entity.client.baby.BBCuyPeruModel;
import net.matt.perumod.util.ModWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PeruMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.BB_CUY_ANDINO_LAYER, BBCuyPeruModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BB_CUY_INTI_LAYER, BBCuyPeruModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BB_CUY_PERUANO_LAYER, BBCuyPeruModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CUY_PERUANO_LAYER, CuyPeruanoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CUY_ANDINO_LAYER, CuyAndinoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CUY_INTI_LAYER, CuyIntiModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        Sheets.addWoodType(ModWoodTypes.LEMON);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
