package com.extracraftx.minecraft.extradoors.mixin;

import com.extracraftx.minecraft.extradoors.interfaces.TeleportableEntity;
import com.extracraftx.minecraft.extradoors.interfaces.TeleportableLivingEntity;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements TeleportableLivingEntity{

    public LivingEntityMixin(){
        super(null, null);
    }

    @Override
    public boolean teleport(double x, double y, double z, float yaw, float pitch, boolean particles, int maxDrop) {
        double oldX = this.x;
        double oldY = this.y;
        double oldZ = this.z;
        float oldYaw = this.yaw;
        float oldPitch = this.pitch;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;

        boolean successful = false;
        BlockPos currentBlockPos = new BlockPos(this);
        if(world.isBlockLoaded(currentBlockPos)){
            int blocksDown = 0;
            boolean found = false;
            while(!found && blocksDown <= maxDrop && currentBlockPos.getY() > 0){
                if(world.getBlockState(currentBlockPos.down()).getMaterial().blocksMovement())
                    found = true;
                else{
                    currentBlockPos = currentBlockPos.down();
                    this.y --;
                    blocksDown ++;
                }
            }
            if(found){
                // this.requestTeleport(x, y, z);
                ((TeleportableEntity)this).requestTeleport(this.x, this.y, this.z, yaw, pitch);
                if(!world.intersectsFluid(this.getBoundingBox())){
                    successful = true;
                }
            }
        }

        if(!successful){
            // this.requestTeleport(oldX, oldY, oldZ);
            this.x = oldX;
            this.y = oldY;
            this.z = oldZ;
            this.yaw = oldYaw;
            this.pitch = oldPitch;
            ((TeleportableEntity)this).requestTeleport(oldX, oldY, oldZ, oldYaw, oldPitch);
            return false;
        }

        if(particles){
            world.sendEntityStatus(this, (byte) 46);
        }

        return true;
    }

}