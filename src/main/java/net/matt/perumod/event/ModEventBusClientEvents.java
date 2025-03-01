package net.matt.perumod.event;

import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.client.CuyPeruanoModel;
import net.matt.perumod.entity.client.ModModelLayers;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PeruMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.CUY_PERUANO_LAYER, CuyPeruanoModel::createBodyLayer);
    }
}
