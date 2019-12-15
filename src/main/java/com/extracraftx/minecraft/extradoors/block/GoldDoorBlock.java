package com.extracraftx.minecraft.extradoors.block;

import com.extracraftx.minecraft.extradoors.ExtraDoors;
import com.extracraftx.minecraft.extradoors.CouplingsHelper;
import com.extracraftx.minecraft.extradoors.sound.Sounds;

import io.github.insomniakitten.couplings.Couplings;
import io.github.insomniakitten.couplings.hook.DoorHooks;
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
import net.minecraft.sound.SoundCategory;
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
            if(ExtraDoors.COUPLINGS){
                DoorHooks.usageCallback(state, world, pos, player, hand, hitResult, true);
            }
            return true;
        }
        world.playSound(player, pos, Sounds.LOCKED, SoundCategory.BLOCKS, 1, world.random.nextFloat() * 0.1f + 1.1f);
        if(ExtraDoors.COUPLINGS){
            DoorHooks.usageCallback(state, world, pos, player, hand, hitResult, true);
        }
        return true;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos updatedPos,
            boolean b) {
        boolean isPowered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(
                pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if(!isPowered && ExtraDoors.COUPLINGS){
            if(Couplings.areDoorsEnabled()){
                BlockPos offset = CouplingsHelper.getOtherDoor(state, pos);
                BlockState other = world.getBlockState(offset);
                if(state.getBlock() == other.getBlock()){
                    isPowered = CouplingsHelper.getPower(other, world, offset) > 7;
                }
            }
        }
        if (block != this && isPowered != state.get(POWERED)) {
            world.setBlockState(pos, state.with(POWERED, isPowered), 2);
        }
        if(ExtraDoors.COUPLINGS){
            if (Couplings.areDoorsEnabled()) {
                final BlockPos offset = CouplingsHelper.getOtherDoor(state, pos);
                BlockState other = world.getBlockState(offset);
                if (state.getBlock() == other.getBlock()) {
                    int power = CouplingsHelper.getPower(state, world, pos);
                    if(power > 7)
                        world.setBlockState(offset, other.with(DoorBlock.POWERED, true), 2);
                    else if(CouplingsHelper.getPower(other, world, offset) == 0){
                        world.setBlockState(offset, other.with(DoorBlock.POWERED, false), 2);
                    }
                }
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        BlockState state = super.getPlacementState(placementContext);
        if(state == null)
            return state;
        return state.with(OPEN, false);
    }

}