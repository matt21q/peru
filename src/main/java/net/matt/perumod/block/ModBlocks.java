package net.matt.perumod.block;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.custom.*;
import net.matt.perumod.block.custom.feast.FriedCuyBlock;
import net.matt.perumod.item.ModItems;
import net.matt.perumod.util.ModWoodTypes;
import net.matt.perumod.worldgen.tree.LemonTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;


public class ModBlocks {
    public static final RegistryObject<Block> MUD_STOVE;
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PeruMod.MOD_ID);


    public static final RegistryObject<Block> SALT_SAND_ORE = registerBlock("salt_sand_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.SAND)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));


    public static final RegistryObject<Block> SALT_ORE = registerBlock("salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    public static final RegistryObject<Block> LEMON_LEAVES = registerBlock("lemon_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> LEMON_SAPLING = registerBlock("lemon_sapling",
            () -> new SaplingBlock(new LemonTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));


    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
            () -> new CornCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));







    //wild crops
    public static final RegistryObject<Block> WILD_VARIANTS_POTATOES = BLOCKS.register("wild_variants_potatoes",
            () -> new WildCropBlock(MobEffects.CONFUSION, 8, Block.Properties.copy(Blocks.TALL_GRASS)));

    public static final RegistryObject<Block> WILD_PURPLE_ONIONS = BLOCKS.register("wild_purple_onions",
            () -> new WildCropBlock(MobEffects.DIG_SLOWDOWN, 8, Block.Properties.copy(Blocks.TALL_GRASS)));







    public static final RegistryObject<Block> LEMON_LOG = registerBlock("lemon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> LEMON_WOOD = registerBlock("lemon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_LEMON_LOG = registerBlock("stripped_lemon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_LEMON_WOOD = registerBlock("stripped_lemon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> LEMON_PLANKS = registerBlock("lemon_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F,3.0F)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
//feast
    public static final RegistryObject<Block> FRIED_CUY_BLOCK = BLOCKS.register("fried_cuy_block",
             () -> new FriedCuyBlock(Block.Properties.copy(Blocks.CAKE), ModItems.BAKED_SWEET_POTATO, true));


    public static final RegistryObject<Block> LEMON_LEAVES_WITH_LEMONS = registerBlock("lemon_leaves_with_lemons",
            () -> new CustomLemonLeave(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));

    public static final RegistryObject<Block> LEMON_SIGN = BLOCKS.register("lemon_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.LEMON));
    public static final RegistryObject<Block> LEMON_WALL_SIGN = BLOCKS.register("lemon_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.LEMON));

    public static final RegistryObject<Block> LEMON_HANGING_SIGN = BLOCKS.register("lemon_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.LEMON));
    public static final RegistryObject<Block> LEMON_WALL_HANGING_SIGN = BLOCKS.register("lemon_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.LEMON));

    public static final RegistryObject<Block> LEMON_TREE_DOOR = registerBlock("lemon_tree_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> LEMON_TREE_TRAPDOOR = registerBlock("lemon_tree_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).noOcclusion(), BlockSetType.OAK));

    public static final RegistryObject<Block> LEMON_TREE_STAIRS = registerBlock("lemon_tree_stairs",
            () -> new StairBlock(() -> ModBlocks.LEMON_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LEMON_TREE_SLAB = registerBlock("lemon_tree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LEMON_TREE_BUTTON = registerBlock("lemon_tree_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.WOOD),
                    BlockSetType.IRON, 10, true));

    public static final RegistryObject<Block> LEMON_TREE_PRESSURE_PLATE = registerBlock("lemon_tree_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD),
                    BlockSetType.OAK));

    public static final RegistryObject<Block> LEMON_TREE_FENCE = registerBlock("lemon_tree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LEMON_TREE_FENCE_GATE = registerBlock("lemon_tree_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }





    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> {
            return (Boolean) state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }
    static {
        MUD_STOVE = BLOCKS.register("mud_stove", () ->

        {
            return new MudStoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS));
        });
    }
}
