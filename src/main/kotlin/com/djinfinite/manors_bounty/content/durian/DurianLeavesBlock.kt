package com.djinfinite.manors_bounty.content.durian

import com.djinfinite.manors_bounty.registry.ModBlocks
import com.djinfinite.manors_bounty.registry.ModItems
import com.djinfinite.manors_bounty.util.letChanced
import com.djinfinite.manors_bounty.util.valueChanced
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
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
import net.minecraft.world.level.block.state.properties.IntegerProperty
import kotlin.math.abs


class DurianLeavesBlock(properties: Properties) : Block(properties), BonemealableBlock {

    companion object {
        val AGE: IntegerProperty = IntegerProperty.create("blockstate", 0, 8) // TODO: rename this
    }

    override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState) = true
    override fun getFlammability(state: BlockState, level: BlockGetter, pos: BlockPos, face: Direction) = 30
    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) =
        grow(state, level, pos)

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState) =
        state.getValue(AGE) != 7 && state.getValue(AGE) != 3

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AGE)
    }

    override fun neighborChanged(
        state: BlockState, level: Level, pos: BlockPos, neighborBlock: Block, fromPos: BlockPos, moving: Boolean
    ) {
        super.neighborChanged(state, level, pos, neighborBlock, fromPos, moving)
        if (state.getValue(AGE) > 4 && !hasDurianFruit(level, pos)) setAge(level, pos, 4)
    }

    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        val age = state.getValue(AGE)
        if (age == 0) setAge(level, pos, valueChanced(0.3F, 3, 1))

        var isAlive = false
        val below = pos.below()
        val x = pos.x.toDouble()
        val y = pos.y.toDouble()
        val z = pos.z.toDouble()
        for (dx in -4..4) {
            for (dy in -4..4) {
                for (dz in -4..4) {
                    if (level.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz)).`is`(BlockTags.LOGS)) {
                        isAlive = true
                        break
                    }
                }
                if (isAlive) break
            }
            if (isAlive) break
        }

        if (!isAlive) {
            if (age == 6) {
                level.setBlock(below, Blocks.AIR.defaultBlockState(), 1 or 2)
                spawnFruitItem(level, x, y - 1, z)
            } else {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 1 or 2)
                letChanced(0.06F) {
                    spawnStickItem(level, x, y, z)
                }
            }
        } else {
            if (age != 3) {
                val chance = when (age) {
                    1 -> 0.3F
                    4, 5, 6 -> 0.05F
                    7 -> 0.1F // todo: FRUIT_TREE_DROP
                    else -> 0F
                }
                letChanced(abs(chance)) {
                    if (age == 7) {
                        level.setBlock(below, Blocks.AIR.defaultBlockState(), 1 or 2)
                        if (chance > 0) spawnFruitItem(level, x, y - 1, z)
                    }
                    grow(state, level, pos)
                }
            }
        }
    }

    override fun setPlacedBy(
        level: Level, pos: BlockPos, blockstate: BlockState, placer: LivingEntity?, itemstack: ItemStack
    ) {
        level.setBlock(pos, defaultBlockState().setValue(AGE, 2), 1 or 2)
    }

    private fun hasDurianFruit(level: Level, pos: BlockPos): Boolean {
        return level.getBlockState(pos.below()).`is`(ModBlocks.DURIAN_FRUIT)
    }

    private fun setAge(level: Level, pos: BlockPos, age: Int) {
        level.setBlock(pos, level.getBlockState(pos).setValue(AGE, age), 1 or 2)
    }

    private fun placeDurianFruit(level: Level, pos: BlockPos, age: Int) {
        val state = level.getBlockState(pos)
        if (state.`is`(ModBlocks.DURIAN_FRUIT)) {
            level.setBlock(pos, state.setValue(DurianFruitBlock.FRUIT_AGE, age), 1 or 2)
        } else if (state.isAir) {
            level.setBlock(pos, ModBlocks.DURIAN_FRUIT.get().defaultBlockState().setValue(DurianFruitBlock.FRUIT_AGE, age), 1 or 2)
        }
    }

    private fun grow(state: BlockState, level: Level, pos: BlockPos) {
        val age = state.getValue(AGE)
        val below = pos.below()
        val targetAge = when (age) {
            0, 1 -> 4
            2 -> 8
            4 -> {
                placeDurianFruit(level, below, 1)
                5
            }

            5 -> {
                placeDurianFruit(level, below, 2)
                6
            }

            6 -> {
                placeDurianFruit(level, below, 3)
                7
            }

            8 -> 2
            else -> return // 3, 7 are not bonemealable
        }
        setAge(level, pos, targetAge)
    }

    private fun spawnFruitItem(level: LevelAccessor, x: Double, y: Double, z: Double) {
        if (level is ServerLevel) {
            val fruitEntity = ItemEntity(level, x, y, z, ItemStack(ModItems.MUSANG_KING_DURIAN.get()))
            fruitEntity.setPickUpDelay(10)
            level.addFreshEntity(fruitEntity)
        }
    }

    private fun spawnStickItem(level: LevelAccessor, x: Double, y: Double, z: Double) {
        if (level is ServerLevel) {
            val stickEntity = ItemEntity(level, x, y, z, ItemStack(Items.STICK))
            stickEntity.setPickUpDelay(0)
            level.addFreshEntity(stickEntity)
        }
    }

}