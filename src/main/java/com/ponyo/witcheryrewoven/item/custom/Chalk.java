package com.ponyo.witcheryrewoven.item.custom;

import com.ponyo.witcheryrewoven.block.ModBlocks;
import com.ponyo.witcheryrewoven.block.custom.GlyphBlock;
import com.ponyo.witcheryrewoven.item.ModItems.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.common.Mod;

public class Chalk extends Item {
    public Chalk(Properties properties) { super(properties); }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Direction face = context.getClickedFace();
        Item usedItem = context.getItemInHand().getItem();

        if (face != Direction.UP) {
            return InteractionResult.FAIL;
        }

        BlockPos placePos = clickedPos.above();

        if(!level.getBlockState(placePos).canBeReplaced()){
            return  InteractionResult.FAIL;
        }

        if(!level.getBlockState(clickedPos).isFaceSturdy(level, clickedPos, Direction.UP)) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide) {
            int variant = level.getRandom().nextInt(12); // 0–11

            if (usedItem == ModItems.WHITE_CHALK.get()) {
                level.setBlock(placePos, ModBlocks.WHITE_GLYPH.get().defaultBlockState().setValue(GlyphBlock.VARIANT, variant), 3);
            } else if (usedItem == ModItems.GOLDEN_CHALK.get()) {
                    level.setBlock(placePos, ModBlocks.GOLDEN_GLYPH.get().defaultBlockState().setValue(GlyphBlock.VARIANT, 0), 3);
            } else if (usedItem == ModItems.RED_CHALK.get()) {
                level.setBlock(placePos, ModBlocks.RED_GLYPH.get().defaultBlockState().setValue(GlyphBlock.VARIANT, variant), 3);
            } else if (usedItem == ModItems.PURPLE_CHALK.get()) {
                level.setBlock(placePos, ModBlocks.PURPLE_GLYPH.get().defaultBlockState().setValue(GlyphBlock.VARIANT, variant),3);
            }
            context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

            // we pass it null to play for all nearby players, but you can pass specific players (hallucination curse?)
            level.playSound(null,placePos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS);
        }



        return InteractionResult.SUCCESS;
    }
}
