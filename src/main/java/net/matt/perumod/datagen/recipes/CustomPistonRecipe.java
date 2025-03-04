package net.matt.perumod.datagen.recipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class CustomPistonRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final ItemStack result;
    private final ItemStack input;

    public CustomPistonRecipe(ResourceLocation id, ItemStack input, ItemStack result) {
        this.id = id;
        this.input = input;
        this.result = result;
    }

    @Override
    public boolean matches(Container inv, Level world) {
        // Lógica para verificar si la receta se puede aplicar
        return inv.getItem(0).is(input.getItem());
    }

    @Override
    public ItemStack assemble(Container inv) {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PeruMod.RECIPE_SERIALIZERS.get("custom_piston_recipe").get();
    }

    @Override
    public RecipeType<?> getType() {
        return CustomRecipeType.INSTANCE;
    }

    public static class Serializer implements RecipeSerializer<CustomPistonRecipe> {
        @Override
        public CustomPistonRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            // Lógica para deserializar la receta desde JSON
            ItemStack input = ItemStack.of(new ResourceLocation(json.get("input").getAsString()));
            ItemStack result = ItemStack.of(new ResourceLocation(json.get("result").getAsString()));
            return new CustomPistonRecipe(recipeId, input, result);
        }

        @Override
        public CustomPistonRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            // Lógica para deserializar la receta desde el buffer
            ItemStack input = buffer.readItem();
            ItemStack result = buffer.readItem();
            return new CustomPistonRecipe(recipeId, input, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CustomPistonRecipe recipe) {
            // Lógica para serializar la receta al buffer
            buffer.writeItem(recipe.input);
            buffer.writeItem(recipe.result);
        }
    }
}
