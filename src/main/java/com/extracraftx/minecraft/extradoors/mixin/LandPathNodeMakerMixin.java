package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;

import net.minecraft.block.DoorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(LandPathNodeMaker.class)
public class LandPathNodeMakerMixin {

    @Redirect(
        method = "getCommonNodeType",
        at = @At(target = "Lnet/minecraft/block/DoorBlock;isWoodenDoor(Lnet/minecraft/block/BlockState;)Z", value = "INVOKE")
        )
    private static boolean isClosedInteractableDoor(BlockState state){
        if(Tags.INTERACTABLE_DOORS.contains(state.getBlock()))
            return true;
        else
            return DoorBlock.isWoodenDoor(state);
    }

}