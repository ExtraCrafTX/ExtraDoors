package com.extracraftx.minecraft.extradoors.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BambooDoorBlock extends DoorBlock {

    public BambooDoorBlock() {
        super(FabricBlockSettings.of(Material.BAMBOO).strength(1f, 1f).sounds(BlockSoundGroup.BAMBOO).build());
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hitResult) {
        state = state.cycle(OPEN);
        world.setBlockState(pos, state, 10);
        playSound(world, player, pos, state);
        return true;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos updatedPos,
            boolean b) {
        //Do nothing
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        BlockState state = super.getPlacementState(placementContext);
        if(state == null)
            return state;
        return state.with(OPEN, false);
    }

    protected void playSound(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        world.playSound(player, pos,
                state.get(OPEN) ? SoundEvents.BLOCK_WOODEN_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE,
                SoundCategory.BLOCKS, 1, world.getRandom().nextFloat() * 0.1f + 1f);
    }

}