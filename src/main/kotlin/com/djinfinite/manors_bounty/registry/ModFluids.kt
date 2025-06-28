package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.food.SimpleFluid
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

    lateinit var PINEAPPLE_JUICE: DeferredHolder<Fluid, SimpleFluid.Source>
    lateinit var PINEAPPLE_JUICE_FLOWING: DeferredHolder<Fluid, SimpleFluid.Flowing>

    lateinit var OLIVE_OIL: DeferredHolder<Fluid, SimpleFluid.Source>
    lateinit var OLIVE_OIL_FLOWING: DeferredHolder<Fluid, SimpleFluid.Flowing>

    lateinit var CAKE_LIQUID: DeferredHolder<Fluid, SimpleFluid.Source>
    lateinit var CAKE_LIQUID_FLOWING: DeferredHolder<Fluid, SimpleFluid.Flowing>

    init {
        register("pineapple_juice", SimpleFluid::Source, SimpleFluid::Flowing, {
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

        register("olive_oil", SimpleFluid::Source, SimpleFluid::Flowing, {
            newProperties(
                ModFluidTypes.OLIVE_OIL, OLIVE_OIL, OLIVE_OIL_FLOWING, ModBlocks.OLIVE_OIL, ModItems.OLIVE_OIL_BUCKET
            )
        }).apply {
            OLIVE_OIL = source
            OLIVE_OIL_FLOWING = flowing
        }

        register("cake_liquid", SimpleFluid::Source, SimpleFluid::Flowing, {
            newProperties(
                ModFluidTypes.CAKE_LIQUID,
                CAKE_LIQUID,
                CAKE_LIQUID_FLOWING,
                ModBlocks.CAKE_LIQUID,
                ModItems.CAKE_LIQUID_BUCKET,
                levelDecreasePerBlock = 2,
                slopeFindDistance = 3
            )
        }).apply {
            CAKE_LIQUID = source
            CAKE_LIQUID_FLOWING = flowing
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
        explosionResistance: Float = 100F,
        levelDecreasePerBlock: Int = 1,
        slopeFindDistance: Int = 4
    ): BaseFlowingFluid.Properties {
        return BaseFlowingFluid.Properties(fluidType, source, flowing).apply {
            explosionResistance(explosionResistance)
            block(block)
            bucket(bucket)
            levelDecreasePerBlock(levelDecreasePerBlock)
            slopeFindDistance(slopeFindDistance)
        }
    }

}