package com.djinfinite.manors_bounty.content

import net.minecraft.core.Holder
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.common.ItemAbilities
import net.neoforged.neoforge.common.ItemAbility
import net.neoforged.neoforge.registries.DeferredBlock


class SimpleLogBlock : RotatedPillarBlock {

    private var stripped: Holder<out Block>? = null
//    var blockBlockStateFunction: Function<BlockState, BlockState?> = Function { blockState ->
//        if (stripped != null) stripped!!.defaultBlockState().setValue(
//            AXIS, blockState.getValue(AXIS) as Direction.Axis
//        ) as BlockState else null
//    }

    constructor(properties: Properties) : super(properties)

    constructor(properties: Properties, stripped: DeferredBlock<out Block>?) : super(properties) {
        this.stripped = stripped
    }

    override fun getToolModifiedState(
        state: BlockState, context: UseOnContext, itemAbility: ItemAbility, simulate: Boolean
    ): BlockState? {
        return if (itemAbility == ItemAbilities.AXE_STRIP) {
            stripped?.value()?.defaultBlockState()?.setValue(AXIS, state.getValue(AXIS))
        } else {
            super.getToolModifiedState(state, context, itemAbility, simulate)
        }
    }

//    override fun getToolModifiedState(
//        state: BlockState, context: UseOnContext, toolAction: ToolAction, simulate: Boolean
//    ): BlockState? {
//        return if (toolAction === ToolActions.AXE_STRIP) blockBlockStateFunction.apply(state) else super.getToolModifiedState(
//            state, context, toolAction, simulate
//        )
//    }
}