package net.matt.perumod.datagen;


import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PeruMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.registerFarmersDelightTags();
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SALT_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.SALT_SAND_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.LEMON_PLANKS.get())
                .add(ModBlocks.LEMON_WOOD.get())
                .add(ModBlocks.LEMON_LOG.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.LEMON_LEAVES.get())
                .add(ModBlocks.LEMON_LEAVES_WITH_LEMONS.get());
        this.registerBlockMineables();


        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.LEMON_LOG.get())
                .add(ModBlocks.LEMON_WOOD.get())
                .add(ModBlocks.STRIPPED_LEMON_LOG.get())
                .add(ModBlocks.STRIPPED_LEMON_WOOD.get());
        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.LEMON_PLANKS.get());
    }

    private void registerFarmersDelightTags() {
        tag(ModTags.HEAT_SOURCES).add(
                ModBlocks.MUD_STOVE.get())
        ;
    }
    protected void registerBlockMineables() {
       tag(ModTags.MINEABLE_WITH_KNIFE)
               .add(ModBlocks.FRIED_CUY_BLOCK.get());
    }
}
