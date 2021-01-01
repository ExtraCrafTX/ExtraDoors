package com.extracraftx.minecraft.extradoors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public interface EntityAccessor{

    @Accessor
    public void setTeleportRequested(boolean teleportRequested);
    @Invoker
    public void invokeUpdatePassengerPosition(Entity entity, Entity.PositionUpdater positionUpdater);

}