package net.matt.perumod.event;

import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.ModEntities;
import net.matt.perumod.entity.custom.CuyPeruEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PeruMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CUY_PERUANO.get(), CuyPeruEntity.createAttributes().build());
    }
}
