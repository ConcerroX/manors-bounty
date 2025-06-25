package com.djinfinite.manors_bounty.content.durian

import com.djinfinite.manors_bounty.registry.ModBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.BoneMealItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape


class DurianFruitBlock(properties: Properties) : Block(properties), BonemealableBlock {

    companion object {
        val FRUIT_AGE: IntegerProperty = BlockStateProperties.AGE_3
        val SHAPES = arrayOf(
            box(5.0, 7.0, 5.0, 11.0, 14.0, 11.0),
            box(5.0, 7.0, 5.0, 11.0, 14.0, 11.0),
            box(3.5, 4.0, 3.5, 12.5, 14.0, 12.5),
            Shapes.or(box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0), box(7.0, 14.0, 7.0, 9.0, 16.0, 9.0)),
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(FRUIT_AGE)
    }

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape = SHAPES[state.getValue(FRUIT_AGE)]

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos) =
        level.getBlockState(pos.above()).`is`(ModBlocks.DURIAN_LEAVES)

    override fun updateShape(
        state: BlockState,
        facing: Direction,
        facingState: BlockState,
        world: LevelAccessor,
        currentPos: BlockPos,
        facingPos: BlockPos
    ): BlockState {
        return if (!state.canSurvive(world, currentPos)) Blocks.AIR.defaultBlockState() else super.updateShape(
            state, facing, facingState, world, currentPos, facingPos
        )
    }

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState) =
        state.getValue(FRUIT_AGE) != 3

    override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState) =
        state.getValue(FRUIT_AGE) != 3

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        if (state.getValue(FRUIT_AGE) != 3) {
            val canApplyBonemeal =
                BoneMealItem.growCrop(ItemStack(Items.BONE_MEAL), level, pos.above()) || BoneMealItem.growWaterPlant(
                    ItemStack(Items.BONE_MEAL), level, pos.above(), null
                )
            if (canApplyBonemeal) {
                level.levelEvent(2005, pos.above(), 0)
            }
        }
    }

}