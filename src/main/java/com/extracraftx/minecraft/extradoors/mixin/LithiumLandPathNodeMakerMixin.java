package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// increase priority value to load after Lithium mixins do
@Mixin(value=LandPathNodeMaker.class,priority = 1002)
public class LithiumLandPathNodeMakerMixin {

    @Redirect(
            method = "Lnet/minecraft/entity/ai/pathing/LandPathNodeMaker;getTaggedBlockType$lithium(Lnet/minecraft/block/BlockState;)Lnet/minecraft/entity/ai/pathing/PathNodeType;",
            at = @At(target = "Lnet/minecraft/block/DoorBlock;isWoodenDoor(Lnet/minecraft/block/BlockState;)Z", value = "INVOKE")
    )
    private static boolean isClosedInteractableDoor(BlockState state){
        if(Tags.INTERACTABLE_DOORS.contains(state.getBlock()))
            return true;
        else
            return DoorBlock.isWoodenDoor(state);
    }
}
