package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import net.minecraft.sounds.SoundEvents
import net.neoforged.neoforge.common.SoundActions
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.fluids.FluidType.Properties
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.function.Supplier

object ModFluidTypes {

    val FLUID_TYPES: DeferredRegister<FluidType> =
        DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, ManorsBounty.ID)

    val PINEAPPLE_JUICE: DeferredHolder<FluidType, FluidType> = registerJuice("pineapple_juice")

    private fun registerJuice(id: String): DeferredHolder<FluidType, FluidType> {
        return FLUID_TYPES.register(id, Supplier {
            FluidType(
                Properties.create().fallDistanceModifier(0F).canExtinguish(true).supportsBoating(true).canHydrate(true)
                    .motionScale(0.007).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            )
        })
    }


}