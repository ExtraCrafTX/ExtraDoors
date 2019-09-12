package com.extracraftx.minecraft.extradoors.tags;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Tags{
    public static Tag<Block> INTERACTABLE_DOORS;

    public static void registerTags(){
        INTERACTABLE_DOORS = TagRegistry.block(new Identifier("extradoors", "interactable_doors"));
    }
}