package com.djinfinite.manors_bounty.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.Vec3i
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration

data class StructureFeatureConfiguration(
    val structure: ResourceLocation,
    val randomRotation: Boolean,
    val randomMirror: Boolean,
    val ignoredBlocks: HolderSet<Block>,
    val offset: Vec3i
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<StructureFeatureConfiguration> = RecordCodecBuilder.create {
            it.group(
                ResourceLocation.CODEC.fieldOf("structure").forGetter(StructureFeatureConfiguration::structure),
                Codec.BOOL.fieldOf("random_rotation").orElse(false)
                    .forGetter(StructureFeatureConfiguration::randomRotation),
                Codec.BOOL.fieldOf("random_mirror").orElse(false)
                    .forGetter(StructureFeatureConfiguration::randomMirror),
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("ignored_blocks")
                    .forGetter(StructureFeatureConfiguration::ignoredBlocks),
                Vec3i.offsetCodec(48).optionalFieldOf("offset", Vec3i.ZERO)
                    .forGetter(StructureFeatureConfiguration::offset),
            ).apply(it, ::StructureFeatureConfiguration)
        }
    }
}
