package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.food.FruitEffectDataComponent
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModDataComponents {

    val DATA_COMPONENTS: DeferredRegister.DataComponents =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ManorsBounty.ID)

    val FRUIT_EFFECT: DeferredHolder<DataComponentType<*>, DataComponentType<FruitEffectDataComponent>> =
        DATA_COMPONENTS.registerComponentType("fruit_effect") {
            it.networkSynchronized(FruitEffectDataComponent.STREAM_CODEC)
        }

}