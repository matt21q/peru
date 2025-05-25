package net.matt.perumod.block.custom.mortar;


import com.mojang.blaze3d.systems.RenderSystem;
import net.matt.perumod.PeruMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.utility.TextUtils;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MortarScreen extends AbstractContainerScreen<MortarMenu> {
  private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(PeruMod.MOD_ID, "textures/gui/mortar.png");
  private static final Rectangle PROGRESS_ARROW = new Rectangle(89, 25, 0, 17);

  private boolean widthTooNarrow;

  public MortarScreen(MortarMenu screenContainer, Inventory inv, Component titleIn) {
    super(screenContainer, inv, titleIn);
  }

  @Override
  public void init() {
    super.init();
    this.widthTooNarrow = this.width < 379;
    this.titleLabelX = 28;
  }

  @Override
  protected void containerTick() {
    super.containerTick();
  }

  @Override
  public void render(GuiGraphics gui, final int mouseX, final int mouseY, float partialTicks) {
    this.renderBackground(gui);

    super.render(gui, mouseX, mouseY, partialTicks);
    this.renderMealDisplayTooltip(gui, mouseX, mouseY);
  }

  protected void renderMealDisplayTooltip(GuiGraphics gui, int mouseX, int mouseY) {
    if (this.minecraft != null && this.minecraft.player != null && this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
      if (this.hoveredSlot.index == 6) {
        List<Component> tooltip = new ArrayList<>();

        ItemStack mealStack = this.hoveredSlot.getItem();
        tooltip.add(((MutableComponent) mealStack.getItem().getDescription()).withStyle(mealStack.getRarity().color));

        ItemStack containerStack = this.menu.blockEntity.getContainer();
        String container = !containerStack.isEmpty() ? containerStack.getItem().getDescription().getString() : "";

        tooltip.add(Component.translatable("container.mortar.fuels", container).withStyle(ChatFormatting.GRAY));

        gui.renderComponentTooltip(font, tooltip, mouseX, mouseY);
      } else {
        gui.renderTooltip(font, this.hoveredSlot.getItem(), mouseX, mouseY);
      }
    }
  }

  @Override

  protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) {
    gui.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0xFFFFFF, false);
  }

  @Override
  protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
    // Render UI background
    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    if (this.minecraft == null)
      return;

    gui.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

    // Render progress arrow
    int l = this.menu.getProcessProgressScaled();
    gui.blit(BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
  }

  @Override
  protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
    return super.isHovering(x, y, width, height, mouseX, mouseY);
  }

  @Override
  public boolean mouseClicked(double mouseX, double mouseY, int buttonId) {
    return super.mouseClicked(mouseX, mouseY, buttonId);
  }

  @Override
  protected void slotClicked(Slot slot, int mouseX, int mouseY, ClickType clickType) {
    super.slotClicked(slot, mouseX, mouseY, clickType);
  }
}