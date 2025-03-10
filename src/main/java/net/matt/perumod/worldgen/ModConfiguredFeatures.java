package net.matt.perumod.worldgen;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;


public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SALT_ORE_KEY = registerKey("salt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SALT_SAND_ORE_KEY = registerKey("salt_sand_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_KEY = registerKey("lemon");



    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest dripstoneReplaceable = new TagMatchTest(BlockTags.DRIPSTONE_REPLACEABLE);
        RuleTest sandReplaceable = new TagMatchTest(BlockTags.SAND);


        register(context, OVERWORLD_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(dripstoneReplaceable,
                ModBlocks.SALT_ORE.get().defaultBlockState(), 11));
        register(context, OVERWORLD_SALT_SAND_ORE_KEY, Feature.ORE, new OreConfiguration(sandReplaceable,
                ModBlocks.SALT_SAND_ORE.get().defaultBlockState(), 10));

        register(context, LEMON_KEY, Feature.TREE,(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.LEMON_LOG.get()),
                new BendingTrunkPlacer(4, 2, 0, 3, UniformInt.of(1, 2)),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.LEMON_LEAVES.get().defaultBlockState(), 3)
                        .add(ModBlocks.LEMON_LEAVES_WITH_LEMONS.get().defaultBlockState(), 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3),
                ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 0, 1)))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).forceDirt().build());
    }



    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(PeruMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
