package com.extracraftx.minecraft.extradoors.block;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static GoldDoorBlock GOLD_DOOR;

    public static void registerBlocks() {
        GOLD_DOOR = Registry.register(Registry.BLOCK, new Identifier("extradoors", "gold_door"), new GoldDoorBlock());
        Registry.register(Registry.ITEM, new Identifier("extradoors", "gold_door"),
                new BlockItem(GOLD_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)));
    }

}