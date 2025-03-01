package net.matt.perumod.client.event;


import net.matt.perumod.PeruMod;
import net.matt.perumod.block.entity.PeruBlockEntityTypes;
import net.matt.perumod.client.renderer.MudStoveRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PeruMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(PeruBlockEntityTypes.MUD_STOVE.get(), MudStoveRenderer::new);
    }
}
