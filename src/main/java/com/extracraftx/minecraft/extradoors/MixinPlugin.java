package com.extracraftx.minecraft.extradoors;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
    static boolean LITHIUM = false;
    @Override
    public void onLoad(String mixinPackage) {
        LITHIUM = FabricLoader.getInstance().isModLoaded("lithium");
        if (LITHIUM) {
            ExtraDoors.log(Level.INFO,"Lithium detected, loading compatibility mixin");
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
            if (LITHIUM && mixinClassName.equals("com.extracraftx.minecraft.extradoors.mixin.LandPathNodeMakerMixin")) {
                return false;
            }
            if (!LITHIUM && mixinClassName.equals("com.extracraftx.minecraft.extradoors.mixin.LithiumLandPathNodeMakerMixin")) {
                return false;
            }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        ExtraDoors.log(Level.INFO, "Mixed into: " + targetClassName);
    }
}
