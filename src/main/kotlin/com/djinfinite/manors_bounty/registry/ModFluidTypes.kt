package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.res
import net.minecraft.sounds.SoundEvents
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.common.SoundActions
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.fluids.FluidType.Properties
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.function.Supplier

object ModFluidTypes {

    val FLUID_TYPES: DeferredRegister<FluidType> = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, ManorsBounty.ID)
    private val ICE_CREAM_PROPERTIES: Properties =
        Properties.create().fallDistanceModifier(0F).canExtinguish(true).supportsBoating(true).canHydrate(true)
            .motionScale(0.0035).temperature(260).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_POWDER_SNOW)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)

    val PINEAPPLE_JUICE = registerFluid("pineapple_juice")
    val OLIVE_OIL = registerFluid("olive_oil")
    val CAKE_LIQUID = registerFluid(
        "cake_liquid",
        Properties.create().fallDistanceModifier(0F).canExtinguish(true).supportsBoating(true).canHydrate(true).motionScale(0.007)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
    )
    val VANILLA_ICE_CREAM = registerFluid("vanilla_ice_cream", ICE_CREAM_PROPERTIES)
    val CHOCOLATE_ICE_CREAM = registerFluid("chocolate_ice_cream", ICE_CREAM_PROPERTIES)
    val BLUEBERRY_ICE_CREAM = registerFluid("blueberry_ice_cream", ICE_CREAM_PROPERTIES)
    val CHERRIES_ICE_CREAM = registerFluid("cherries_ice_cream", ICE_CREAM_PROPERTIES)
    val STARFRUIT_ICE_CREAM = registerFluid("starfruit_ice_cream", ICE_CREAM_PROPERTIES)
    val JALAPENO_ICE_CREAM = registerFluid("jalapeno_ice_cream", ICE_CREAM_PROPERTIES)

    internal fun registerTextures(event: RegisterClientExtensionsEvent) {
        event.registerTexture(PINEAPPLE_JUICE)
        event.registerTexture(OLIVE_OIL)
        event.registerTexture(CAKE_LIQUID)
        event.registerTexture(VANILLA_ICE_CREAM)
        event.registerTexture(CHOCOLATE_ICE_CREAM)
        event.registerTexture(BLUEBERRY_ICE_CREAM)
        event.registerTexture(CHERRIES_ICE_CREAM)
        event.registerTexture(STARFRUIT_ICE_CREAM)
        event.registerTexture(JALAPENO_ICE_CREAM)
    }

    private fun RegisterClientExtensionsEvent.registerTexture(fluidType: DeferredHolder<FluidType, FluidType>) {
        val name = fluidType.id.path
        registerFluidType(object : IClientFluidTypeExtensions {
            override fun getStillTexture() = res("block/${name}_still")
            override fun getFlowingTexture() = res("block/${name}_flow")
        }, fluidType)
    }

    private fun registerFluid(id: String, properties: Properties? = null): DeferredHolder<FluidType, FluidType> {
        return FLUID_TYPES.register(id, Supplier {
            FluidType(
                properties ?: Properties.create().fallDistanceModifier(0F).canExtinguish(true).supportsBoating(true)
                    .canHydrate(true).motionScale(0.007).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            )
        })
    }

}