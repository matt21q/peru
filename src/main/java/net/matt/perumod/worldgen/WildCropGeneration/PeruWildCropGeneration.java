package net.matt.perumod.worldgen.WildCropGeneration;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PeruWildCropGeneration {
    public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_PURPLE_ONIONS;
    public static ResourceKey<PlacedFeature> PATCH_WILD_PURPLE_ONIONS;

    public void WildCropGeneration() {
    }

    public static void load() {
    }
    static {
        FEATURE_PATCH_WILD_PURPLE_ONIONS = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("perusdelight", "patch_wild_purple_onions"));
    }
}
