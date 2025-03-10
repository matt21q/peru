package net.matt.perumod.datagen;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.block.custom.CornCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PeruMod.MOD_ID, exFileHelper);
    }
    private static final int DEFAULT_ANGLE_OFFSET = 180;
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SALT_ORE);
        blockWithItem(ModBlocks.SALT_SAND_ORE);
        leavesBlock(ModBlocks.LEMON_LEAVES);
        saplingBlock(ModBlocks.LEMON_SAPLING);
        makeCornCrop(((CropBlock) ModBlocks.CORN_CROP.get()), "corn_crop", "corn_crop");
        logBlock(((RotatedPillarBlock) ModBlocks.LEMON_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.LEMON_WOOD.get()), blockTexture(ModBlocks.LEMON_LOG.get()), blockTexture(ModBlocks.LEMON_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_LEMON_LOG.get()), blockTexture(ModBlocks.STRIPPED_LEMON_LOG.get()),
                new ResourceLocation(PeruMod.MOD_ID, "block/stripped_lemon_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_LEMON_WOOD.get()), blockTexture(ModBlocks.STRIPPED_LEMON_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_LEMON_LOG.get()));

        blockItem(ModBlocks.LEMON_LOG);
        blockItem(ModBlocks.LEMON_WOOD);
        blockItem(ModBlocks.STRIPPED_LEMON_LOG);
        blockItem(ModBlocks.STRIPPED_LEMON_WOOD);

        blockWithItem(ModBlocks.LEMON_PLANKS);

        signBlock(((StandingSignBlock) ModBlocks.LEMON_SIGN.get()), ((WallSignBlock) ModBlocks.LEMON_WALL_SIGN.get()),
                blockTexture(ModBlocks.LEMON_PLANKS.get()));

        hangingSignBlock(ModBlocks.LEMON_HANGING_SIGN.get(), ModBlocks.LEMON_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.LEMON_PLANKS.get()));
        simpleBlockWithItem(ModBlocks.LEMON_LEAVES_WITH_LEMONS.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/lemon_leaves_with_lemons")));



        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.LEMON_TREE_TRAPDOOR.get()), modLoc("block/lemon_tree_trapdoor"), true, "cutout");

        stairsBlock(((StairBlock) ModBlocks.LEMON_TREE_STAIRS.get()), blockTexture(ModBlocks.LEMON_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.LEMON_TREE_SLAB.get()), blockTexture(ModBlocks.LEMON_PLANKS.get()), blockTexture(ModBlocks.LEMON_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.LEMON_TREE_BUTTON.get()), blockTexture(ModBlocks.LEMON_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.LEMON_TREE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.LEMON_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.LEMON_TREE_FENCE.get()), blockTexture(ModBlocks.LEMON_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.LEMON_TREE_FENCE_GATE.get()), blockTexture(ModBlocks.LEMON_PLANKS.get()));

        this.wildCropBlock(ModBlocks.WILD_VARIANTS_POTATOES.get());
        this.wildCropBlock(ModBlocks.WILD_PURPLE_ONIONS.get());

        this.feastBlock((FeastBlock) ModBlocks.FRIED_CUY_BLOCK.get());




    }






    public void feastBlock(FeastBlock block) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    IntegerProperty servingsProperty = block.getServingsProperty();
                    int servings = state.getValue(servingsProperty);

                    String suffix = "_stage" + (block.getMaxServings() - servings);

                    if (servings == 0) {
                        suffix = block.hasLeftovers ? "_leftover" : "_stage" + (servingsProperty.getPossibleValues().toArray().length - 2);
                    }

                    return ConfiguredModel.builder()
                            .modelFile(existingModel(blockName(block) + suffix))
                            .rotationY(((int) state.getValue(FeastBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
                            .build();
                });
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }
    public void wildCropBlock(Block block) {
        this.wildCropBlock(block, false);
    }

    public void wildCropBlock(Block block, boolean isBushCrop) {
        if (isBushCrop) {
            this.simpleBlock(block, models().singleTexture(blockName(block), resourceBlock("bush_crop"), "crop", resourceBlock(blockName(block))).renderType("cutout"));
        } else {
            this.simpleBlock(block, models().cross(blockName(block), resourceBlock(blockName(block))).renderType("cutout"));
        }
    }
    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(PeruMod.MOD_ID, "block/" + path);
    }

    private String blockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(PeruMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    public void makeCornCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CornCropBlock) block).getAgeProperty()),
                new ResourceLocation(PeruMod.MOD_ID, "block/" + textureName + state.getValue(((CornCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }



    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
