package net.matt.perumod.block.entity;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes  {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PeruMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MudStoveBlockEntity>> MUD_STOVE = TILES.register("mud_stove",
            () -> BlockEntityType.Builder.of(MudStoveBlockEntity::new, ModBlocks.MUD_STOVE.get()).build(null));


    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            TILES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.LEMON_SIGN.get(), ModBlocks.LEMON_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            TILES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.LEMON_HANGING_SIGN.get(), ModBlocks.LEMON_WALL_HANGING_SIGN.get()).build(null));
}
