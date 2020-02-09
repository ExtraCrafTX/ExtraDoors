package com.extracraftx.minecraft.extradoors.block;

import com.extracraftx.minecraft.extradoors.ExtraDoors;
import com.extracraftx.minecraft.extradoors.interfaces.TeleportableLivingEntity;

import io.github.insomniakitten.couplings.hook.TrapdoorHooks;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PurpurTrapdoorBlock extends TrapdoorBlock{

    public PurpurTrapdoorBlock() {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.MAGENTA).strength(1.5f, 6f).build());
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockHitResult hitResult) {
        //TODO: Add trapdoor opened callback to couplings if and when added
        if(state.get(OPEN)){
            //If door is open, just close it
            BlockState newState = state.with(OPEN, false);
            world.setBlockState(pos, newState, 10);
            world.playLevelEvent(player, 1013, pos, 0);
            if(ExtraDoors.COUPLINGS)
                TrapdoorHooks.usageCallback(newState, world, pos, player, hand, hitResult, true);
            return true;
        }
        //Otherwise, teleport to opposite side if space available
        if(hitResult.getSide().getAxis() != net.minecraft.util.math.Direction.Axis.Y){
            return false;
        }
        BlockPos toPos = pos.offset(hitResult.getSide().getOpposite());
        if(!world.getBlockState(toPos).canSuffocate(world, toPos)){
            //Space available, teleport
            if (!world.isClient){
                if(((TeleportableLivingEntity)player).teleport(toPos.getX()+0.5, toPos.getY(), toPos.getZ()+0.5, player.yaw, -player.pitch, true, 4)){
                    world.playSound(null, toPos.getX()+0.5, toPos.getY(), toPos.getZ()+0.5, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }else{
                    //Couldn't teleport, open instead
                    BlockState newState = state.with(OPEN, true);
                    world.setBlockState(pos, newState, 10);
                    world.playLevelEvent(player, 1007, pos, 0);
                }
                // player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            }
            return true;
        }
        //Blocked, open instead
        BlockState newState = state.with(OPEN, true);
        world.setBlockState(pos, newState);
        world.playLevelEvent(player, 1007, pos, 0);
        return true;
    }
    
}