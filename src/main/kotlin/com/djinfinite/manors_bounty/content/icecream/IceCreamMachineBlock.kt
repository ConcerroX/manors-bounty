package com.djinfinite.manors_bounty.content.icecream

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.Containers
import net.minecraft.world.InteractionResult
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape


class IceCreamMachineBlock(properties: Properties) : BaseEntityBlock(properties), EntityBlock {

    companion object {
        val FACING: DirectionProperty = BlockStateProperties.FACING
        val CODEC: MapCodec<IceCreamMachineBlock> = simpleCodec(::IceCreamMachineBlock)
    }

    init {
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return super.getStateForPlacement(context)?.setValue(FACING, context.horizontalDirection.opposite)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    @Suppress("DEPRECATION")
    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hitResult: BlockHitResult
    ): InteractionResult {
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is IceCreamMachineBlockEntity && player is ServerPlayer) {
            player.openMenu(SimpleMenuProvider(blockEntity, blockEntity.displayName), pos)
            return InteractionResult.SUCCESS
        }
        return InteractionResult.SUCCESS
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return IceCreamMachineBlockEntity(pos, state)
    }

    override fun onRemove(
        state: BlockState, level: Level, pos: BlockPos, newState: BlockState, movedByPiston: Boolean
    ) {
        if (state.block != newState.block) {
            val blockEntity = level.getBlockEntity(pos)
            if (blockEntity is IceCreamMachineBlockEntity) {
                blockEntity.dropContents()
                level.updateNeighbourForOutputSignal(pos, this)
            }
            super.onRemove(state, level, pos, newState, movedByPiston)
        }
    }

//    public override fun hasAnalogOutputSignal(state: BlockState): Boolean {
//        return true
//    }
//
//    public override fun getAnalogOutputSignal(blockState: BlockState, world: Level, pos: BlockPos): Int {
//        val blockEntity = world.getBlockEntity(pos)
//        return if (blockEntity is IceCreamMachineBlockEntity) AbstractContainerMenu.getRedstoneSignalFromContainer(
//            blockEntity)
//        else 0
//    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when (state.getValue(FACING)) {
            Direction.NORTH -> Shapes.or(box(1.0, 0.0, 4.0, 15.0, 15.0, 16.0), box(1.0, 12.0, 0.0, 15.0, 15.0, 4.0),
                box(13.0, 0.0, 2.0, 15.0, 12.0, 4.0), box(3.0, 0.0, 2.0, 13.0, 2.0, 4.0),
                box(1.0, 0.0, 2.0, 3.0, 12.0, 4.0), box(6.0, 9.0, 2.0, 10.0, 11.0, 4.0),
                box(5.0, 4.0, 2.0, 11.0, 5.0, 4.0))

            Direction.EAST -> Shapes.or(box(0.0, 0.0, 1.0, 12.0, 15.0, 15.0), box(12.0, 12.0, 1.0, 16.0, 15.0, 15.0),
                box(12.0, 0.0, 13.0, 14.0, 12.0, 15.0), box(12.0, 0.0, 3.0, 14.0, 2.0, 13.0),
                box(12.0, 0.0, 1.0, 14.0, 12.0, 3.0), box(12.0, 9.0, 6.0, 14.0, 11.0, 10.0),
                box(12.0, 4.0, 5.0, 14.0, 5.0, 11.0))

            Direction.WEST -> Shapes.or(box(4.0, 0.0, 1.0, 16.0, 15.0, 15.0), box(0.0, 12.0, 1.0, 4.0, 15.0, 15.0),
                box(2.0, 0.0, 1.0, 4.0, 12.0, 3.0), box(2.0, 0.0, 3.0, 4.0, 2.0, 13.0),
                box(2.0, 0.0, 13.0, 4.0, 12.0, 15.0), box(2.0, 9.0, 6.0, 4.0, 11.0, 10.0),
                box(2.0, 4.0, 5.0, 4.0, 5.0, 11.0))

            else -> Shapes.or(box(1.0, 0.0, 0.0, 15.0, 15.0, 12.0), box(1.0, 12.0, 12.0, 15.0, 15.0, 16.0),
                box(1.0, 0.0, 12.0, 3.0, 12.0, 14.0), box(3.0, 0.0, 12.0, 13.0, 2.0, 14.0),
                box(13.0, 0.0, 12.0, 15.0, 12.0, 14.0), box(6.0, 9.0, 12.0, 10.0, 11.0, 14.0),
                box(5.0, 4.0, 12.0, 11.0, 5.0, 14.0))
        }
    }

}