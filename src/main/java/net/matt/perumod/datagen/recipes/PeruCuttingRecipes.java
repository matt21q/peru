package net.matt.perumod.datagen.recipes;

import net.matt.perumod.item.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class PeruCuttingRecipes {
    public PeruCuttingRecipes() {
    }
    public static void register(Consumer<FinishedRecipe> consumer) {
        cuttingVegetables(consumer);
    }
    private static void cuttingVegetables(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAKED_POTATO),
                        Ingredient.of(ForgeTags.TOOLS_KNIVES),
                        ModItems.FRENCH_FRIES.get(), 4)
                .addResultWithChance(ModItems.FRENCH_FRIES.get(), 0.25F, 1)
                .build(consumer, "perusdelight:cutting/french_fries");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.LEMON.get()),
                        Ingredient.of(ForgeTags.TOOLS_KNIVES),
                        ModItems.LEMON_SLICE.get(), 4)
                .build(consumer, "perusdelight:cutting/lemon_slice");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.PORKCHOP),
                        Ingredient.of(Items.SHEARS),
                        ModItems.SAUSAGE.get(), 3)
                .build(consumer, "perusdelight:cutting/sausage_slice");
    }
}
