package com.djinfinite.manors_bounty.data

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.registry.ModTags
import com.djinfinite.manors_bounty.res
import com.djinfinite.manors_bounty.util.FeatureUtils
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.GenerationStep
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.common.world.BiomeModifiers
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.concurrent.CompletableFuture


class ModBiomeModifiersProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
) : DatapackBuiltinEntriesProvider(output, registries, BUILDER, setOf(ManorsBounty.ID)) {

    companion object {

        private val BUILDER: RegistrySetBuilder = RegistrySetBuilder().add(NeoForgeRegistries.Keys.BIOME_MODIFIERS) {
            val biomes = it.lookup(Registries.BIOME)
            val placedFeatures = it.lookup(Registries.PLACED_FEATURE)
            it.register(
                create("add_scots_pine"), BiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(ModTags.Biomes.OPTIONAL_VIOLET_BLUE_PLAINS),
                    HolderSet.direct(placedFeatures.getOrThrow(FeatureUtils.createPlacedFeatureKey("scots_pine_1"))),
                    GenerationStep.Decoration.SURFACE_STRUCTURES
                )
            )
        }

        private fun create(path: String) = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, res(path))
    }

}