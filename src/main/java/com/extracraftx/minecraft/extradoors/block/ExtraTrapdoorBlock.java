package com.extracraftx.minecraft.extradoors.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.TrapdoorBlock;

public class ExtraTrapdoorBlock extends TrapdoorBlock{

    public ExtraTrapdoorBlock(Settings settings){
        super(settings);
    }

    public ExtraTrapdoorBlock(Block block){
        super(FabricBlockSettings.copy(block).build());
    }

}