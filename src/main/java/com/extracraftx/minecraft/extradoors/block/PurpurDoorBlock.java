package com.extracraftx.minecraft.extradoors.block;

import com.extracraftx.minecraft.extradoors.ExtraDoors;
import com.extracraftx.minecraft.extradoors.interfaces.TeleportableLivingEntity;

import io.github.insomniakitten.couplings.hook.DoorHooks;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PurpurDoorBlock extends DoorBlock{

    public PurpurDoorBlock() {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.MAGENTA).strength(1.5f, 6f).build());
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockHitResult hitResult) {
        if(state.get(OPEN)){
            //If door is open, just close it
            BlockState newState = state.with(OPEN, false);
            world.setBlockState(pos, newState, 10);
            world.playLevelEvent(player, 1006, pos, 0);
            if(ExtraDoors.COUPLINGS)
                DoorHooks.usageCallback(newState, world, pos, player, hand, hitResult, true);
            return true;
        }
        //Otherwise, teleport to opposite side if space available
        BlockPos toPos = pos;
        if(state.get(HALF) == DoubleBlockHalf.UPPER)
            toPos = toPos.down();
        if(state.get(FACING).getAxis() != hitResult.getSide().getAxis()){
            return false;
        }
        toPos = toPos.offset(hitResult.getSide().getOpposite());
        if(!world.getBlockState(toPos).canSuffocate(world, toPos)){
            //Space available, teleport
            if (((TeleportableLivingEntity)player).teleport(toPos.getX()+0.5, toPos.getY(), toPos.getZ()+0.5, player.yaw + 180, player.pitch, true)) {
                world.playSound(null, toPos.getX()+0.5, toPos.getY(), toPos.getZ()+0.5, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                // player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            }else{
                //Couldn't teleport, open instead
                BlockState newState = state.with(OPEN, true);
                world.setBlockState(pos, newState, 10);
                world.playLevelEvent(player, 1012, pos, 0);
                if(ExtraDoors.COUPLINGS)
                    DoorHooks.openCallback(state, world, pos, true);
            }
            return true;
        }
        //Blocked, open instead
        BlockState newState = state.with(OPEN, true);
        world.setBlockState(pos, newState, 10);
        world.playLevelEvent(player, 1012, pos, 0);
        if(ExtraDoors.COUPLINGS)
            DoorHooks.openCallback(state, world, pos, true);
        return true;
    }
    
}