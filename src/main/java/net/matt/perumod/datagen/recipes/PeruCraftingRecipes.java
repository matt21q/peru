package net.matt.perumod.datagen.recipes;

import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEMON_TREE_DOOR.get())
                .pattern("SS ")
                .pattern("SS ")
                .pattern("SS ")
                .define('S', ModBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_door_lemon", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEMON_PLANKS.get()))
                .save(consumer, new ResourceLocation("perusdelight", "lemondoor"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEMON_TREE_FENCE.get())
                .pattern("SXS")
                .pattern("SXS")
                .define('S', ModBlocks.LEMON_PLANKS.get())
                .define('X', Items.STICK)
                .unlockedBy("has_fence_lemon", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEMON_PLANKS.get()))
                .save(consumer, new ResourceLocation("perusdelight", "lemonfence"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEMON_TREE_TRAPDOOR.get())
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_trapdoor_lemon", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEMON_PLANKS.get()))
                .save(consumer, new ResourceLocation("perusdelight", "lemontrapdoor"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LEMON_TREE_BUTTON.get(), 1)
                .requires(ModBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_lemon_button", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEMON_PLANKS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LEMON_PLANKS.get(), 4)
                .requires(ModBlocks.LEMON_LOG.get())
                .unlockedBy("has_lemon_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEMON_LOG.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.LEMONADE.get())
                .pattern(" #X")
                .pattern(" S ")
                .define('X', ModItems.LEMON_SLICE.get())
                .define('S', Items.POTION)
                .define('#', Items.SUGAR)
                .unlockedBy("has_lemonade", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEMONADE.get()))
                .save(consumer, new ResourceLocation("perusdelight", "lemonade"));
    }
}
