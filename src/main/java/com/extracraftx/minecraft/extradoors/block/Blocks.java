package com.extracraftx.minecraft.extradoors.block;

import static net.minecraft.block.Blocks.IRON_DOOR;
import static net.minecraft.block.Blocks.OAK_DOOR;
import static net.minecraft.block.Blocks.SPRUCE_DOOR;
import static net.minecraft.block.Blocks.BIRCH_DOOR;
import static net.minecraft.block.Blocks.JUNGLE_DOOR;
import static net.minecraft.block.Blocks.ACACIA_DOOR;
import static net.minecraft.block.Blocks.DARK_OAK_DOOR;
import static net.minecraft.block.Blocks.CRIMSON_DOOR;
import static net.minecraft.block.Blocks.WARPED_DOOR;

import static net.minecraft.block.Blocks.IRON_TRAPDOOR;
import static net.minecraft.block.Blocks.OAK_TRAPDOOR;
import static net.minecraft.block.Blocks.SPRUCE_TRAPDOOR;

import java.util.LinkedList;

import static net.minecraft.block.Blocks.BIRCH_TRAPDOOR;
import static net.minecraft.block.Blocks.JUNGLE_TRAPDOOR;
import static net.minecraft.block.Blocks.ACACIA_TRAPDOOR;
import static net.minecraft.block.Blocks.DARK_OAK_TRAPDOOR;
import static net.minecraft.block.Blocks.CRIMSON_TRAPDOOR;
import static net.minecraft.block.Blocks.WARPED_TRAPDOOR;

import com.extracraftx.minecraft.extradoors.ExtraDoors;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    private static LinkedList<Block> ALL_BLOCKS = new LinkedList<>();

    public static GoldDoorBlock GOLD_DOOR;
    public static GoldTrapdoorBlock GOLD_TRAPDOOR;
    public static BambooDoorBlock BAMBOO_DOOR;
    public static BambooTrapdoorBlock BAMBOO_TRAPDOOR;
    public static GlassDoorBlock GLASS_DOOR;
    public static GlassTrapdoorBlock GLASS_TRAPDOOR;
    public static PurpurDoorBlock PURPUR_DOOR;
    public static PurpurTrapdoorBlock PURPUR_TRAPDOOR;

    public static DoorBlock IRON_GLASS_DOOR;
    public static DoorBlock OAK_GLASS_DOOR;
    public static DoorBlock SPRUCE_GLASS_DOOR;
    public static DoorBlock BIRCH_GLASS_DOOR;
    public static DoorBlock JUNGLE_GLASS_DOOR;
    public static DoorBlock ACACIA_GLASS_DOOR;
    public static DoorBlock DARK_OAK_GLASS_DOOR;
    public static DoorBlock CRIMSON_GLASS_DOOR;
    public static DoorBlock WARPED_GLASS_DOOR;
    public static GoldDoorBlock GOLD_GLASS_DOOR;
    public static PurpurDoorBlock PURPUR_GLASS_DOOR;

    public static TrapdoorBlock OAK_GLASS_TRAPDOOR;
    public static TrapdoorBlock SPRUCE_GLASS_TRAPDOOR;
    public static TrapdoorBlock BIRCH_GLASS_TRAPDOOR;
    public static TrapdoorBlock JUNGLE_GLASS_TRAPDOOR;
    public static TrapdoorBlock ACACIA_GLASS_TRAPDOOR;
    public static TrapdoorBlock DARK_OAK_GLASS_TRAPDOOR;
    public static TrapdoorBlock CRIMSON_GLASS_TRAPDOOR;
    public static TrapdoorBlock WARPED_GLASS_TRAPDOOR;
    public static TrapdoorBlock IRON_GLASS_TRAPDOOR;
    public static GoldTrapdoorBlock GOLD_GLASS_TRAPDOOR;
    public static PurpurTrapdoorBlock PURPUR_GLASS_TRAPDOOR;

    public static void registerBlocks() {
        GOLD_DOOR = registerDoorBlock("gold_door", new GoldDoorBlock(), ItemGroup.REDSTONE);
        GOLD_TRAPDOOR = registerTrapdoorBlock("gold_trapdoor", new GoldTrapdoorBlock(), ItemGroup.REDSTONE);
        BAMBOO_DOOR = registerDoorBlock("bamboo_door", new BambooDoorBlock(), ItemGroup.DECORATIONS);
        BAMBOO_TRAPDOOR = registerTrapdoorBlock("bamboo_trapdoor", new BambooTrapdoorBlock(), ItemGroup.DECORATIONS);
        GLASS_DOOR = registerDoorBlock("glass_door", new GlassDoorBlock(), ItemGroup.REDSTONE);
        GLASS_TRAPDOOR = registerTrapdoorBlock("glass_trapdoor", new GlassTrapdoorBlock(), ItemGroup.REDSTONE);
        PURPUR_DOOR = registerDoorBlock("purpur_door", new PurpurDoorBlock(), ItemGroup.REDSTONE);
        PURPUR_TRAPDOOR = registerTrapdoorBlock("purpur_trapdoor", new PurpurTrapdoorBlock(), ItemGroup.REDSTONE);

        IRON_GLASS_DOOR = copyAndRegisterDoor(IRON_DOOR, "iron_glass_door", ItemGroup.REDSTONE);
        OAK_GLASS_DOOR = copyAndRegisterDoor(OAK_DOOR, "oak_glass_door", ItemGroup.REDSTONE);
        SPRUCE_GLASS_DOOR = copyAndRegisterDoor(SPRUCE_DOOR, "spruce_glass_door", ItemGroup.REDSTONE);
        BIRCH_GLASS_DOOR = copyAndRegisterDoor(BIRCH_DOOR, "birch_glass_door", ItemGroup.REDSTONE);
        JUNGLE_GLASS_DOOR = copyAndRegisterDoor(JUNGLE_DOOR, "jungle_glass_door", ItemGroup.REDSTONE);
        ACACIA_GLASS_DOOR = copyAndRegisterDoor(ACACIA_DOOR, "acacia_glass_door", ItemGroup.REDSTONE);
        DARK_OAK_GLASS_DOOR = copyAndRegisterDoor(DARK_OAK_DOOR, "dark_oak_glass_door", ItemGroup.REDSTONE);
        CRIMSON_GLASS_DOOR = copyAndRegisterDoor(CRIMSON_DOOR, "crimson_glass_door", ItemGroup.REDSTONE);
        WARPED_GLASS_DOOR = copyAndRegisterDoor(WARPED_DOOR, "warped_glass_door", ItemGroup.REDSTONE);

        GOLD_GLASS_DOOR = registerDoorBlock("gold_glass_door", new GoldDoorBlock(), ItemGroup.REDSTONE);
        PURPUR_GLASS_DOOR = registerDoorBlock("purpur_glass_door", new PurpurDoorBlock(), ItemGroup.REDSTONE);

        OAK_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(OAK_TRAPDOOR, "oak_glass_trapdoor", ItemGroup.REDSTONE);
        SPRUCE_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(SPRUCE_TRAPDOOR, "spruce_glass_trapdoor", ItemGroup.REDSTONE);
        BIRCH_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(BIRCH_TRAPDOOR, "birch_glass_trapdoor", ItemGroup.REDSTONE);
        JUNGLE_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(JUNGLE_TRAPDOOR, "jungle_glass_trapdoor", ItemGroup.REDSTONE);
        ACACIA_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(ACACIA_TRAPDOOR, "acacia_glass_trapdoor", ItemGroup.REDSTONE);
        DARK_OAK_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(DARK_OAK_TRAPDOOR, "dark_oak_glass_trapdoor", ItemGroup.REDSTONE);
        CRIMSON_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(CRIMSON_TRAPDOOR, "crimson_glass_trapdoor", ItemGroup.REDSTONE);
        WARPED_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(WARPED_TRAPDOOR, "warped_glass_trapdoor", ItemGroup.REDSTONE);
        IRON_GLASS_TRAPDOOR = copyAndRegisterTrapdoor(IRON_TRAPDOOR, "iron_glass_trapdoor", ItemGroup.REDSTONE);

        GOLD_GLASS_TRAPDOOR = registerTrapdoorBlock("gold_glass_trapdoor", new GoldTrapdoorBlock(), ItemGroup.REDSTONE);
        PURPUR_GLASS_TRAPDOOR = registerTrapdoorBlock("purpur_glass_trapdoor", new PurpurTrapdoorBlock(), ItemGroup.REDSTONE);
    }
    
    public static void setRenderLayers(){
        for(Block block : ALL_BLOCKS)
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }

    public static ExtraDoorBlock copyAndRegisterDoor(Block block, Identifier id, ItemGroup group) {
        return registerDoorBlock(id, new ExtraDoorBlock(block), group);
    }

    public static ExtraTrapdoorBlock copyAndRegisterTrapdoor(Block block, Identifier id, ItemGroup group) {
        return registerTrapdoorBlock(id, new ExtraTrapdoorBlock(block), group);
    }

    public static <T extends DoorBlock> T registerDoorBlock(Identifier id, T block, ItemGroup group) {
        T registered = Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(registered, new Item.Settings().group(group)));
        ALL_BLOCKS.add(registered);
        return registered;
    }

    public static <T extends TrapdoorBlock> T registerTrapdoorBlock(Identifier id, T block, ItemGroup group) {
        T registered = Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(registered, new Item.Settings().group(group)));
        ALL_BLOCKS.add(registered);
        return registered;
    }

    private static ExtraDoorBlock copyAndRegisterDoor(Block block, String name, ItemGroup group) {
        return registerDoorBlock(name, new ExtraDoorBlock(block), group);
    }

    private static ExtraTrapdoorBlock copyAndRegisterTrapdoor(Block block, String name, ItemGroup group) {
        return registerTrapdoorBlock(name, new ExtraTrapdoorBlock(block), group);
    }

    private static <T extends DoorBlock> T registerDoorBlock(String name, T block, ItemGroup group) {
        Identifier id = new Identifier(ExtraDoors.MOD_ID, name);
        return registerDoorBlock(id, block, group);
    }

    private static <T extends TrapdoorBlock> T registerTrapdoorBlock(String name, T block, ItemGroup group) {
        Identifier id = new Identifier(ExtraDoors.MOD_ID, name);
        return registerTrapdoorBlock(id, block, group);
    }

}