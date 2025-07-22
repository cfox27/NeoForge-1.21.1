package com.ponyo.witcheryrewoven.block;

import com.ponyo.witcheryrewoven.WitcheryRewoven;
import com.ponyo.witcheryrewoven.block.custom.GlyphBlock;
import com.ponyo.witcheryrewoven.item.ModItems.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(WitcheryRewoven.MODID);

    public static final DeferredBlock<Block> ALDER_LOG = registerBlock(
            "alder_log",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .strength(2.0F)
                            .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> ROWAN_LOG = registerBlock(
            "rowan_log",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .strength(2.0F)
                            .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> ROWAN_LEAVES = registerBlock(
            "rowan_leaves",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .strength(.2F)
                            .sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> ALDER_LEAVES = registerBlock(
            "alder_leaves",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .strength(.2F)
                            .sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> GOLDEN_GLYPH = registerBlock(
            "golden_glyph",
            () -> new GlyphBlock(
                    BlockBehaviour.Properties.of()
                            .strength(.1F)));

    public static final DeferredBlock<Block> WHITE_GLYPH = registerBlock(
            "white_glyph",
            () -> new GlyphBlock(
                    BlockBehaviour.Properties.of()
                            .strength(.1F)));

    public static final DeferredBlock<Block> RED_GLYPH = registerBlock(
            "red_glyph",
            () -> new GlyphBlock(
                    BlockBehaviour.Properties.of()
                            .strength(.1F)));

    public static final DeferredBlock<Block> PURPLE_GLYPH = registerBlock(
            "purple_glyph",
            () -> new GlyphBlock(
                    BlockBehaviour.Properties.of()
                            .strength(.1F)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        // Create and register this block
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        // Then register the block ITEM associated with it
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
