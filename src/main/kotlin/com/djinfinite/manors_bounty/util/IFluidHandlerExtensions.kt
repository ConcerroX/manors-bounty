package com.djinfinite.manors_bounty.util

import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.capability.IFluidHandler

val IFluidHandler.fluids: List<FluidStack>
    get() = List(tanks, ::getFluidInTank)

val IFluidHandler.isEmpty: Boolean
    get() = fluids.all { it.isEmpty }