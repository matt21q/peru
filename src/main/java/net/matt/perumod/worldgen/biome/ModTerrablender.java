package net.matt.perumod.worldgen.biome;

import net.matt.perumod.PeruMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(PeruMod.MOD_ID, "overworld"), 10));
    }
}
