package com.extracraftx.minecraft.extradoors.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlassTrapdoorBlock extends TrapdoorBlock {

    public GlassTrapdoorBlock() {
        super(Settings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos updatedPos,
            boolean b) {
        boolean isPowered = world.isReceivingRedstonePower(pos);
        if(block != this && isPowered != state.get(POWERED)){
            if(isPowered){
                BlockState newState = state.with(POWERED, true).cycle(OPEN);
                world.setBlockState(pos, newState, 2);
                world.playLevelEvent(null, state.get(OPEN) ? 1012 : 1006, pos, 0);
            }else{
                world.setBlockState(pos, state.with(POWERED, false), 2);
            }
        }
    }

}