package net.matt.perumod.block.custom.feast;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import java.util.function.Supplier;

public class FriedCuyBlock extends FeastBlock {

    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape CUY_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE,
            Block.box(4.0D, 2.0D, 4.0D, 12.0D, 9.0D, 12.0D),
            BooleanOp.OR);


    public FriedCuyBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : CUY_SHAPE;
    }
}
