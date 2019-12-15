package com.extracraftx.minecraft.extradoors;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CouplingsHelper{

    public static int getPower(BlockState state, World world, BlockPos pos){
        return Math.max(
            world.getReceivedRedstonePower(pos),
            world.getReceivedRedstonePower(pos.offset(state.get(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN))
        );
    }

    public static BlockPos getOtherDoor(BlockState self, BlockPos origin) {
        Direction facing = self.get(DoorBlock.FACING);
        boolean left = DoorHinge.LEFT == self.get(DoorBlock.HINGE);
        return origin.offset(left ? facing.rotateYClockwise() : facing.rotateYCounterclockwise());
    }

}