package net.matt.perumod.datagen.recipes;

import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.block.custom.mortar.MortarBlockRecipeBuilder;
import net.matt.perumod.item.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class PeruMortarRecipes {
  public PeruMortarRecipes() {
  }
  public static void register(Consumer<FinishedRecipe> consumer) {
    mortarRecipes(consumer);
  }
  private static void mortarRecipes(Consumer<FinishedRecipe> consumer) {
    MortarBlockRecipeBuilder.mortarRecipe(ModItems.SALT.get(), 5, MortarBlockRecipeBuilder.FAST_PROCESS, MortarBlockRecipeBuilder.SMALL_EXP, Items.BOWL)
            .addIngredient(ModBlocks.SALT_ORE.get())
            .unlockedByAnyIngredient(ModBlocks.SALT_ORE.get())
            .build(consumer, "perusdelight:mortar/salt_example");

  }
}
