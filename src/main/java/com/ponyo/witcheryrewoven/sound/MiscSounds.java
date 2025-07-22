package com.ponyo.witcheryrewoven.sound;

import com.ponyo.witcheryrewoven.WitcheryRewoven;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MiscSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS=
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, WitcheryRewoven.MODID);

    public static final Supplier<SoundEvent> CHALK_DRAW = registerSoundEvent("chalk_draw");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(WitcheryRewoven.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
