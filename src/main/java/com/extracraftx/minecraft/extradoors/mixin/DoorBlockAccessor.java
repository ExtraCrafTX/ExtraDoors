package com.extracraftx.minecraft.extradoors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.item.ItemPlacementContext;

@Mixin(DoorBlock.class)
public interface DoorBlockAccessor{

    @Invoker
    public abstract DoorHinge invokeGetHinge(ItemPlacementContext placementContext);

}