package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;

//Mixin after lithium
@Mixin(value = LandPathNodeMaker.class, priority = 1001)
public class LandPathNodeMakerLithiumCompatMixin {

    @Redirect(
        method = "getTaggedBlockType$lithium(Lnet/minecraft/class_2680;)Lnet/minecraft/class_7;",
        at = @At(target = "Lnet/minecraft/block/DoorBlock;isWoodenDoor(Lnet/minecraft/block/BlockState;)Z", value = "INVOKE")
    )
    private static boolean isInteractableDoor(BlockState state){
        return Tags.INTERACTABLE_DOORS.contains(state.getBlock()) || DoorBlock.isWoodenDoor(state);
    }

}