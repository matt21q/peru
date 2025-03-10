package net.matt.perumod.datagen;

import net.matt.perumod.PeruMod;

import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModItems;


import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, PeruMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ModTags.Items.FISH_CEVICHE)
                .add(ModItems.COD_SLICE.get())
                .add(ModItems.SALMON_SLICE.get());
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.LEMON_LOG.get().asItem())
                .add(ModBlocks.LEMON_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_LEMON_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_LEMON_WOOD.get().asItem());
        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.LEMON_PLANKS.get().asItem());
        this.tag(ItemTags.MUSIC_DISCS)
                .add(net.matt.perumod.item.ModItems.THE_CONDOR_PASSES_MUSIC_DISC.get());
    }

}
