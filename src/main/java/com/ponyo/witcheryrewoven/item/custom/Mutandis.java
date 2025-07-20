package com.ponyo.witcheryrewoven.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class Mutandis extends Item {
        public static final Map<Block, Block> MUTANDIS_MAP =
                //make sure to use .get() after for calling deferred blocks (modded blocks)
                Map.of(
                        Blocks.GRASS_BLOCK, Blocks.MYCELIUM,
                        Blocks.MYCELIUM, Blocks.GRASS_BLOCK
                );

    public Mutandis(Properties properties) {
        super(properties);
        // just type "override" to preview all available overrides here
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(MUTANDIS_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                // ONLY ON SERVER!
                level.setBlockAndUpdate(context.getClickedPos(), MUTANDIS_MAP.get(clickedBlock).defaultBlockState());

                level.playSound(null, context.getClickedPos(), SoundEvents.BONE_MEAL_USE, SoundSource.PLAYERS);

                context.getItemInHand().shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
