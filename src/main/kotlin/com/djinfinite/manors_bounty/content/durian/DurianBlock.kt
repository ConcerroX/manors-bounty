package com.djinfinite.manors_bounty.content.durian

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.item.FallingBlockEntity
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.FallingBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class DurianBlock(properties: Properties) : FallingBlock(properties) {

    companion object {
        private val SHAPE = Shapes.or(box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0), box(7.0, 14.0, 7.0, 9.0, 16.0, 9.0))
    }

    override fun codec(): MapCodec<DurianBlock> = simpleCodec(::DurianBlock)

    override fun falling(fallingBlockEntity: FallingBlockEntity) {
        fallingBlockEntity.setHurtsEntities(1.25F, 30)
    }

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape = SHAPE

}