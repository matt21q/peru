package net.matt.perumod.block.custom.mortar;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MortarStoneSlot extends SlotItemHandler {
  public MortarStoneSlot(IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
    super(inventoryIn, index, xPosition, yPosition);
  }

  public boolean mayPlace(ItemStack stack) {
    return false;
  }

  public boolean mayPickup(Player playerIn) {
    return false;
  }
}
