package com.ponyo.witcheryrewoven.item.ModItems;


import com.ponyo.witcheryrewoven.WitcheryRewoven;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PlantItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WitcheryRewoven.MODID);


    public static final DeferredItem<Item> ATTUNED_STONE = ITEMS.register("attuned_stone",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SOFT_CLAY_JAR = ITEMS.register("soft_clay_jar",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CLAY_JAR = ITEMS.register("clay_jar",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
