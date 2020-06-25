package com.extracraftx.minecraft.extradoors.mixin;

import java.util.stream.Stream;

import com.extracraftx.minecraft.extradoors.interfaces.TeleportableEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

@Mixin(Entity.class)
public abstract class EntityMixin implements TeleportableEntity{

    @Shadow public abstract void requestTeleport(double destX, double destY, double destZ);

    @Shadow public float pitch;
    @Shadow
    private World world;

    @Shadow public float yaw;

    @Override
    public void requestTeleport(double x, double y, double z, float yaw, float pitch) {
        if(!world.isClient){
            this.yaw=yaw;
            this.pitch=pitch;
            requestTeleport(x,y,z);
        }
    }


}