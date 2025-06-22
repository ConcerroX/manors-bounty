package com.djinfinite.manors_bounty.data

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.registry.ModTags
import com.djinfinite.manors_bounty.res
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.TagsProvider
import net.minecraft.world.level.biome.Biome
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModBiomeTagsProvider(
    output: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>, existingFileHelper: ExistingFileHelper
) : TagsProvider<Biome>(output, Registries.BIOME, lookupProvider, ManorsBounty.ID, existingFileHelper) {

    override fun addTags(provider: HolderLookup.Provider) {
        tag(ModTags.Biomes.OPTIONAL_VIOLET_BLUE_PLAINS).addOptional(res("violet_blue_plains"))
    }

}