package com.ponyo.witcheryrewoven.item.custom;

import com.ponyo.witcheryrewoven.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Chalk extends Item {
    public Chalk(Properties properties) { super(properties); }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Direction face = context.getClickedFace();

        if (face != Direction.UP) {
            return InteractionResult.FAIL;
        }

        BlockPos placePos = clickedPos.above();

        //Player player = context.getPlayer();
        //ItemStack itemStack = context.getItemInHand();

        if(!level.getBlockState(placePos).canBeReplaced()){
            return  InteractionResult.FAIL;
        }

        if(!level.getBlockState(clickedPos).isFaceSturdy(level, clickedPos, Direction.UP)) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide) {
            level.setBlock(placePos, ModBlocks.LOG_ROWAN.get().defaultBlockState(),3);
            // we pass it null to play for all nearby players, but you can pass specific players (hallucination curse?)
            context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            level.playSound(null,placePos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS);


        }

        return InteractionResult.SUCCESS;
    }
}
