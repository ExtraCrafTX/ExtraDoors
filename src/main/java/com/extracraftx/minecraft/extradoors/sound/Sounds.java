package com.extracraftx.minecraft.extradoors.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds{
    public static SoundEvent LOCKED;

    public static void registerSounds(){
        Identifier locked = new Identifier("extradoors", "locked");
        LOCKED = Registry.register(Registry.SOUND_EVENT, locked, new SoundEvent(locked));
    }

}