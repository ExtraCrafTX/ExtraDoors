package com.extracraftx.minecraft.extradoors.block;

import static net.minecraft.block.Blocks.IRON_DOOR;
import static net.minecraft.block.Blocks.OAK_DOOR;
import static net.minecraft.block.Blocks.SPRUCE_DOOR;
import static net.minecraft.block.Blocks.BIRCH_DOOR;
import static net.minecraft.block.Blocks.JUNGLE_DOOR;
import static net.minecraft.block.Blocks.ACACIA_DOOR;
import static net.minecraft.block.Blocks.DARK_OAK_DOOR;

import static net.minecraft.block.Blocks.IRON_TRAPDOOR;

import com.extracraftx.minecraft.extradoors.ExtraDoors;

import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static GoldDoorBlock GOLD_DOOR;
    public static GoldTrapdoorBlock GOLD_TRAPDOOR;
    public static BambooDoorBlock BAMBOO_DOOR;
    public static BambooTrapdoorBlock BAMBOO_TRAPDOOR;
    public static GlassDoorBlock GLASS_DOOR;
    public static GlassTrapdoorBlock GLASS_TRAPDOOR;

    public static DoorBlock IRON_GLASS_DOOR;
    public static DoorBlock OAK_GLASS_DOOR;
    public static DoorBlock SPRUCE_GLASS_DOOR;
    public static DoorBlock BIRCH_GLASS_DOOR;
    public static DoorBlock JUNGLE_GLASS_DOOR;
    public static DoorBlock ACACIA_GLASS_DOOR;
    public static DoorBlock DARK_OAK_GLASS_DOOR;
    public static GoldDoorBlock GOLD_GLASS_DOOR;

    public static TrapdoorBlock IRON_GLASS_TRAPDOOR;

    public static void registerBlocks() {
        GOLD_DOOR = registerBlock("gold_door", new GoldDoorBlock(), ItemGroup.REDSTONE);
        GOLD_TRAPDOOR = registerBlock("gold_trapdoor", new GoldTrapdoorBlock(), ItemGroup.REDSTONE);
        BAMBOO_DOOR = registerBlock("bamboo_door", new BambooDoorBlock(), ItemGroup.DECORATIONS);
        BAMBOO_TRAPDOOR = registerBlock("bamboo_trapdoor", new BambooTrapdoorBlock(), ItemGroup.DECORATIONS);
        GLASS_DOOR = registerBlock("glass_door", new GlassDoorBlock(), ItemGroup.REDSTONE);
        GLASS_TRAPDOOR = registerBlock("glass_trapdoor", new GlassTrapdoorBlock(), ItemGroup.REDSTONE);

        IRON_GLASS_DOOR = registerBlock("iron_glass_door", new ExtraDoorBlock(IRON_DOOR), ItemGroup.REDSTONE);
        OAK_GLASS_DOOR = registerBlock("oak_glass_door", new ExtraDoorBlock(OAK_DOOR), ItemGroup.REDSTONE);
        SPRUCE_GLASS_DOOR = registerBlock("spruce_glass_door", new ExtraDoorBlock(SPRUCE_DOOR), ItemGroup.REDSTONE);
        BIRCH_GLASS_DOOR = registerBlock("birch_glass_door", new ExtraDoorBlock(BIRCH_DOOR), ItemGroup.REDSTONE);
        JUNGLE_GLASS_DOOR = registerBlock("jungle_glass_door", new ExtraDoorBlock(JUNGLE_DOOR), ItemGroup.REDSTONE);
        ACACIA_GLASS_DOOR = registerBlock("acacia_glass_door", new ExtraDoorBlock(ACACIA_DOOR), ItemGroup.REDSTONE);
        DARK_OAK_GLASS_DOOR = registerBlock("dark_oak_glass_door", new ExtraDoorBlock(DARK_OAK_DOOR), ItemGroup.REDSTONE);
        
        GOLD_GLASS_DOOR = registerBlock("gold_glass_door", new GoldDoorBlock(), ItemGroup.REDSTONE);

        IRON_GLASS_TRAPDOOR = registerBlock("iron_glass_trapdoor", new ExtraTrapdoorBlock(IRON_TRAPDOOR), ItemGroup.REDSTONE);
    }

    private static <T extends Block> T registerBlock(String name, T block, ItemGroup group) {
        Identifier id = new Identifier(ExtraDoors.MOD_ID, name);
        T registered = Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(registered, new Item.Settings().group(group)));
        return registered;
    }

}