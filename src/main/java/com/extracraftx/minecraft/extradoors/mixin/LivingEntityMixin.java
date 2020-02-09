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
        double oldX = this.getX();
        double oldY = this.getY();
        double oldZ = this.getZ();
        float oldYaw = this.yaw;
        float oldPitch = this.pitch;

        boolean successful = false;
        BlockPos currentBlockPos = new BlockPos(x, y, z);
        if(world.isChunkLoaded(currentBlockPos)){
            int blocksDown = 0;
            boolean found = false;
            while(!found && blocksDown <= maxDrop && currentBlockPos.getY() > 0){
                if(world.getBlockState(currentBlockPos.down()).getMaterial().blocksMovement())
                    found = true;
                else{
                    currentBlockPos = currentBlockPos.down();
                    y --;
                    blocksDown ++;
                }
            }
            if(found){
                ((TeleportableEntity)this).requestTeleport(x, y, z, yaw, pitch);
                if(!world.containsFluid(this.getBoundingBox())){
                    successful = true;
                }
            }
        }

        if(!successful){
            ((TeleportableEntity)this).requestTeleport(oldX, oldY, oldZ, oldYaw, oldPitch);
            return false;
        }

        if(particles){
            world.sendEntityStatus(this, (byte) 46);
        }

        return true;
    }

}