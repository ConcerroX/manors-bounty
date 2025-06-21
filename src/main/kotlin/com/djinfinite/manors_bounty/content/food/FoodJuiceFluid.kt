package com.djinfinite.manors_bounty.content.food

import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.neoforged.neoforge.fluids.BaseFlowingFluid

abstract class FoodJuiceFluid(properties: Properties) : BaseFlowingFluid(properties) {

    class Source(properties: Properties) : FoodJuiceFluid(properties) {
        override fun getAmount(state: FluidState) = 8
        override fun isSource(state: FluidState) = true
    }

    class Flowing(properties: Properties) : FoodJuiceFluid(properties) {
        override fun createFluidStateDefinition(builder: StateDefinition.Builder<Fluid?, FluidState?>) {
            super.createFluidStateDefinition(builder)
            builder.add(LEVEL)
        }

        override fun getAmount(state: FluidState): Int = state.getValue(LEVEL)
        override fun isSource(state: FluidState) = false
    }

}
