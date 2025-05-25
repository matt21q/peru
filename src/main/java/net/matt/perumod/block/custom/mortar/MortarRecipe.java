package net.matt.perumod.block.custom.mortar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.matt.perumod.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MortarRecipe implements Recipe<RecipeWrapper> {
  private final ResourceLocation id;
  private final String group;
  private final NonNullList<Ingredient> inputItems;
  private final ItemStack output;
  private final ItemStack container;
  private final float experience;
  private final int processTime;

  public MortarRecipe(ResourceLocation id, String group, NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int processTime) {
    this.id = id;
    this.group = group;
    this.inputItems = inputItems;
    this.output = output;
    if (!container.isEmpty()) {
      this.container = container;
    } else if (!output.getCraftingRemainingItem().isEmpty()) {
      this.container = output.getCraftingRemainingItem();
    } else {
      this.container = ItemStack.EMPTY;
    }

    this.experience = experience;
    this.processTime = processTime;
  }

  public ResourceLocation getId() {
    return this.id;
  }

  public String getGroup() {
    return this.group;
  }

  public NonNullList<Ingredient> getIngredients() {
    return this.inputItems;
  }

  public ItemStack getResultItem(RegistryAccess access) {
    return this.output;
  }

  public ItemStack getOutputContainer() {
    return this.container;
  }

  public ItemStack assemble(RecipeWrapper inv, RegistryAccess access) {
    return this.output.copy();
  }

  public float getExperience() {
    return this.experience;
  }

  public int getProcessTime() {
    return this.processTime;
  }

  public boolean matches(RecipeWrapper inv, Level level) {
    List<ItemStack> inputs = new ArrayList();
    int i = 0;

    for(int j = 0; j < 6; ++j) {
      ItemStack itemstack = inv.getItem(j);
      if (!itemstack.isEmpty()) {
        ++i;
        inputs.add(itemstack);
      }
    }

    return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null;
  }

  public boolean canCraftInDimensions(int width, int height) {
    return width * height >= this.inputItems.size();
  }

  public RecipeSerializer<?> getSerializer() {
    return ModRecipeSerializers.MORTAR.get();
  }

  public RecipeType<?> getType() {
    return ModRecipeTypes.MORTAR.get();
  }

  public ItemStack getToastSymbol() {
    return new ItemStack(ModItems.MORTAR_BLOCK.get());
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o != null && this.getClass() == o.getClass()) {
      MortarRecipe that = (MortarRecipe)o;
      if (Float.compare(that.getExperience(), this.getExperience()) != 0) {
        return false;
      } else if (this.getProcessTime() != that.getProcessTime()) {
        return false;
      } else if (!this.getId().equals(that.getId())) {
        return false;
      } else if (!this.getGroup().equals(that.getGroup())) {
        return false;
      }  else if (!this.inputItems.equals(that.inputItems)) {
        return false;
      } else {
        return this.output.equals(that.output) && this.container.equals(that.container);
      }
    } else {
      return false;
    }
  }

  public int hashCode() {
    int result = this.getId().hashCode();
    result = 31 * result + this.getGroup().hashCode();
    result = 31 * result + 0;
    result = 31 * result + this.inputItems.hashCode();
    result = 31 * result + this.output.hashCode();
    result = 31 * result + this.container.hashCode();
    result = 31 * result + (this.getExperience() != 0.0F ? Float.floatToIntBits(this.getExperience()) : 0);
    result = 31 * result + this.getProcessTime();
    return result;
  }

  public static class Serializer implements RecipeSerializer<MortarRecipe> {
    public MortarRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
      String groupIn = GsonHelper.getAsString(json, "group", "");
      NonNullList<Ingredient> inputItemsIn = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
      if (inputItemsIn.isEmpty()) {
        throw new JsonParseException("No ingredients for mortar recipe");
      } else if (inputItemsIn.size() > 6) {
        throw new JsonParseException("Too many ingredients for mortar recipe! The max is 6");
      } else {
        ItemStack outputIn = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
        ItemStack container = GsonHelper.isValidNode(json, "container") ? CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "container"), true) : ItemStack.EMPTY;
        float experienceIn = GsonHelper.getAsFloat(json, "experience", 0.0F);
        int processTimeIn = GsonHelper.getAsInt(json, "processTime", 200);
        return new MortarRecipe(recipeId, groupIn, inputItemsIn, outputIn, container, experienceIn, processTimeIn);
      }
    }

    private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
      NonNullList<Ingredient> nonnulllist = NonNullList.create();

      for(int i = 0; i < ingredientArray.size(); ++i) {
        Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
        if (!ingredient.isEmpty()) {
          nonnulllist.add(ingredient);
        }
      }

      return nonnulllist;
    }

    @Nullable
    public MortarRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
      String groupIn = buffer.readUtf();
      int i = buffer.readVarInt();
      NonNullList<Ingredient> inputItemsIn = NonNullList.withSize(i, Ingredient.EMPTY);

      for(int j = 0; j < inputItemsIn.size(); ++j) {
        inputItemsIn.set(j, Ingredient.fromNetwork(buffer));
      }

      ItemStack outputIn = buffer.readItem();
      ItemStack container = buffer.readItem();
      float experienceIn = buffer.readFloat();
      int processTimeIn = buffer.readVarInt();
      return new MortarRecipe(recipeId, groupIn,  inputItemsIn, outputIn, container, experienceIn, processTimeIn);
    }

    public void toNetwork(FriendlyByteBuf buffer, MortarRecipe recipe) {
      buffer.writeUtf(recipe.group);
      buffer.writeVarInt(recipe.inputItems.size());

      for(Ingredient ingredient : recipe.inputItems) {
        ingredient.toNetwork(buffer);
      }

      buffer.writeItem(recipe.output);
      buffer.writeItem(recipe.container);
      buffer.writeFloat(recipe.experience);
      buffer.writeVarInt(recipe.processTime);
    }
  }
}
