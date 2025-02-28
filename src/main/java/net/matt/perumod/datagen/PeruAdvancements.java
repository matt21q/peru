package net.matt.perumod.datagen;

import net.matt.perumod.advancment.PeruAdvancementGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PeruAdvancements extends ForgeAdvancementProvider {

    public PeruAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new PeruAdvancementGenerator()));
    }
}