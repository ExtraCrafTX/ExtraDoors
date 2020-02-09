package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.interfaces.TeleportableEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

@Mixin(Entity.class)
public abstract class EntityMixin implements TeleportableEntity{

    @Shadow
    private boolean teleportRequested;
    @Shadow
    private World world;

    @Override
    public void requestTeleport(double x, double y, double z, float yaw, float pitch) {
        if(!world.isClient){
            teleportRequested = true;
            this.setPositionAndAngles(x, y, z, yaw, pitch);
            ((ServerWorld)this.world).checkChunk((Entity)(Object)this);
        }
    }

    @Shadow
    public abstract void setPositionAndAngles(double x, double y, double z, float yaw, float pitch);

}