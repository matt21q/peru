package net.matt.perumod.datagen.recipes;

import net.matt.perumod.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.ItemStack;
import java.util.Arrays;
import java.util.function.Consumer;

public class PeruSmeltingRecipes {
    public PeruSmeltingRecipes() {
    }

    public static void register(Consumer<FinishedRecipe> consumer) {
        foodSmeltingRecipes("sausage", Ingredient.of(ModItems.SAUSAGE.get()), ModItems.COOKED_SAUSAGE.get(), 0.35F, consumer);
        foodSmeltingRecipes("sweet_potato", Ingredient.of(ModItems.SWEET_POTATO.get()), ModItems.BAKED_SWEET_POTATO.get(), 0.35F, consumer);

    }

    private static void foodSmeltingRecipes(String name, Ingredient ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String namePrefix = (new ResourceLocation("perusdelight", name)).toString();

        ItemLike[] items = Arrays.stream(ingredient.getItems())
                .map(ItemStack::getItem)
                .toArray(ItemLike[]::new);


        SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.FOOD, result, experience, 200)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items))
                .save(consumer, namePrefix + "_cooking");
        SimpleCookingRecipeBuilder.campfireCooking(ingredient, RecipeCategory.FOOD, result, experience, 600)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items))
                .save(consumer, namePrefix + "_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smoking(ingredient, RecipeCategory.FOOD, result, experience, 100)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items))
                .save(consumer, namePrefix + "_from_smoking");


    }


}