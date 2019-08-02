package com.extracraftx.minecraft.extradoors.block;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static GoldDoorBlock GOLD_DOOR;
    public static GoldTrapdoorBlock GOLD_TRAPDOOR;
    public static BambooDoorBlock BAMBOO_DOOR;

    public static void registerBlocks() {
        Identifier gold_door = new Identifier("extradoors", "gold_door");
        GOLD_DOOR = Registry.register(Registry.BLOCK, gold_door, new GoldDoorBlock());
        Registry.register(Registry.ITEM, gold_door,
                new BlockItem(GOLD_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)));

        Identifier gold_trapdoor = new Identifier("extradoors", "gold_trapdoor");
        GOLD_TRAPDOOR = Registry.register(Registry.BLOCK, gold_trapdoor, new GoldTrapdoorBlock());
        Registry.register(Registry.ITEM, gold_trapdoor,
                new BlockItem(GOLD_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE)));
                
        Identifier bamboo_door = new Identifier("extradoors", "bamboo_door");
        BAMBOO_DOOR = Registry.register(Registry.BLOCK, bamboo_door, new BambooDoorBlock());
        Registry.register(Registry.ITEM, bamboo_door,
                new BlockItem(BAMBOO_DOOR, new Item.Settings().group(ItemGroup.DECORATIONS)));
    }

}