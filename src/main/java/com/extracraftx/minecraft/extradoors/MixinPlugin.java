package com.extracraftx.minecraft.extradoors;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.logging.log4j.Level;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.util.version.SemanticVersionImpl;
import net.fabricmc.loader.util.version.SemanticVersionPredicateParser;
import net.fabricmc.loader.api.VersionParsingException;

public class MixinPlugin implements IMixinConfigPlugin {
    private static Predicate<SemanticVersionImpl> LITHIUM_API;
    private static Predicate<SemanticVersionImpl> LITHIUM_SEP;
    private static Predicate<SemanticVersionImpl> LITHIUM_MIX;

    static {
        try {
            LITHIUM_API = SemanticVersionPredicateParser.create(">=0.6.0");
            LITHIUM_SEP = SemanticVersionPredicateParser.create(">=0.5.5");
            LITHIUM_MIX = SemanticVersionPredicateParser.create(">=0.5.0");
        } catch (VersionParsingException e) {
            e.printStackTrace();
        }
    }

    public static LithiumMixinType LITHIUM_MIXIN;

    @Override
    public void onLoad(String mixinPackage) {
        boolean lithium = FabricLoader.getInstance().isModLoaded("lithium");
        if(lithium){
            SemanticVersionImpl version = (SemanticVersionImpl) FabricLoader.getInstance().getModContainer("lithium").get().getMetadata().getVersion();
            if(LITHIUM_API.test(version)){
                LITHIUM_MIXIN = LithiumMixinType.API;
                ExtraDoors.log(Level.INFO, "Running with lithium, disabling pathing mixin");
            }else if(LITHIUM_SEP.test(version)){
                LITHIUM_MIXIN = LithiumMixinType.SEPARATE;
                ExtraDoors.log(Level.INFO, "Running with an older version of lithium, mixing in to its pathfinding");
            }else if(LITHIUM_MIX.test(version)){
                LITHIUM_MIXIN = LithiumMixinType.DIRECT;
                ExtraDoors.log(Level.INFO, "Running with an older version of lithium, enabling lithium compatibility mixin");
            }else{
                LITHIUM_MIXIN = LithiumMixinType.NONE;
                ExtraDoors.log(Level.INFO, "Running with an older version of lithium, no compatibility fixes needed");
            }
        }else{
            LITHIUM_MIXIN = LithiumMixinType.NONE;
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClass, String mixinClass) {
        if(mixinClass.equals("com.extracraftx.minecraft.extradoors.mixin.LandPathNodeMakerMixin")){
            return LITHIUM_MIXIN == LithiumMixinType.NONE;
        }
        if(mixinClass.equals("com.extracraftx.minecraft.extradoors.mixin.LandPathNodeMakerLithiumCompatMixin")){
            return LITHIUM_MIXIN == LithiumMixinType.DIRECT;
        }
        if(mixinClass.equals("com.extracraftx.minecraft.extradoors.mixin.LandPathNodeMakerLithiumMixin")){
            return LITHIUM_MIXIN == LithiumMixinType.SEPARATE;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    private static enum LithiumMixinType{
        NONE, DIRECT, SEPARATE, API
    }

}
