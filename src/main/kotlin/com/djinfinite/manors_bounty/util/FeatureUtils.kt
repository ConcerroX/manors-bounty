package com.djinfinite.manors_bounty.util

import com.djinfinite.manors_bounty.res
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import java.util.*

object FeatureUtils {

    fun createOptionalConfiguredFeatureKey(path: String): Optional<ResourceKey<ConfiguredFeature<*, *>>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, res(path)).cast(Registries.CONFIGURED_FEATURE)
    }

    fun createPlacedFeatureKey(path: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, res(path))
    }

}