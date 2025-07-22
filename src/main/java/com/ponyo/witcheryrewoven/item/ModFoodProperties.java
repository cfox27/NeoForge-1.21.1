package com.ponyo.witcheryrewoven.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public class ModFoodProperties {
    public static final FoodProperties GARLIC = new FoodProperties.Builder().nutrition(1).saturationModifier(.25f)
            .alwaysEdible().effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1.0f).build();
    public static final FoodProperties ROWAN_BERRIES = new FoodProperties.Builder().nutrition(1).saturationModifier(.25f)
            .alwaysEdible().effect(() -> new MobEffectInstance(MobEffects.POISON,50,0),1.0f).build();
    public static final FoodProperties ROWAN_BERRY_PIE = new FoodProperties.Builder().nutrition(8).saturationModifier(.5f)
            .build();
}
