package net.matt.perumod.block.custom.mortar;

import com.mojang.datafixers.util.Pair;
import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.util.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class MortarMenu extends AbstractContainerMenu {
  public static final ResourceLocation EMPTY_CONTAINER_SLOT = new ResourceLocation(PeruMod.MOD_ID, "item/empty_mortar");
  public final MortarBlockEntity blockEntity;
  public final ItemStackHandler inventory;
  private final ContainerData mortarData;
  private final ContainerLevelAccess canInteractWithCallable;
  protected final Level level;

  public MortarMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
    this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4));
  }

  public MortarMenu(int windowId, Inventory playerInventory, MortarBlockEntity blockEntity, ContainerData mortarDataIn) {
    super(ModMenuTypes.MORTAR.get(), windowId);
    this.blockEntity = blockEntity;
    this.inventory = blockEntity.getInventory();
    this.mortarData = mortarDataIn;
    this.level = playerInventory.player.level();
    this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
    int startX = 8;
    int startY = 18;
    int inputStartX = 30;
    int inputStartY = 17;
    int borderSlotSize = 18;

    for(int row = 0; row < 2; ++row) {
      for(int column = 0; column < 3; ++column) {
        this.addSlot(new SlotItemHandler(this.inventory, row * 3 + column, inputStartX + column * borderSlotSize, inputStartY + row * borderSlotSize));
      }
    }

    this.addSlot(new MortarPreviewSlot(this.inventory, 6, 124, 25));
    this.addSlot(new SlotItemHandler(this.inventory, 7, 48, 55) {
      public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
        return Pair.of(InventoryMenu.BLOCK_ATLAS, MortarMenu.EMPTY_CONTAINER_SLOT);
      }
    });
    this.addSlot(new MortarResultSlot(playerInventory.player, blockEntity, this.inventory, 8, 124, 55));
    int startPlayerInvY = startY * 4 + 12;

    for(int row = 0; row < 3; ++row) {
      for(int column = 0; column < 9; ++column) {
        this.addSlot(new Slot(playerInventory, 9 + row * 9 + column, startX + column * borderSlotSize, startPlayerInvY + row * borderSlotSize));
      }
    }

    for(int column = 0; column < 9; ++column) {
      this.addSlot(new Slot(playerInventory, column, startX + column * borderSlotSize, 142));
    }

    this.addDataSlots(mortarDataIn);
  }

  private static MortarBlockEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
    Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
    Objects.requireNonNull(data, "data cannot be null");
    BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
    if (tileAtPos instanceof MortarBlockEntity) {
      return (MortarBlockEntity)tileAtPos;
    } else {
      throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }
  }

  public boolean stillValid(Player playerIn) {
    return stillValid(this.canInteractWithCallable, playerIn, (Block) ModBlocks.MORTAR_BLOCK.get());
  }

  public ItemStack quickMoveStack(Player playerIn, int index) {
    int indexMealDisplay = 6;
    int indexContainerInput = 7;
    int indexOutput = 8;
    int startPlayerInv = indexOutput + 1;
    int endPlayerInv = startPlayerInv + 36;
    ItemStack slotStackCopy = ItemStack.EMPTY;
    Slot slot = this.slots.get(index);
    if (slot.hasItem()) {
      ItemStack slotStack = slot.getItem();
      slotStackCopy = slotStack.copy();
      if (index == indexOutput) {
        if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, true)) {
          return ItemStack.EMPTY;
        }
      } else if (index <= indexOutput) {
        if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, false)) {
          return ItemStack.EMPTY;
        }
      } else {
        boolean isValidContainer = slotStack.is(ModTags.Items.MORTAR_FUEL) || slotStack.is(this.blockEntity.getContainer().getItem());
        if (isValidContainer && !this.moveItemStackTo(slotStack, indexContainerInput, indexContainerInput + 1, false)) {
          return ItemStack.EMPTY;
        }

        if (!this.moveItemStackTo(slotStack, 0, indexMealDisplay, false)) {
          return ItemStack.EMPTY;
        }

        if (!this.moveItemStackTo(slotStack, indexContainerInput, indexOutput, false)) {
          return ItemStack.EMPTY;
        }
      }

      if (slotStack.isEmpty()) {
        slot.set(ItemStack.EMPTY);
      } else {
        slot.setChanged();
      }

      if (slotStack.getCount() == slotStackCopy.getCount()) {
        return ItemStack.EMPTY;
      }

      slot.onTake(playerIn, slotStack);
    }

    return slotStackCopy;
  }

  public int getProcessProgressScaled() {
    int i = this.mortarData.get(0);
    int j = this.mortarData.get(1);
    return j != 0 && i != 0 ? i * 24 / j : 0;
  }
}
