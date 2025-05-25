package net.matt.perumod.block.custom.mortar;

import net.matt.perumod.PeruMod;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
  public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;
  public static final RegistryObject<RecipeType<MortarRecipe>> MORTAR;

  public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
    return new RecipeType<T>() {
      public String toString() {
        return PeruMod.MOD_ID +  ":" + identifier;
      }
    };
  }

  static {
    RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, PeruMod.MOD_ID);
    MORTAR = RECIPE_TYPES.register("mortar", () -> registerRecipeType("mortar"));
  }
}
