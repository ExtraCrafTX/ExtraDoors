package com.extracraftx.minecraft.extradoors;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import com.extracraftx.minecraft.extradoors.block.Blocks;
import com.extracraftx.minecraft.extradoors.sound.Sounds;
import com.extracraftx.minecraft.extradoors.tags.Tags;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtraDoors implements ModInitializer, ClientModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "extradoors";
    public static final String MOD_NAME = "ExtraDoors";

    public static boolean COUPLINGS;

    @Override
    public void onInitialize() {
        Blocks.registerBlocks();
        ItemGroups.createItemGroups();
        Sounds.registerSounds();
        Tags.registerTags();

        COUPLINGS = FabricLoader.getInstance().isModLoaded("couplings");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    @Override
    public void onInitializeClient() {
        Blocks.setRenderLayers();
    }

}
