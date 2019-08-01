package com.extracraftx.minecraft.extradoors.block;

import com.extracraftx.minecraft.extradoors.mixin.DoorBlockAccessor;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class GoldDoorBlock extends DoorBlock {

    public GoldDoorBlock() {
        super(FabricBlockSettings.of(Material.METAL, MaterialColor.GOLD).strength(4, 4).sounds(BlockSoundGroup.METAL)
                .build());
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hitResult) {
        if (!state.get(POWERED)) {
            state = state.cycle(OPEN);
            world.setBlockState(pos, state, 10);
            world.playLevelEvent(player, state.get(OPEN) ? 1011 : 1005, pos, 0);
        }
        return true;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos updatedPos,
            boolean b) {
        boolean isPowered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(
                pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (block != this && isPowered != state.get(POWERED)) {
            world.setBlockState(pos, state.with(POWERED, isPowered), 2);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        BlockPos pos = placementContext.getBlockPos();
        if (pos.getY() < 255 && placementContext.getWorld().getBlockState(pos.up()).canReplace(placementContext)) {
            World world = placementContext.getWorld();
            boolean isPowered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up());
            return this.getDefaultState().with(POWERED, isPowered).with(FACING, placementContext.getPlayerFacing())
                    .with(HINGE, ((DoorBlockAccessor) this).invokeGetHinge(placementContext))
                    .with(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

}