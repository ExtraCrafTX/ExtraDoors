package com.extracraftx.minecraft.extradoors.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;

public class ExtraDoorBlock extends DoorBlock{

    public ExtraDoorBlock(Settings settings){
        super(settings);
    }

    public ExtraDoorBlock(Block block){
        super(FabricBlockSettings.copy(block).build());
    }

}