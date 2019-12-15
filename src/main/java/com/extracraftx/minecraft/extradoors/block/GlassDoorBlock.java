package com.extracraftx.minecraft.extradoors.block;

import com.extracraftx.minecraft.extradoors.CouplingsHelper;
import com.extracraftx.minecraft.extradoors.ExtraDoors;

import io.github.chloedawn.couplings.Couplings;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class GlassDoorBlock extends DoorBlock {

    public GlassDoorBlock() {
        super(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos updatedPos,
            boolean b) {
        boolean isPowered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(
                pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if(block != this && isPowered != state.get(POWERED)){
            if(isPowered){
                BlockState newState = state.with(POWERED, true).cycle(OPEN);
                world.setBlockState(pos, newState, 2);
                world.playLevelEvent(null, state.get(OPEN) ? 1012 : 1006, pos, 0);
                if(ExtraDoors.COUPLINGS){
                    if (Couplings.areDoorsEnabled()) {
                        final BlockPos offset = CouplingsHelper.getOtherDoor(state, pos);
                        BlockState other = world.getBlockState(offset);
                        if (state.getBlock() == other.getBlock()) {
                            int power = CouplingsHelper.getPower(state, world, pos);
                            if(power > 7){
                                world.setBlockState(offset, other.with(OPEN, newState.get(OPEN)), 2);
                            }
                        }
                    }
                }
            }else{
                world.setBlockState(pos, state.with(POWERED, false), 2);
            }
        }
    }

}