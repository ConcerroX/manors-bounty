package com.djinfinite.manors_bounty.content.pineapple

import com.djinfinite.manors_bounty.registry.ModItems
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape


class PineappleCropBlock(properties: Properties) : CropBlock(properties), BonemealableBlock {

    companion object {
        val AGE: IntegerProperty = BlockStateProperties.AGE_5
        val SHAPES = arrayOf(
            box(3.0, 0.0, 3.0, 13.0, 9.0, 13.0),
            box(3.0, 0.0, 3.0, 13.0, 10.0, 13.0),
            box(3.0, 0.0, 3.0, 13.0, 13.0, 13.0),
            box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0),
            box(3.0, 0.0, 3.0, 13.0, 20.0, 13.0),
            box(3.0, 0.0, 3.0, 13.0, 22.0, 13.0),
        )
    }

    override fun getMaxAge() = 5
    override fun getAgeProperty() = AGE
    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape =
        SHAPES[getAge(state)]

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AGE)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return super.canSurvive(state, level, pos) || level.getBlockState(pos.below()).`is`(Blocks.SAND)
    }

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hitResult: BlockHitResult
    ): InteractionResult {
        if (getAge(state) == getMaxAge()) {
            if (level.getBlockState(pos.below()).`is`(Blocks.SAND)) {
                level.destroyBlock(pos, false)
            } else {
                level.setBlock(pos, state.setValue(AGE, 2), 1 or 2)
            }
            level.playSound(player, pos, SoundEvents.BAMBOO_SAPLING_BREAK, SoundSource.BLOCKS, 1F, 1F)
            if (level is ServerLevel) {
                val pineappleEntity = ItemEntity(
                    level, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), ItemStack(ModItems.PINEAPPLE.get())
                )
                pineappleEntity.setPickUpDelay(10)
                level.addFreshEntity(pineappleEntity)
            }
            return InteractionResult.SUCCESS
        }
        return super.useWithoutItem(state, level, pos, player, hitResult)
    }

}