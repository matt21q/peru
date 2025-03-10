package net.matt.perumod.datagen;

import net.matt.perumod.datagen.recipes.PeruCookingRecipes;
import net.matt.perumod.datagen.recipes.PeruCraftingRecipes;
import net.matt.perumod.datagen.recipes.PeruCuttingRecipes;
import net.matt.perumod.datagen.recipes.PeruSmeltingRecipes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;


import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider{

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        PeruCookingRecipes.register(consumer);
        PeruCraftingRecipes.register(consumer);
        PeruCuttingRecipes.register(consumer);
        PeruSmeltingRecipes.register(consumer);
    }

}
