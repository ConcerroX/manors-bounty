package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.food.FoodJuiceFluid
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BucketItem
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.fluids.BaseFlowingFluid
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Function
import java.util.function.Supplier

object ModFluids {

    val FLUIDS: DeferredRegister<Fluid> = DeferredRegister.create(Registries.FLUID, ManorsBounty.ID)

    lateinit var PINEAPPLE_JUICE: DeferredHolder<Fluid, FoodJuiceFluid.Source>
    lateinit var PINEAPPLE_JUICE_FLOWING: DeferredHolder<Fluid, FoodJuiceFluid.Flowing>

    init {
        register("pineapple_juice", FoodJuiceFluid::Source, FoodJuiceFluid::Flowing, {
            newProperties(
                ModFluidTypes.PINEAPPLE_JUICE,
                PINEAPPLE_JUICE,
                PINEAPPLE_JUICE_FLOWING,
                ModBlocks.PINEAPPLE_JUICE,
                ModItems.PINEAPPLE_JUICE_BUCKET
            )
        }).apply {
            PINEAPPLE_JUICE = source
            PINEAPPLE_JUICE_FLOWING = flowing
        }
    }

    private data class FluidPair<S : Fluid, F : Fluid>(
        val source: DeferredHolder<Fluid, S>, val flowing: DeferredHolder<Fluid, F>
    )

    private fun <S : Fluid, F : Fluid> register(
        id: String,
        source: Function<BaseFlowingFluid.Properties, S>,
        flowing: Function<BaseFlowingFluid.Properties, F>,
        properties: Supplier<BaseFlowingFluid.Properties>
    ): FluidPair<S, F> {
        val sourceFluid = FLUIDS.register(id, Supplier { source.apply(properties.get()) })
        val flowingFluid = FLUIDS.register("${id}_flowing", Supplier { flowing.apply(properties.get()) })
        return FluidPair(sourceFluid, flowingFluid)
    }

    private fun newProperties(
        fluidType: Supplier<out FluidType>,
        source: Supplier<out Fluid>,
        flowing: Supplier<out Fluid>,
        block: Supplier<out LiquidBlock>,
        bucket: Supplier<BucketItem>,
        explosionResistance: Float = 100F
    ): BaseFlowingFluid.Properties {
        return BaseFlowingFluid.Properties(fluidType, source, flowing).apply {
            explosionResistance(explosionResistance)
            block(block)
            bucket(bucket)
        }
    }

}