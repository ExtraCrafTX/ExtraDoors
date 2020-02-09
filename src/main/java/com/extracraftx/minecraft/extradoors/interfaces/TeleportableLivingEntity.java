package com.extracraftx.minecraft.extradoors.interfaces;

public interface TeleportableLivingEntity{

    public boolean teleport(double x, double y, double z, float yaw, float pitch, boolean particles);
    public boolean teleport(double x, double y, double z, float yaw, float pitch, boolean particles, int maxDrop);

}