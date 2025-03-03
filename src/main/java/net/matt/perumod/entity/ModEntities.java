package net.matt.perumod.entity;

import net.matt.perumod.PeruMod;
import net.matt.perumod.entity.custom.CuyAndinoEntity;
import net.matt.perumod.entity.custom.CuyIntiEntity;
import net.matt.perumod.entity.custom.CuyPeruEntity;
import net.matt.perumod.entity.custom.baby.BBCuyAndinoEntity;
import net.matt.perumod.entity.custom.baby.BBCuyIntiEntity;
import net.matt.perumod.entity.custom.baby.BBCuyPeruEntity;
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

    public static final RegistryObject<EntityType<CuyAndinoEntity>> CUY_ANDINO =
            ENTITY_TYPES.register("cuy_andino", () -> EntityType.Builder.of(CuyAndinoEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.6f).build("cuy_andino"));

    public static final RegistryObject<EntityType<CuyIntiEntity>> CUY_INTI =
            ENTITY_TYPES.register("cuy_inti", () -> EntityType.Builder.of(CuyIntiEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.6f).build("cuy_inti"));


    public static final RegistryObject<EntityType<BBCuyPeruEntity>> BB_CUY_PERUANO =
            ENTITY_TYPES.register("bb_cuy_peruano", () -> EntityType.Builder.of(BBCuyPeruEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.6f).build("bb_cuy_peruano"));

    public static final RegistryObject<EntityType<BBCuyAndinoEntity>> BB_CUY_ANDINO =
            ENTITY_TYPES.register("bb_cuy_andino", () -> EntityType.Builder.of(BBCuyAndinoEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.6f).build("bb_cuy_andino"));

    public static final RegistryObject<EntityType<BBCuyIntiEntity>> BB_CUY_INTI =
            ENTITY_TYPES.register("bb_cuy_inti", () -> EntityType.Builder.of(BBCuyIntiEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.6f).build("bb_cuy_inti"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
