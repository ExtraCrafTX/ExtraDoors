package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.tags.Tags;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.ai.pathing.PathNodeType;

@Mixin(DoorBlock.class)
public class DoorBlockMixin {

    // Added for lithium compatibility
    public PathNodeType getPathNodeType(BlockState state) {
        if (state.get(DoorBlock.OPEN)) {
            return PathNodeType.DOOR_OPEN;
        } else if (state.isIn(Tags.INTERACTABLE_DOORS)) {
            return PathNodeType.DOOR_WOOD_CLOSED;
        } else {
            return PathNodeType.DOOR_IRON_CLOSED;
        }
    }

}
