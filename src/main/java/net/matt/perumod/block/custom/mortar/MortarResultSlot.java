package net.matt.perumod.block.custom.mortar;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class MortarResultSlot extends SlotItemHandler {
  public final MortarBlockEntity tileEntity;
  private final Player player;
  private int removeCount;

  public MortarResultSlot(Player player, MortarBlockEntity tile, IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
    super(inventoryIn, index, xPosition, yPosition);
    this.tileEntity = tile;
    this.player = player;
  }

  public boolean mayPlace(ItemStack stack) {
    return false;
  }

  @Nonnull
  public ItemStack remove(int amount) {
    if (this.hasItem()) {
      this.removeCount += Math.min(amount, this.getItem().getCount());
    }

    return super.remove(amount);
  }

  public void onTake(Player thePlayer, ItemStack stack) {
    this.checkTakeAchievements(stack);
    super.onTake(thePlayer, stack);
  }

  protected void onQuickCraft(ItemStack stack, int amount) {
    this.removeCount += amount;
    this.checkTakeAchievements(stack);
  }

  protected void checkTakeAchievements(ItemStack stack) {
    stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
    if (!this.player.level().isClientSide) {
      this.tileEntity.awardUsedRecipes(this.player, this.tileEntity.getDroppableInventory());
    }

    this.removeCount = 0;
  }
}
