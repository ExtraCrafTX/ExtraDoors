package com.extracraftx.minecraft.extradoors;

import net.fabricmc.api.ModInitializer;

import com.extracraftx.minecraft.extradoors.block.Blocks;
import com.extracraftx.minecraft.extradoors.sound.Sounds;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtraDoors implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "extradoors";
    public static final String MOD_NAME = "ExtraDoors";

    @Override
    public void onInitialize() {
        Blocks.registerBlocks();
        ItemGroups.createItemGroups();
        Sounds.registerSounds();
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}
