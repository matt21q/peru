package net.matt.perumod.worldgen;

import com.mojang.serialization.Codec;
import net.matt.perumod.PeruMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SALT_ORE = registerKey("add_salt_ore");
    public static final ResourceKey<BiomeModifier> ADD_SALT_SAND_ORE = registerKey("add_salt_sand_ore");
    public static final ResourceKey<BiomeModifier> ADD_WILD_VARIANTS_POTATOES = registerKey("add_wild_variants_potatoes");
    public static final ResourceKey<BiomeModifier> ADD_WILD_PURPLE_ONIONS = registerKey("add_wild_purple_onions");
    public static final ResourceKey<BiomeModifier> ADD_TREE_LEMON = registerKey("add_tree_lemon");




    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SALT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DRIPSTONE_CAVES)), // Convert to HolderSet
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_ORE_PLACED_KEY)), // Ensure this is a HolderSet
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SALT_SAND_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BEACH)), // Convert to HolderSet
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_SAND_ORE_PLACED_KEY)), // Ensure this is a HolderSet
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TREE_LEMON, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEMON_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WILD_PURPLE_ONIONS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.WILD_PURPLE_ONIONS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_WILD_VARIANTS_POTATOES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.WILD_VARIANTS_POTATOES_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }












    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(PeruMod.MOD_ID, name));
    }
}
