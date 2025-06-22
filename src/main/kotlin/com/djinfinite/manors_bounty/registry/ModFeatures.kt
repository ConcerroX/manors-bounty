package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.feature.StructureFeature
import com.djinfinite.manors_bounty.feature.StructureFeatureConfiguration
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.Feature
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModFeatures {

    val FEATURES: DeferredRegister<Feature<*>> = DeferredRegister.create(Registries.FEATURE, ManorsBounty.ID)

    val STRUCTURE_FEATURE: DeferredHolder<Feature<*>, StructureFeature> =
        FEATURES.register("structure_feature", Supplier { StructureFeature(StructureFeatureConfiguration.CODEC) })

}