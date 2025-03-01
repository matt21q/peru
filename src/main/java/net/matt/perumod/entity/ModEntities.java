package net.matt.perumod.entity;

import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.custom.CuyPeruEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PeruMod.MOD_ID);

    public static final RegistryObject<EntityType<CuyPeruEntity>> CUY_PERUANO =
            ENTITY_TYPES.register("cuy_peruano", () -> EntityType.Builder.of(CuyPeruEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.6f).build("cuy_peruano"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
