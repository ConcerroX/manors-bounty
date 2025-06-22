package com.djinfinite.manors_bounty.content

import com.djinfinite.manors_bounty.ManorsBountyConfig
import com.djinfinite.manors_bounty.util.letChanced
import com.djinfinite.manors_bounty.util.valueChanced
import com.djinfinite.manors_bounty.util.valueChancedOrNull
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.BlockHitResult
import kotlin.math.abs


class FruitLeavesBlock(val fruit: ItemLike, val fruitDropCount: IntRange, properties: Properties) :
    LeavesBlock(properties), BonemealableBlock {

    companion object {
        val AGE: IntegerProperty = IntegerProperty.create("blockstate", 0, 6) // TODO: rename this
        private const val AGE_FIRST_CYCLE_START = 0
        private const val AGE_OTHER_CYCLE_START = 1
        private const val AGE_PLACED_BY_PLAYER = 2
        private const val AGE_NEVER_GROWTH = 3
        private const val AGE_BLOSSOM = 4
        private const val AGE_BEARING = 5
        private const val AGE_PLACED_BY_PLAYER_BLOSSOM = 6
//        val EXPOSED: BooleanProperty = BooleanProperty.create("exposed")
    }

//    init {
//        registerDefaultState(stateDefinition.any().setValue(AGE, 0))
//    }

//    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, movedByPiston: Boolean) {
//        updateExposed(level, pos)
//    }

    override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState) = true

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(AGE)
    }

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        return state.getValue(AGE) != AGE_NEVER_GROWTH && state.getValue(AGE) != AGE_PLACED_BY_PLAYER_BLOSSOM
    }

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        if (state.getValue(AGE) == AGE_PLACED_BY_PLAYER) {
            setAge(level, pos, AGE_PLACED_BY_PLAYER_BLOSSOM)
        } else {
            tryGrow(level, pos, state, true)
        }
    }

    override fun isRandomlyTicking(state: BlockState): Boolean {
        return true //super.isRandomlyTicking(state) && state.getValue(EXPOSED)
    }

//    override fun updateShape(
//        state: BlockState,
//        facing: Direction,
//        facingState: BlockState,
//        level: LevelAccessor,
//        currentPos: BlockPos,
//        facingPos: BlockPos
//    ): BlockState {
//        updateExposed(level, currentPos)
//        return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
//    }

    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        tryGrow(level, pos, state, false)
        // Leaves placed by player don't decay
        if (state.getValue(AGE) != AGE_PLACED_BY_PLAYER || state.getValue(AGE) != AGE_PLACED_BY_PLAYER_BLOSSOM) {
            super.randomTick(state, level, pos, random)
        }
    }

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hitResult: BlockHitResult
    ): InteractionResult {
        if (state.getValue(AGE) == AGE_BEARING) {
            if (level is ServerLevel) spawnFruitItem(level, pos)
            setAge(level, pos, AGE_OTHER_CYCLE_START)
            return InteractionResult.SUCCESS
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult)
        }
    }

    private fun setAge(level: Level, pos: BlockPos, age: Int) {
        level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(AGE, age))
    }

//    private fun updateExposed(level: LevelAccessor, pos: BlockPos) {
//        val state = level.getBlockState(pos)
//        var exposed = state.getValue(EXPOSED)
//        for (direction in Direction.entries) {
//            val neighbor = pos.relative(direction)
//            val neighborState = level.getBlockState(neighbor)
//            if (neighborState.isAir || !neighborState.canOcclude()) {
//                exposed = true
//                break
//            }
//        }
//        level.setBlock(pos, state.setValue(EXPOSED, exposed), 2)
//    }

    private fun tryGrow(level: ServerLevel, pos: BlockPos, state: BlockState, isBonemealed: Boolean) {
        val age = state.getValue(AGE)
        val targetAge = when (age) {
            AGE_FIRST_CYCLE_START -> valueChanced(
                if (isBonemealed) 1.0 else ManorsBountyConfig.fruitLeavesBearChance.get(), AGE_BLOSSOM, AGE_NEVER_GROWTH
            )

            AGE_OTHER_CYCLE_START -> valueChancedOrNull(
                if (isBonemealed) 1.0 else ManorsBountyConfig.fruitLeavesBlossomRate.get(), AGE_BLOSSOM
            )

            AGE_PLACED_BY_PLAYER -> null
            AGE_NEVER_GROWTH -> null
            AGE_BLOSSOM -> valueChancedOrNull(
                if (isBonemealed) 1.0 else ManorsBountyConfig.fruitLeavesBearRate.get(), AGE_BEARING
            )

            AGE_BEARING -> {
                val fruitLeavesDropRate = if (isBonemealed) 1.0 else ManorsBountyConfig.fruitLeavesDropRate.get()
                letChanced(abs(fruitLeavesDropRate), success = {
                    if (fruitLeavesDropRate > 0) {
                        spawnFruitItem(level, pos)
                    }
                    AGE_OTHER_CYCLE_START
                }, failure = {
                    null
                })
            }

            AGE_PLACED_BY_PLAYER_BLOSSOM -> null
            else -> null // should never happen
        }
        targetAge?.let { setAge(level, pos, it) }
    }

    private fun spawnFruitItem(level: Level, pos: BlockPos) {
        level.addFreshEntity(
            ItemEntity(
                level, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), ItemStack(fruit, fruitDropCount.random())
            ).apply { setPickUpDelay(10) },
        )
    }

}