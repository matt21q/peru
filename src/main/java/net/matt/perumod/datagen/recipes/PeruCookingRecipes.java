package net.matt.perumod.datagen.recipes;

import net.matt.perumod.item.ModItems;
import net.matt.perumod.util.ModTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import java.util.function.Consumer;

import static vectorwing.farmersdelight.data.recipe.CookingRecipes.*;

public class PeruCookingRecipes {
    public PeruCookingRecipes() {
    }
    public static void register(Consumer<FinishedRecipe> consumer) {
        cookMinecraftSoups(consumer);
    }
    private static void cookMinecraftSoups(Consumer<FinishedRecipe> consumer) {
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SALCHIPAPA.get(), 1, FAST_COOKING, SMALL_EXP, Items.BOWL)
                .addIngredient(ModItems.SALT.get())
                .addIngredient(ModItems.FRENCH_FRIES.get())
                .addIngredient(ModItems.COOKED_SAUSAGE.get())
                .unlockedByAnyIngredient(ModItems.FRENCH_FRIES.get(), ModItems.COOKED_SAUSAGE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "perusdelight:cooking/salchipapa");



        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CEVICHE.get(), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.BAKED_SWEET_POTATO.get())
                .addIngredient(ModTags.Items.FISH_CEVICHE)
                .addIngredient(ModItems.LEMON_SLICE.get())
                .addIngredient(ModItems.PURPLE_ONION.get())
                .addIngredient(ModItems.CORN_KERNELS.get())
                .addIngredient(ModItems.SALT.get())
                .unlockedByAnyIngredient(ModItems.LEMON.get(), ModItems.PURPLE_ONION.get(), ModItems.BAKED_SWEET_POTATO.get(), ModItems.CORN_KERNELS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(consumer, "perusdelight:cooking/ceviche");
    }
}
