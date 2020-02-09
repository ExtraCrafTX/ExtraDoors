package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.interfaces.TeleportableEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements TeleportableEntity{

    @Shadow
    public ServerPlayNetworkHandler networkHandler;

    @Override
    public void requestTeleport(double x, double y, double z, float yaw, float pitch) {
        networkHandler.requestTeleport(x, y, z, yaw, pitch);
    }

}