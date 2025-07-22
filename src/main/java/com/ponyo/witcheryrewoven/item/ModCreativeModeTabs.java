package com.ponyo.witcheryrewoven.item;

import com.ponyo.witcheryrewoven.WitcheryRewoven;
import com.ponyo.witcheryrewoven.block.MiscBlocks;
import com.ponyo.witcheryrewoven.item.ModItems.MiscItems;
import com.ponyo.witcheryrewoven.item.ModItems.PlantItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WitcheryRewoven.MODID);

    public static final Supplier<CreativeModeTab> WITCHERYREWOVEN_ITEMS_TAB = CREATIVE_MODE_TAB.register("witcheryrewoven_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MiscItems.TAGLOCK_KIT.get()))
                    .title(Component.translatable("creativetab.witcheryrewoven.witcheryrewoven_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(MiscItems.TAGLOCK_KIT);
                        output.accept(MiscItems.BONE_NEEDLE);
                        output.accept(MiscItems.MUTANDIS);
                        output.accept(MiscItems.MUTANDIS_EXTREMIS);
                        output.accept(PlantItems.ATTUNED_STONE);
                        output.accept(PlantItems.CLAY_JAR);
                        output.accept(PlantItems.SOFT_CLAY_JAR);
                        output.accept(MiscBlocks.ROWAN_LEAVES);
                        output.accept(MiscBlocks.ROWAN_LOG);
                        output.accept(MiscBlocks.ALDER_LEAVES);
                        output.accept(MiscBlocks.ALDER_LOG);
                        output.accept(MiscItems.WHITE_CHALK);
                        output.accept(MiscItems.GOLDEN_CHALK);
                        output.accept(MiscItems.RED_CHALK);
                        output.accept(MiscItems.PURPLE_CHALK);
                        output.accept(MiscItems.GARLIC);
                        output.accept(MiscItems.ROWAN_BERRIES);
                        output.accept(MiscItems.ROWAN_BERRY_PIE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
