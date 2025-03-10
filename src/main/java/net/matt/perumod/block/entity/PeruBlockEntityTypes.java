package net.matt.perumod.block.entity;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PeruBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PeruMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MudStoveBlockEntity>> MUD_STOVE = TILES.register("mud_stove",
            () -> BlockEntityType.Builder.of(MudStoveBlockEntity::new, ModBlocks.MUD_STOVE.get()).build(null));
}
