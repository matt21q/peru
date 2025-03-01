package net.matt.perumod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.matt.perumod.block.MudStoveBlock;
import net.matt.perumod.block.entity.MudStoveBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;
public class MudStoveRenderer implements BlockEntityRenderer<MudStoveBlockEntity> {
    public MudStoveRenderer(BlockEntityRendererProvider.Context context) {
    }

    public void render(MudStoveBlockEntity stoveEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = ((Direction) stoveEntity.getBlockState().getValue(MudStoveBlock.FACING)).getOpposite();
        ItemStackHandler inventory = stoveEntity.getInventory();
        int posLong = (int) stoveEntity.getBlockPos().asLong();

        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack stoveStack = inventory.getStackInSlot(i);
            if (!stoveStack.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5, 1.02, 0.5);
                float f = -direction.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                Vec2 itemOffset = stoveEntity.getStoveItemOffset(i);
                poseStack.translate((double) itemOffset.x, (double) itemOffset.y, 0.0);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                if (stoveEntity.getLevel() != null) {
                    Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemDisplayContext.FIXED, LevelRenderer.getLightColor(stoveEntity.getLevel(), stoveEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, stoveEntity.getLevel(), posLong + i);
                }

                poseStack.popPose();
            }
        }
    }
}
