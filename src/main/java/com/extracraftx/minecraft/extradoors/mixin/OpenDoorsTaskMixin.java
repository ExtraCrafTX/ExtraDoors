package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.brain.task.OpenDoorsTask;
import net.minecraft.tag.Tag;

@Mixin(OpenDoorsTask.class)
public class OpenDoorsTaskMixin{

    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/tag/Tag$Identified;contains(Ljava/lang/Object;)Z"))
    private static boolean containsProxy(Tag.Identified tag, Object obj){
        Block block = (Block) obj;
        return Tags.INTERACTABLE_DOORS.contains(block);
    }

}