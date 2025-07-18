package com.ponyo.witcheryrewoven.item.ModItems;

import com.ponyo.witcheryrewoven.WitcheryRewoven;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
