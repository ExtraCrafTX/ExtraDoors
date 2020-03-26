package com.extracraftx.minecraft.extradoors.block;

import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;

public class ExtraDoorBlock extends DoorBlock{

    public ExtraDoorBlock(Settings settings){
        super(settings.nonOpaque());
    }

    public ExtraDoorBlock(Block block){
        super(Settings.copy(block).nonOpaque());
    }

}