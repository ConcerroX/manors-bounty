package com.djinfinite.manors_bounty.feature

import com.mojang.serialization.Codec
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings

class StructureFeature(codec: Codec<StructureFeatureConfiguration>) : Feature<StructureFeatureConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<StructureFeatureConfiguration>): Boolean {
        val random = context.random()
        val worldGenLevel = context.level()
        val config = context.config()
        val rotation = if (config.randomRotation) Rotation.getRandom(random) else Rotation.NONE
        val mirror = if (config.randomMirror) Mirror.entries[random.nextInt(2)] else Mirror.NONE
        val placePos = context.origin().offset(config.offset)
        val structureManager = worldGenLevel.level.server.structureManager
        val template = structureManager.getOrCreate(config.structure)
        val placeSettings =
            StructurePlaceSettings().setRotation(rotation).setMirror(mirror).setRandom(random).setIgnoreEntities(false)
                .addProcessor(BlockIgnoreProcessor(config.ignoredBlocks.map { it.value() }))
        template.placeInWorld(worldGenLevel, placePos, placePos, placeSettings, random, 4)
        return true
    }

}
