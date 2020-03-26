package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.ai.brain.sensor.InteractableDoorsSensor;
import net.minecraft.tag.Tag;

@Mixin(InteractableDoorsSensor.class)
public class InteractableDoorsSensorMixin{

    @ModifyArg(method = "sense", at = @At(value="INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/tag/Tag;)Z"), index=0)
    private Tag modifyTag(Tag tag){
        return Tags.INTERACTABLE_DOORS;
    }

}