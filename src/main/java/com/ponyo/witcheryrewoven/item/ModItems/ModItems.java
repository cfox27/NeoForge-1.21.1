package com.ponyo.witcheryrewoven.item.ModItems;

import com.ponyo.witcheryrewoven.WitcheryRewoven;
import com.ponyo.witcheryrewoven.item.ModFoodProperties;
import com.ponyo.witcheryrewoven.item.custom.Chalk;
import com.ponyo.witcheryrewoven.item.custom.Mutandis;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    //A list of everything you want to register, must register under MODID

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WitcheryRewoven.MODID);

    public static final DeferredItem<Item> BONE_NEEDLE = ITEMS.register("bone_needle",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TAGLOCK_KIT = ITEMS.register("taglock_kit",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WHITE_CHALK = ITEMS.register("white_chalk",
            () -> new Chalk(new Item.Properties().stacksTo(1).durability(64)));
    public static final DeferredItem<Item> GOLDEN_CHALK = ITEMS.register("golden_chalk",
            () -> new Chalk(new Item.Properties().stacksTo(1).durability(32)));
    public static final DeferredItem<Item> RED_CHALK = ITEMS.register("red_chalk",
            () -> new Chalk(new Item.Properties().stacksTo(1).durability(64)));
    public static final DeferredItem<Item> PURPLE_CHALK = ITEMS.register("purple_chalk",
            () -> new Chalk(new Item.Properties().stacksTo(1).durability(64)));
    public static final DeferredItem<Item> MUTANDIS = ITEMS.register("mutandis",
            () -> new Mutandis(new Item.Properties()));
    public static final DeferredItem<Item> GARLIC = ITEMS.register("garlic",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GARLIC)));
    public static final DeferredItem<Item> ROWAN_BERRIES = ITEMS.register("rowan_berries",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROWAN_BERRIES)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
