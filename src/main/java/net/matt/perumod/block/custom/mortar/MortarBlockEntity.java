package net.matt.perumod.block.custom.mortar;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.matt.perumod.block.entity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.mixin.accessor.RecipeManagerAccessor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MortarBlockEntity extends SyncedBlockEntity implements MenuProvider, Nameable, RecipeHolder {
  public static final Map<Item, Item> INGREDIENT_REMAINDER_OVERRIDES;
  private final ItemStackHandler inventory = this.createHandler();
  private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> new MortarItemHandler(this.inventory, Direction.UP));
  private final LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> new MortarItemHandler(this.inventory, Direction.DOWN));
  private int processTime;
  private int processTimeTotal;
  private ItemStack mealContainerStack;
  private Component customName;
  protected final ContainerData mortarData;
  private final Object2IntOpenHashMap<ResourceLocation> usedRecipeTracker;
  private ResourceLocation lastRecipeID;
  private boolean checkNewRecipe;

  public MortarBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntityTypes.MORTAR.get(), pos, state);
    this.mealContainerStack = ItemStack.EMPTY;
    this.mortarData = this.createIntArray();
    this.usedRecipeTracker = new Object2IntOpenHashMap();
    this.checkNewRecipe = true;
  }

  public void load(CompoundTag compound) {
    super.load(compound);
    this.inventory.deserializeNBT(compound.getCompound("Inventory"));
    this.processTime = compound.getInt("ProcessTime");
    this.processTimeTotal = compound.getInt("ProcessTimeTotal");
    this.mealContainerStack = ItemStack.of(compound.getCompound("Container"));
    if (compound.contains("CustomName", 8)) {
      this.customName = Component.Serializer.fromJson(compound.getString("CustomName"));
    }

    CompoundTag compoundRecipes = compound.getCompound("RecipesUsed");

    for(String key : compoundRecipes.getAllKeys()) {
      this.usedRecipeTracker.put(new ResourceLocation(key), compoundRecipes.getInt(key));
    }
  }

  public void saveAdditional(CompoundTag compound) {
    super.saveAdditional(compound);
    compound.putInt("ProcessTime", this.processTime);
    compound.putInt("ProcessTimeTotal", this.processTimeTotal);
    compound.put("Container", this.mealContainerStack.serializeNBT());
    if (this.customName != null) {
      compound.putString("CustomName", Component.Serializer.toJson(this.customName));
    }

    compound.put("Inventory", this.inventory.serializeNBT());
    CompoundTag compoundRecipes = new CompoundTag();
    this.usedRecipeTracker.forEach((recipeId, craftedAmount) -> compoundRecipes.putInt(recipeId.toString(), craftedAmount));
    compound.put("RecipesUsed", compoundRecipes);
  }

  private CompoundTag writeItems(CompoundTag compound) {
    super.saveAdditional(compound);
    compound.put("Container", this.mealContainerStack.serializeNBT());
    compound.put("Inventory", this.inventory.serializeNBT());
    return compound;
  }

  public static void processTick(Level level, BlockPos pos, BlockState state, MortarBlockEntity mortar) {
    boolean didInventoryChange = false;
    if (mortar.hasInput()) {
      Optional<MortarRecipe> recipe = mortar.getMatchingRecipe(new RecipeWrapper(mortar.inventory));
      if (recipe.isPresent() && mortar.canProcess(recipe.get())) {
        didInventoryChange = mortar.startProcess(recipe.get(), mortar);
      } else {
        mortar.processTime = 0;
      }
    } else if (mortar.processTime > 0) {
      mortar.processTime = Mth.clamp(mortar.processTime - 2, 0, mortar.processTimeTotal);
    }

    ItemStack mealStack = mortar.getMeal();
    if (!mealStack.isEmpty()) {
      if (!mortar.doesMealHaveContainer(mealStack)) {
        mortar.moveMealToOutput();
        didInventoryChange = true;
      } else if (!mortar.inventory.getStackInSlot(7).isEmpty()) {
        mortar.useStoredContainersOnMeal();
        didInventoryChange = true;
      }
    }

    if (didInventoryChange) {
      mortar.inventoryChanged();
    }
  }

  public static void animationTick(Level level, BlockPos pos, BlockState state, MortarBlockEntity mortar) {
      RandomSource random = level.random;
      if (random.nextFloat() < 0.2F) {
        double x = (double)pos.getX() + (double)0.5F + (random.nextDouble() * 0.6 - 0.3);
        double y = (double)pos.getY() + 0.7;
        double z = (double)pos.getZ() + (double)0.5F + (random.nextDouble() * 0.6 - 0.3);
        // add particle here
      }

      if (random.nextFloat() < 0.05F) {
        double x = (double)pos.getX() + (double)0.5F + (random.nextDouble() * 0.4 - 0.2);
        double y = (double)pos.getY() + (double)0.5F;
        double z = (double)pos.getZ() + (double)0.5F + (random.nextDouble() * 0.4 - 0.2);
        double motionY = random.nextBoolean() ? 0.015 : 0.005;
        // add particle here
      }
  }

  private Optional<MortarRecipe> getMatchingRecipe(RecipeWrapper inventoryWrapper) {
    if (this.level == null) {
      return Optional.empty();
    } else {
      if (this.lastRecipeID != null) {
        Recipe<RecipeWrapper> recipe = (Recipe)((RecipeManagerAccessor)this.level.getRecipeManager()).getRecipeMap((RecipeType) ModRecipeTypes.MORTAR.get()).get(this.lastRecipeID);
        if (recipe instanceof MortarRecipe) {
          if (recipe.matches(inventoryWrapper, this.level)) {
            return Optional.of((MortarRecipe)recipe);
          }

          if (ItemStack.isSameItem(recipe.getResultItem(this.level.registryAccess()), this.getMeal())) {
            return Optional.empty();
          }
        }
      }

      if (this.checkNewRecipe) {
        Optional<MortarRecipe> recipe = this.level.getRecipeManager().getRecipeFor((RecipeType)ModRecipeTypes.MORTAR.get(), inventoryWrapper, this.level);
        if (recipe.isPresent()) {
          ResourceLocation newRecipeID = ((MortarRecipe)recipe.get()).getId();
          if (this.lastRecipeID != null && !this.lastRecipeID.equals(newRecipeID)) {
            this.processTime = 0;
          }

          this.lastRecipeID = newRecipeID;
          return recipe;
        }
      }

      this.checkNewRecipe = false;
      return Optional.empty();
    }
  }

  public ItemStack getContainer() {
    ItemStack mealStack = this.getMeal();
    return !mealStack.isEmpty() && !this.mealContainerStack.isEmpty() ? this.mealContainerStack : mealStack.getCraftingRemainingItem();
  }

  private boolean hasInput() {
    for(int i = 0; i < 6; ++i) {
      if (!this.inventory.getStackInSlot(i).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  protected boolean canProcess(MortarRecipe recipe) {
    if (this.hasInput()) {
      ItemStack resultStack = recipe.getResultItem(this.level.registryAccess());
      if (resultStack.isEmpty()) {
        return false;
      } else {
        ItemStack storedMealStack = this.inventory.getStackInSlot(6);
        if (storedMealStack.isEmpty()) {
          return true;
        } else if (!ItemStack.isSameItem(storedMealStack, resultStack)) {
          return false;
        } else if (storedMealStack.getCount() + resultStack.getCount() <= this.inventory.getSlotLimit(6)) {
          return true;
        } else {
          return storedMealStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize();
        }
      }
    } else {
      return false;
    }
  }

  private boolean startProcess(MortarRecipe recipe, MortarBlockEntity mortar) {
    if (this.level == null) {
      return false;
    } else {
      ++this.processTime;
      this.processTimeTotal = recipe.getProcessTime();
      if (this.processTime < this.processTimeTotal) {
        return false;
      } else {
        this.processTime = 0;
        this.mealContainerStack = recipe.getOutputContainer();
        ItemStack resultStack = recipe.getResultItem(this.level.registryAccess());
        ItemStack storedMealStack = this.inventory.getStackInSlot(6);
        if (storedMealStack.isEmpty()) {
          this.inventory.setStackInSlot(6, resultStack.copy());
        } else if (ItemStack.isSameItem(storedMealStack, resultStack)) {
          storedMealStack.grow(resultStack.getCount());
        }

        mortar.setRecipeUsed(recipe);

        for(int i = 0; i < 6; ++i) {
          ItemStack slotStack = this.inventory.getStackInSlot(i);
          if (slotStack.hasCraftingRemainingItem()) {
          } else if (INGREDIENT_REMAINDER_OVERRIDES.containsKey(slotStack.getItem())) {
          }

          if (!slotStack.isEmpty()) {
            slotStack.shrink(1);
          }
        }

        return true;
      }
    }
  }



  public void setRecipeUsed(@Nullable Recipe<?> recipe) {
    if (recipe != null) {
      ResourceLocation recipeID = recipe.getId();
      this.usedRecipeTracker.addTo(recipeID, 1);
    }

  }

  @Nullable
  public Recipe<?> getRecipeUsed() {
    return null;
  }

  public void awardUsedRecipes(Player player, List<ItemStack> items) {
    List<Recipe<?>> usedRecipes = this.getUsedRecipesAndPopExperience(player.level(), player.position());
    player.awardRecipes(usedRecipes);
    this.usedRecipeTracker.clear();
  }

  public List<Recipe<?>> getUsedRecipesAndPopExperience(Level level, Vec3 pos) {
    List<Recipe<?>> list = Lists.newArrayList();
    ObjectIterator var4 = this.usedRecipeTracker.object2IntEntrySet().iterator();

    while(var4.hasNext()) {
      Object2IntMap.Entry<ResourceLocation> entry = (Object2IntMap.Entry)var4.next();
      level.getRecipeManager().byKey((ResourceLocation)entry.getKey()).ifPresent((recipe) -> {
        list.add(recipe);
        splitAndSpawnExperience((ServerLevel)level, pos, entry.getIntValue(), ((MortarRecipe)recipe).getExperience());
      });
    }

    return list;
  }

  private static void splitAndSpawnExperience(ServerLevel level, Vec3 pos, int craftedAmount, float experience) {
    int expTotal = Mth.floor((float)craftedAmount * experience);
    float expFraction = Mth.frac((float)craftedAmount * experience);
    if (expFraction != 0.0F && Math.random() < (double)expFraction) {
      ++expTotal;
    }

    ExperienceOrb.award(level, pos, expTotal);
  }

  public ItemStackHandler getInventory() {
    return this.inventory;
  }

  public ItemStack getMeal() {
    return this.inventory.getStackInSlot(6);
  }

  public NonNullList<ItemStack> getDroppableInventory() {
    NonNullList<ItemStack> drops = NonNullList.create();

    for(int i = 0; i < 9; ++i) {
      if (i != 6) {
        drops.add(this.inventory.getStackInSlot(i));
      }
    }

    return drops;
  }

  private void moveMealToOutput() {
    ItemStack mealStack = this.inventory.getStackInSlot(6);
    ItemStack outputStack = this.inventory.getStackInSlot(8);
    int mealCount = Math.min(mealStack.getCount(), mealStack.getMaxStackSize() - outputStack.getCount());
    if (outputStack.isEmpty()) {
      this.inventory.setStackInSlot(8, mealStack.split(mealCount));
    } else if (outputStack.getItem() == mealStack.getItem()) {
      mealStack.shrink(mealCount);
      outputStack.grow(mealCount);
    }

  }

  private void useStoredContainersOnMeal() {
    ItemStack mealStack = this.inventory.getStackInSlot(6);
    ItemStack containerInputStack = this.inventory.getStackInSlot(7);
    ItemStack outputStack = this.inventory.getStackInSlot(8);
    if (this.isContainerValid(containerInputStack) && outputStack.getCount() < outputStack.getMaxStackSize()) {
      int smallerStackCount = Math.min(mealStack.getCount(), containerInputStack.getCount());
      int mealCount = Math.min(smallerStackCount, mealStack.getMaxStackSize() - outputStack.getCount());
      if (outputStack.isEmpty()) {
        containerInputStack.shrink(mealCount);
        this.inventory.setStackInSlot(8, mealStack.split(mealCount));
      } else if (outputStack.getItem() == mealStack.getItem()) {
        mealStack.shrink(mealCount);
        containerInputStack.shrink(mealCount);
        outputStack.grow(mealCount);
      }
    }

  }

  private boolean doesMealHaveContainer(ItemStack meal) {
    return !this.mealContainerStack.isEmpty() || meal.hasCraftingRemainingItem();
  }

  public boolean isContainerValid(ItemStack containerItem) {
    if (containerItem.isEmpty()) {
      return false;
    } else {
      return !this.mealContainerStack.isEmpty() ? ItemStack.isSameItem(this.mealContainerStack, containerItem) : ItemStack.isSameItem(this.getMeal(), containerItem);
    }
  }

  public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
    return new MortarMenu(id, player, this, this.mortarData);
  }

  @Nonnull
  public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
    if (cap.equals(ForgeCapabilities.ITEM_HANDLER)) {
      return side != null && !side.equals(Direction.UP) ? this.outputHandler.cast() : this.inputHandler.cast();
    } else {
      return super.getCapability(cap, side);
    }
  }

  public void setRemoved() {
    super.setRemoved();
    this.inputHandler.invalidate();
    this.outputHandler.invalidate();
  }

  public CompoundTag getUpdateTag() {
    return this.writeItems(new CompoundTag());
  }

  private ItemStackHandler createHandler() {
    return new ItemStackHandler(9) {
      protected void onContentsChanged(int slot) {
        if (slot >= 0 && slot < 6) {
          MortarBlockEntity.this.checkNewRecipe = true;
        }

        MortarBlockEntity.this.inventoryChanged();
      }
    };
  }

  private ContainerData createIntArray() {
    return new ContainerData() {
      public int get(int index) {
        int var10000;
        switch (index) {
          case 0 -> var10000 = MortarBlockEntity.this.processTime;
          case 1 -> var10000 = MortarBlockEntity.this.processTimeTotal;
          default -> var10000 = 0;
        }

        return var10000;
      }

      public void set(int index, int value) {
        switch (index) {
          case 0 -> MortarBlockEntity.this.processTime = value;
          case 1 -> MortarBlockEntity.this.processTimeTotal = value;
        }

      }

      public int getCount() {
        return 2;
      }
    };
  }

  static {
    INGREDIENT_REMAINDER_OVERRIDES = Map.ofEntries(Map.entry(Items.POWDER_SNOW_BUCKET, Items.BUCKET), Map.entry(Items.AXOLOTL_BUCKET, Items.BUCKET), Map.entry(Items.COD_BUCKET, Items.BUCKET), Map.entry(Items.PUFFERFISH_BUCKET, Items.BUCKET), Map.entry(Items.SALMON_BUCKET, Items.BUCKET), Map.entry(Items.TROPICAL_FISH_BUCKET, Items.BUCKET), Map.entry(Items.SUSPICIOUS_STEW, Items.BOWL), Map.entry(Items.MUSHROOM_STEW, Items.BOWL), Map.entry(Items.RABBIT_STEW, Items.BOWL), Map.entry(Items.BEETROOT_SOUP, Items.BOWL), Map.entry(Items.POTION, Items.GLASS_BOTTLE), Map.entry(Items.SPLASH_POTION, Items.GLASS_BOTTLE), Map.entry(Items.LINGERING_POTION, Items.GLASS_BOTTLE), Map.entry(Items.EXPERIENCE_BOTTLE, Items.GLASS_BOTTLE));
  }

  @Override
  public Component getName() {
    return Component.literal("Mortar");
  }

  @Override
  public Component getDisplayName() {
    return Component.literal("Mortar");
  }
}
