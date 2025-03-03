package net.matt.perumod.event;

import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.ModEntities;
import net.matt.perumod.entity.custom.CuyAndinoEntity;
import net.matt.perumod.entity.custom.CuyIntiEntity;
import net.matt.perumod.entity.custom.CuyPeruEntity;
import net.matt.perumod.entity.custom.baby.BBCuyPeruEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PeruMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BB_CUY_INTI.get(), BBCuyPeruEntity.createAttributes().build());
        event.put(ModEntities.BB_CUY_ANDINO.get(), BBCuyPeruEntity.createAttributes().build());
        event.put(ModEntities.BB_CUY_PERUANO.get(), BBCuyPeruEntity.createAttributes().build());
        event.put(ModEntities.CUY_INTI.get(), CuyIntiEntity.createAttributes().build());
        event.put(ModEntities.CUY_PERUANO.get(), CuyPeruEntity.createAttributes().build());
        event.put(ModEntities.CUY_ANDINO.get(), CuyAndinoEntity.createAttributes().build());
    }
}
