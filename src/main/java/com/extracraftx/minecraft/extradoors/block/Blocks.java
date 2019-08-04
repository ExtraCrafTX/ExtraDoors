package com.extracraftx.minecraft.extradoors.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static GoldDoorBlock GOLD_DOOR;
    public static GoldTrapdoorBlock GOLD_TRAPDOOR;
    public static BambooDoorBlock BAMBOO_DOOR;

    public static DoorBlock ACACIA_GLASS_DOOR;

    public static void registerBlocks() {
        GOLD_DOOR = registerBlock("gold_door", new GoldDoorBlock(), ItemGroup.REDSTONE);
        
        GOLD_TRAPDOOR = registerBlock("gold_trapdoor", new GoldTrapdoorBlock(), ItemGroup.REDSTONE);

        BAMBOO_DOOR = registerBlock("bamboo_door", new BambooDoorBlock(), ItemGroup.DECORATIONS);

        ACACIA_GLASS_DOOR = registerBlock("acacia_glass_door",
                new ExtraDoorBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.ACACIA_DOOR).build()),
                ItemGroup.REDSTONE);
    }

    private static <T extends Block> T registerBlock(String name, T block, ItemGroup group) {
        Identifier id = new Identifier("extradoors", name);
        T registered = Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(registered, new Item.Settings().group(group)));
        return registered;
    }

}