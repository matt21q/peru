package net.matt.perumod.block.custom.mortar;

import net.matt.perumod.block.entity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class MortarBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
  public MortarBlock(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return ((BlockEntityType) ModBlockEntityTypes.MORTAR.get()).create(pos, state);
  }

  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.MODEL;
  }

  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
   if (!level.isClientSide) {
      BlockEntity tileEntity = level.getBlockEntity(pos);
      if (tileEntity instanceof MortarBlockEntity) {
        MortarBlockEntity blockEntity = (MortarBlockEntity)tileEntity;
        NetworkHooks.openScreen((ServerPlayer)player, blockEntity, pos);
      }
      return InteractionResult.SUCCESS;
    }
    return InteractionResult.SUCCESS;
  }

  // drop contents when broken
  @Override
  public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity tileEntity = level.getBlockEntity(pos);
      if (tileEntity instanceof MortarBlockEntity) {
        MortarBlockEntity mortarEntity = (MortarBlockEntity)tileEntity;
        Containers.dropContents(level, pos, mortarEntity.getDroppableInventory());
        mortarEntity.getUsedRecipesAndPopExperience(level, Vec3.atCenterOf(pos));
        level.updateNeighbourForOutputSignal(pos, this);
      }

      super.onRemove(state, level, pos, newState, isMoving);
    }
  }

  @Nullable
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
    if (level.isClientSide) {
      return createTickerHelper(blockEntity, ModBlockEntityTypes.MORTAR.get(), MortarBlockEntity::animationTick);
    }
    return createTickerHelper(blockEntity, ModBlockEntityTypes.MORTAR.get(), MortarBlockEntity::cookingTick);
  }
}
