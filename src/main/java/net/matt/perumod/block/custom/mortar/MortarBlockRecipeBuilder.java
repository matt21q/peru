package net.matt.perumod.block.custom.mortar;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.matt.perumod.PeruMod;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MortarBlockRecipeBuilder {
  private final List<Ingredient> ingredients = Lists.newArrayList();
  private final Item result;
  private final int count;
  private final int processTime;
  private final float experience;
  private final Item container;
  private final Advancement.Builder advancement = Advancement.Builder.advancement();

  public static final int FAST_PROCESS = 100;
  public static final int NORMAL_PROCESS = 200;
  public static final int SLOW_PROCESS = 400;
  public static final float SMALL_EXP = 0.35F;
  public static final float MEDIUM_EXP = 1.0F;
  public static final float LARGE_EXP = 2.0F;

  private MortarBlockRecipeBuilder(ItemLike resultIn, int count, int processTime, float experience, @Nullable ItemLike container) {
    this.result = resultIn.asItem();
    this.count = count;
    this.processTime = processTime;
    this.experience = experience;
    this.container = container != null ? container.asItem() : null;
  }

  public static MortarBlockRecipeBuilder mortarRecipe(ItemLike mainResult, int count, int processTime, float experience) {
    return new MortarBlockRecipeBuilder(mainResult, count, processTime, experience, (ItemLike)null);
  }

  public static MortarBlockRecipeBuilder mortarRecipe(ItemLike mainResult, int count, int processTime, float experience, ItemLike container) {
    return new MortarBlockRecipeBuilder(mainResult, count, processTime, experience, container);
  }

  public MortarBlockRecipeBuilder addIngredient(TagKey<Item> tagIn) {
    return this.addIngredient(Ingredient.of(tagIn));
  }

  public MortarBlockRecipeBuilder addIngredient(ItemLike itemIn) {
    return this.addIngredient((ItemLike)itemIn, 1);
  }

  public MortarBlockRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
    for(int i = 0; i < quantity; ++i) {
      this.addIngredient(Ingredient.of(new ItemLike[]{itemIn}));
    }

    return this;
  }

  public MortarBlockRecipeBuilder addIngredient(Ingredient ingredientIn) {
    return this.addIngredient((Ingredient)ingredientIn, 1);
  }

  public MortarBlockRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
    for(int i = 0; i < quantity; ++i) {
      this.ingredients.add(ingredientIn);
    }

    return this;
  }

  public MortarBlockRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
    this.advancement.addCriterion(criterionName, criterionTrigger);
    return this;
  }

  public MortarBlockRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
    return this.unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
  }

  public MortarBlockRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
    this.advancement.addCriterion("has_any_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemPredicate[]{net.minecraft.advancements.critereon.ItemPredicate.Builder.item().of(items).build()}));
    return this;
  }

  public void build(Consumer<FinishedRecipe> consumerIn) {
    ResourceLocation location = ForgeRegistries.ITEMS.getKey(this.result);
    this.build(consumerIn, PeruMod.MOD_ID + ":mortar/" + location.getPath());
  }

  public void build(Consumer<FinishedRecipe> consumerIn, String save) {
    ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);
    if ((new ResourceLocation(save)).equals(resourcelocation)) {
      throw new IllegalStateException("Mortar Recipe " + save + " should remove its 'save' argument");
    } else {
      this.build(consumerIn, new ResourceLocation(save));
    }
  }

  public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
    if (!this.advancement.getCriteria().isEmpty()) {
      this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
      ResourceLocation advancementId = new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath());
      consumerIn.accept(new MortarBlockRecipeBuilder.Result(id, this.result, this.count, this.ingredients, this.processTime, this.experience, this.container, this.advancement, advancementId));
    } else {
      consumerIn.accept(new MortarBlockRecipeBuilder.Result(id, this.result, this.count, this.ingredients, this.processTime, this.experience, this.container));
    }

  }

  public static class Result implements FinishedRecipe {
    private final ResourceLocation id;
    private final List<Ingredient> ingredients;
    private final Item result;
    private final int count;
    private final int processTime;
    private final float experience;
    private final Item container;
    private final Advancement.Builder advancement;
    private final ResourceLocation advancementId;

    public Result(ResourceLocation idIn, Item resultIn, int countIn, List<Ingredient> ingredientsIn, int processTimeIn, float experienceIn, @Nullable Item containerIn, @Nullable Advancement.Builder advancement, @Nullable ResourceLocation advancementId) {
      this.id = idIn;
      this.ingredients = ingredientsIn;
      this.result = resultIn;
      this.count = countIn;
      this.processTime = processTimeIn;
      this.experience = experienceIn;
      this.container = containerIn;
      this.advancement = advancement;
      this.advancementId = advancementId;
    }

    public Result(ResourceLocation idIn, Item resultIn, int countIn, List<Ingredient> ingredientsIn, int processTimeIn, float experienceIn, @Nullable Item containerIn) {
      this(idIn, resultIn, countIn, ingredientsIn, processTimeIn, experienceIn, containerIn, (Advancement.Builder)null, (ResourceLocation)null);
    }

    public void serializeRecipeData(JsonObject json) {
      JsonArray arrayIngredients = new JsonArray();

      for(Ingredient ingredient : this.ingredients) {
        arrayIngredients.add(ingredient.toJson());
      }

      json.add("ingredients", arrayIngredients);
      JsonObject objectResult = new JsonObject();
      objectResult.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
      if (this.count > 1) {
        objectResult.addProperty("count", this.count);
      }

      json.add("result", objectResult);
      if (this.container != null) {
        JsonObject objectContainer = new JsonObject();
        objectContainer.addProperty("item", ForgeRegistries.ITEMS.getKey(this.container).toString());
        json.add("container", objectContainer);
      }

      if (this.experience > 0.0F) {
        json.addProperty("experience", this.experience);
      }

      json.addProperty("processTime", this.processTime);
    }

    public ResourceLocation getId() {
      return this.id;
    }

    public RecipeSerializer<?> getType() {
      return ModRecipeSerializers.MORTAR.get();
    }

    @Nullable
    public JsonObject serializeAdvancement() {
      return this.advancement != null ? this.advancement.serializeToJson() : null;
    }

    @Nullable
    public ResourceLocation getAdvancementId() {
      return this.advancementId;
    }
  }
}
