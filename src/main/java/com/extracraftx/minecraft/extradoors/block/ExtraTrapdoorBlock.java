package com.extracraftx.minecraft.extradoors.block;

import net.minecraft.block.Block;
import net.minecraft.block.TrapdoorBlock;

public class ExtraTrapdoorBlock extends TrapdoorBlock{

    public ExtraTrapdoorBlock(Settings settings){
        super(settings.nonOpaque());
    }

    public ExtraTrapdoorBlock(Block block){
        super(Settings.copy(block).nonOpaque());
    }

}