package com.djinfinite.manors_bounty.content.pineapple

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.FallingBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class PineappleBlock(properties: Properties) : FallingBlock(properties) {

    companion object {
        private val SHAPE: VoxelShape =
            Shapes.or(box(4.0, 0.0, 4.0, 12.0, 10.0, 12.0), box(8.0, 0.0, 8.0, 8.001, 16.0, 8.001))
    }

    override fun codec(): MapCodec<PineappleBlock> = simpleCodec(::PineappleBlock)

    public override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ) = SHAPE

}