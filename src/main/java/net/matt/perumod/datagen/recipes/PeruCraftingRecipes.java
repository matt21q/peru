package net.matt.perumod.datagen.recipes;

import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

import java.util.function.Consumer;


public class PeruCraftingRecipes {
    public PeruCraftingRecipes() {
    }

    public static void register(Consumer<FinishedRecipe> consumer) {
        recipesBlocks(consumer);
        SpecialRecipeBuilder.special( ModRecipeSerializers.FOOD_SERVING.get()).save(consumer, "food_serving");
    }
    private static void recipesBlocks(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_SAND_ORE.get())
                .pattern(" S ")
                .pattern("SXS")
                .pattern(" S ")
                .define('S', ModItems.SALT.get())
                .define('X', Blocks.SAND)
                .unlockedBy("has_sand_sand", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SALT.get()))
                .save(consumer, new ResourceLocation("perusdelight", "salt_sand"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LEMON_PLANKS.get(), 4)
                .requires(ModBlocks.LEMON_LOG.get())
                .unlockedBy("has_lemon_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEMON_LOG.get()))
                .save(consumer);
    }
}
