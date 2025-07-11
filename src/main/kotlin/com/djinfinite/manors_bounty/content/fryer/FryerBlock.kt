package com.djinfinite.manors_bounty.content.fryer

import com.djinfinite.manors_bounty.registry.ModSoundEvents
import com.djinfinite.manors_bounty.util.inWholeTicks
import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import net.neoforged.neoforge.network.PacketDistributor
import kotlin.time.Duration.Companion.seconds

class FryerBlock(properties: Properties) : BaseEntityBlock(properties) {

    companion object {
        val CODEC: MapCodec<FryerBlock> = simpleCodec(::FryerBlock)
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val HAS_OIL: BooleanProperty = BooleanProperty.create("has_oil")
    }

    init {
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HAS_OIL, false))
    }

    override fun codec(): MapCodec<FryerBlock> = CODEC
    override fun getRenderShape(state: BlockState) = RenderShape.MODEL
    override fun newBlockEntity(pos: BlockPos, state: BlockState) = FryerBlockEntity(pos, state)
    override fun getStateForPlacement(context: BlockPlaceContext) =
        super.getStateForPlacement(context)?.setValue(FACING, context.horizontalDirection.opposite)

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING, HAS_OIL)
    }

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hitResult: BlockHitResult
    ): InteractionResult {
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is FryerBlockEntity && player is ServerPlayer) {
            player.openMenu(SimpleMenuProvider(blockEntity, blockEntity.displayName), pos)
        }
        return InteractionResult.SUCCESS
    }

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, movedByPiston: Boolean) {
        super.onPlace(state, level, pos, oldState, movedByPiston)
        level.scheduleTick(pos, this, 1.seconds.inWholeTicks)
    }

    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity is FryerBlockEntity && blockEntity.isWorking) {
            level.playSound(null, pos, ModSoundEvents.FRYING.get(), SoundSource.BLOCKS, 1.5F, 1F)
            blockEntity.updateWorkingProgress(blockEntity.workingProgress + 1)
            // Since the block only ticks on the server, we need to send the progress to clients
            PacketDistributor.sendToAllPlayers(
                FryerUpdateProgressPayload(blockEntity.blockPos, blockEntity.workingProgress)
            )
            level.scheduleTick(pos, this, 1.seconds.inWholeTicks)
        }
    }

    override fun onRemove(
        state: BlockState, level: Level, pos: BlockPos, newState: BlockState, movedByPiston: Boolean
    ) {
        if (state.block != newState.block) {
            val blockEntity = level.getBlockEntity(pos)
            if (blockEntity is FryerBlockEntity) blockEntity.dropContents()
            super.onRemove(state, level, pos, newState, movedByPiston)
        }
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when (state.getValue(FACING)) {
            Direction.NORTH -> Shapes.or(
                box(0.0, 0.0, 3.5, 1.0, 16.0, 13.5),
                box(0.0, 0.0, 13.5, 16.0, 20.0, 16.5),
                box(15.0, 0.0, 3.5, 16.0, 16.0, 13.5),
                box(0.0, 0.0, 1.5, 16.0, 16.0, 3.5),
                box(1.0, 0.0, 3.5, 15.0, 10.0, 13.5)
            )

            Direction.EAST -> Shapes.or(
                box(2.5, 0.0, 0.0, 12.5, 16.0, 1.0),
                box(-0.5, 0.0, 0.0, 2.5, 20.0, 16.0),
                box(2.5, 0.0, 15.0, 12.5, 16.0, 16.0),
                box(12.5, 0.0, 0.0, 14.5, 16.0, 16.0),
                box(2.5, 0.0, 1.0, 12.5, 10.0, 15.0)
            )

            Direction.WEST -> Shapes.or(
                box(3.5, 0.0, 15.0, 13.5, 16.0, 16.0),
                box(13.5, 0.0, 0.0, 16.5, 20.0, 16.0),
                box(3.5, 0.0, 0.0, 13.5, 16.0, 1.0),
                box(1.5, 0.0, 0.0, 3.5, 16.0, 16.0),
                box(3.5, 0.0, 1.0, 13.5, 10.0, 15.0)
            )

            Direction.SOUTH -> Shapes.or(
                box(15.0, 0.0, 2.5, 16.0, 16.0, 12.5),
                box(0.0, 0.0, -0.5, 16.0, 20.0, 2.5),
                box(0.0, 0.0, 2.5, 1.0, 16.0, 12.5),
                box(0.0, 0.0, 12.5, 16.0, 16.0, 14.5),
                box(1.0, 0.0, 2.5, 15.0, 10.0, 12.5)
            )

            else -> throw IllegalStateException("Invalid direction for fryer at $pos")
        }
    }

}