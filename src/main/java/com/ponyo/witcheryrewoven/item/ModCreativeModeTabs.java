package com.ponyo.witcheryrewoven.item;

import com.ponyo.witcheryrewoven.WitcheryRewoven;
import com.ponyo.witcheryrewoven.block.ModBlocks;
import com.ponyo.witcheryrewoven.item.ModItems.ModItems;
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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TAGLOCK_KIT.get()))
                    .title(Component.translatable("creativetab.witcheryrewoven.witcheryrewoven_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.TAGLOCK_KIT);
                        output.accept(ModItems.BONE_NEEDLE);
                        output.accept(ModItems.MUTANDIS);
                        output.accept(PlantItems.ATTUNED_STONE);
                        output.accept(PlantItems.CLAY_JAR);
                        output.accept(PlantItems.SOFT_CLAY_JAR);
                        output.accept(ModBlocks.LEAVES_ROWAN);
                        output.accept(ModBlocks.LOG_ROWAN);
                        output.accept(ModBlocks.LEAVES_ALDER);
                        output.accept(ModBlocks.LOG_ALDER);
                        output.accept(ModItems.WHITE_CHALK);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
