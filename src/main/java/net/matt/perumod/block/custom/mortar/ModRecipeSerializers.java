package net.matt.perumod.block.custom.mortar;

import net.matt.perumod.PeruMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
  public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
  public static final RegistryObject<RecipeSerializer<?>> MORTAR;

  static {
    RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PeruMod.MOD_ID);
    MORTAR = RECIPE_SERIALIZERS.register("mortar", MortarRecipe.Serializer::new);
  }
}
