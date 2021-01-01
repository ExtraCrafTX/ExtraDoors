package com.extracraftx.minecraft.extradoors.mixin;

import java.util.Iterator;
import java.util.stream.Stream;

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
            ServerWorld serverWorld = (ServerWorld)this.world;
            // teleportRequested = true;
            this.refreshPositionAndAngles(x, y, z, yaw, pitch);
            this.streamPassengersRecursively().forEach((entity) -> {
                serverWorld.checkEntityChunkPos(entity);
                ((EntityAccessor)entity).setTeleportRequested(true);
                Iterator<Entity> passengers = entity.getPassengerList().iterator();

                while(passengers.hasNext()) {
                    Entity passenger = passengers.next();
                    ((EntityAccessor)entity).invokeUpdatePassengerPosition(passenger, Entity::refreshPositionAfterTeleport);
                }
            });
        }
    }

    @Shadow
    public abstract void refreshPositionAndAngles(double x, double y, double z, float yaw, float pitch);

    @Shadow
    public abstract Stream<Entity> streamPassengersRecursively();

}