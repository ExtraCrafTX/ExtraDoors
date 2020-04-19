package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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

    @Inject(
        method = "getBasicPathNodeType",
        at = @At(target = "net/minecraft/block/BlockState.getBlock()Lnet/minecraft/block/Block;", value = "INVOKE_ASSIGN"),
        locals = LocalCapture.CAPTURE_FAILHARD,
        cancellable = true)
    private void onGetBasicPathNodeType(BlockView blockView, int x, int y, int z, CallbackInfoReturnable<PathNodeType> callbackInfo, BlockPos pos, BlockState blockState, Block block){
        if(Tags.INTERACTABLE_DOORS.contains(block))
            callbackInfo.setReturnValue(PathNodeType.DOOR_WOOD_CLOSED);
    }

}