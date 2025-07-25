package com.ponyo.witcheryrewoven;

import com.ponyo.witcheryrewoven.block.MiscBlocks;
import com.ponyo.witcheryrewoven.item.ModCreativeModeTabs;
import com.ponyo.witcheryrewoven.item.ModItems.MiscItems;
import com.ponyo.witcheryrewoven.item.ModItems.PlantItems;
import com.ponyo.witcheryrewoven.sound.MiscSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(WitcheryRewoven.MODID)
public class WitcheryRewoven {
    public static final String MODID = "witcheryrewoven";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public WitcheryRewoven(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        MiscItems.register(modEventBus);
        PlantItems.register(modEventBus);
        MiscBlocks.register(modEventBus);

        MiscSounds.register(modEventBus);

        // Register the item to a creative tab
        // modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    /*
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            /* added own witchery rewoven tab!
            event.accept(ModItems.BONE_NEEDLE);
            event.accept(PlantItems.ATTUNED_STONE);
            event.accept(PlantItems.SOFT_CLAY_JAR);
            event.accept(PlantItems.CLAY_JAR);
            event.accept(ModItems.TAGLOCK_KIT);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.LOG_ALDER);
            event.accept(ModBlocks.LOG_ROWAN);
            event.accept(ModBlocks.LEAVES_ROWAN);
            event.accept(ModBlocks.LEAVES_ALDER);
        }

        }
    }
    */

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
