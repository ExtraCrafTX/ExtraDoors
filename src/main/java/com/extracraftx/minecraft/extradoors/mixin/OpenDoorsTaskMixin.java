package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.brain.task.OpenDoorsTask;
import net.minecraft.tag.Tag;

@Mixin(OpenDoorsTask.class)
public class OpenDoorsTaskMixin{

    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/tag/Tag;)Z"))
    private static boolean isInStaticProxy(BlockState blockState, Tag tag){
        return blockState.isIn(Tags.INTERACTABLE_DOORS);
    }

    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/tag/Tag;)Z"))
    private boolean isInProxy(BlockState blockState, Tag tag){
        return blockState.isIn(Tags.INTERACTABLE_DOORS);
    }

}