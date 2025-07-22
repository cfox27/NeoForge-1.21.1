package com.ponyo.witcheryrewoven.item.custom;

import com.ponyo.witcheryrewoven.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

//Defining a custom item class for "Mutandis"
public class Mutandis extends Item {

    //creates a random class to be referenced when determining integers
    private static final Random RANDOM = new Random();

    //Creates the possibility to make a list of weighted mutations
    public record WeightedMutation(Block block, int weight) {}

    //Creates a list of possible Plant Mutations
    private static  final List<WeightedMutation> PLANT_MUTATIONS = List.of(
            //There are 30 normal minecraft blocks... to not skew the possibility of getting Witchery blocks, should increase weight
            /*
            The XXXX's are there to remember to switch it to whatever location the blocks get stored in
            new WeightedMutation(XXXXXXXXXX.ROWAN_SAPLING, 1),
            new WeightedMutation(XXXXXXXXXX.ALDER_SAPLING, 1),
            new WeightedMutation(XXXXXXXXXX.HAWTHORN_SAPLING, 1),
            new WeightedMutation(XXXXXXXXXX.GLINT_WEED, 1),
            new WeightedMutation(XXXXXXXXXX.SPANISH_MOSS, 1),
            new WeightedMutation(XXXXXXXXXX.EMBER_MOSS, 1),
            */
            new WeightedMutation(Blocks.SHORT_GRASS, 1),
            new WeightedMutation(Blocks.LILY_PAD, 1),
            new WeightedMutation(Blocks.VINE, 1),
            new WeightedMutation(Blocks.BROWN_MUSHROOM, 1),
            new WeightedMutation(Blocks.RED_MUSHROOM, 1),
            new WeightedMutation(Blocks.OAK_SAPLING, 1),
            new WeightedMutation(Blocks.BIRCH_SAPLING, 1),
            new WeightedMutation(Blocks.SPRUCE_SAPLING, 1),
            new WeightedMutation(Blocks.JUNGLE_SAPLING, 1),
            new WeightedMutation(Blocks.ACACIA_SAPLING, 1),
            new WeightedMutation(Blocks.DARK_OAK_SAPLING, 1),
            new WeightedMutation(Blocks.MANGROVE_PROPAGULE, 1),
            new WeightedMutation(Blocks.AZALEA, 1),
            new WeightedMutation(Blocks.FLOWERING_AZALEA, 1),
            new WeightedMutation(Blocks.CHERRY_SAPLING, 1),
            new WeightedMutation(Blocks.BIG_DRIPLEAF, 1),
            new WeightedMutation(Blocks.PEONY, 1),
            new WeightedMutation(Blocks.SUNFLOWER, 1),
            new WeightedMutation(Blocks.LILAC, 1),
            new WeightedMutation(Blocks.ROSE_BUSH, 1),
            new WeightedMutation(Blocks.DANDELION, 1),
            new WeightedMutation(Blocks.POPPY, 1),
            new WeightedMutation(Blocks.BLUE_ORCHID, 1),
            new WeightedMutation(Blocks.LILY_OF_THE_VALLEY, 1),
            new WeightedMutation(Blocks.ALLIUM, 1),
            new WeightedMutation(Blocks.AZURE_BLUET, 1),
            new WeightedMutation(Blocks.OXEYE_DAISY, 1),
            new WeightedMutation(Blocks.CORNFLOWER, 1),
            new WeightedMutation(Blocks.RED_TULIP, 1),
            new WeightedMutation(Blocks.PINK_TULIP, 1),
            new WeightedMutation(Blocks.ORANGE_TULIP, 1)
    );

    //Registers the allowable blocks to be clicked to get a mutation to occur
    private static final Set<Block> PLANT_BLOCKS = PLANT_MUTATIONS.stream()
            .map(WeightedMutation::block)
            .collect(Collectors.toSet());

    //registers isPlant to be a function to determine if the Plant is on the list
    private static boolean isPlant(Block block) {
        return PLANT_BLOCKS.contains(block);
    }

    //creating a way to filter the list
    private static Block getRandomPlantMutation(Block current, Level level, BlockPos pos) {
        List<WeightedMutation> validMutations = PLANT_MUTATIONS.stream()
                //filters the list based on weight (less than 0 cant be spawned) & if it is different then the current block
                .filter(m -> m.weight() > 0 && m.block() != current)
                //filters the list based on three seperate things:
                .filter(m -> {
                    Block block = m.block();
                    //is the mutation block a vine? if so, do not allow it to be placed if the clicked block is a two block tall flower
                    if (block == Blocks.VINE && level.getBlockState(pos).hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)){
                        return false;
                    }
                    //is the mutation block a mushroom? if so, do not allow it to be placed if the clicked block is a two block tall flower
                    if (block == Blocks.BROWN_MUSHROOM && level.getBlockState(pos).hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)){
                        return false;
                    }
                    //is the mutation block a mushroom? if so, do not allow it to be placed if the clicked block is a two block tall flower
                    if (block == Blocks.RED_MUSHROOM && level.getBlockState(pos).hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)){
                        return false;
                    }
                    /*is the mutation block a Spanish Moss?? if so, do not allow it to be placed if the clicked block is a two block tall flower
                    The XXXX's are there to remember to switch it to whatever location Spanish moss gets stored in
                    if (block == XXXXXXXXXXXXXXXXXXX.SPANISH_MOSS && level.getBlockState(pos).hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)){
                        return false;
                    }
                    */
                    //Make it so the upper portion of a two block tall flower can not be clicked
                    if (level.getBlockState(pos).hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF) &&
                    level.getBlockState(pos).getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
                        return false;
                    }
                    //don't allow a two block tall flower to be placed if the above block is not air
                    if (block.defaultBlockState().hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)) {
                        return level.getBlockState(pos.above()).isAir();
                    }
                    return true;
                })
                .toList();

        //adds up the weights of all the valid plant mutations
        int totalWeight = validMutations.stream().mapToInt(WeightedMutation::weight).sum();

        //if no valid mutations are available (weight is 0), no mutation happens
        if (totalWeight == 0) return current;

        //roll is a random number from 0 to "totalWeight", cumulative is used to keep track of the running total of weights
        int roll = RANDOM.nextInt(totalWeight);
        int cumulative = 0;

        //loop through all valid plant mutations, once the roll is less than the cumulative value, select the block
        for (WeightedMutation mutation : validMutations) {
            cumulative += mutation.weight();
            if (roll < cumulative) {
                return mutation.block();
            }
        }
        //fallback to prevent an endless loop (should never be used)
        return current;
    }

    //constructor for the Mutandis Item, this just gives the given properties to the parent class (Item)
    public Mutandis (Properties properties) {
        super(properties);
    }

    //spawns particles in random positions near the center of the block
    private void spawnParticles(Level level, BlockPos pos) {
        //controls how many particles are spawned
        for (int i = 0; i < 6; i++) {
            //determines the spread in the x,y,z directions
            double x = pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 0.5;
            double y = pos.getY() + 0.05;
            double z = pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 0.5;

            //determines which type of particles are spawned & how fast the velocity of those particles are
            level.addParticle(ParticleTypes.WITCH, x, y, z, 0.01, 0.01, 0.01);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockPos abovePos = pos.above();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        //This if statement only proceeds if the clicked block is one we determined as a plant
        if (isPlant(clickedBlock)) {
            //selects a new block to turn into using the weighted logic
            Block mutatedBlock = getRandomPlantMutation(clickedBlock, level, pos);

            //if the mutation is different from the current block (it will be due to filters), it attempts to apply it
            if (mutatedBlock != clickedBlock) {
                //due to a world state being changed, the game must determine it is not in client side
                if (!level.isClientSide) {
                    //This removes the upper half of a double-block flower before replacing the lower half
                    if (level.getBlockState(pos).hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)
                         && level.getBlockState(pos).getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER) {
                        BlockState aboveState = level.getBlockState(abovePos);
                        if (aboveState.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)
                         && aboveState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER){
                            level.setBlock(abovePos, Blocks.AIR.defaultBlockState(), 3);
                        }
                    }
                    //if the placed block is a double block, check that the above block is air, then place the upper & lower halves
                    if (mutatedBlock.defaultBlockState().hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)){
                        if (level.getBlockState(abovePos).isAir()) {
                            level.setBlock(pos, mutatedBlock.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER), 3);
                            level.setBlock(pos.above(), mutatedBlock.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 3);
                        } else {
                            return InteractionResult.PASS;
                        }
                    //Vines must be placed with a direction, this direction is set to north
                    } else if (mutatedBlock == Blocks.VINE) {
                        level.setBlock(pos, Blocks.VINE.defaultBlockState().setValue(VineBlock.NORTH, true), 3);
                    //all other blocks are placed normally
                    } else {
                        level.setBlock(pos, mutatedBlock.defaultBlockState(), 3);
                    }

                    //plays the bone meal sound effect
                    level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);

                    //consumes the item if not in creative
                    if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                        context.getItemInHand().shrink(1);
                    }
                //MUST BE IN CLIENT SIDE TO SPAWN PARTICLES
                } else {
                    spawnParticles(level, pos);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
