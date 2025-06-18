package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister


object ModBlocks {

    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(ManorsBounty.ID)

    val PEARL_ROCK_ORE: DeferredBlock<Block> = BLOCKS.registerSimpleBlock("pearl_rock_ore",
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.STONE)
            .sound(SoundType.STONE).strength(3.0F).requiresCorrectToolForDrops().lightLevel { 15 })
    val DEEPSLATE_PEARL_ROCK_ORE: DeferredBlock<Block> = BLOCKS.registerSimpleBlock("deepslate_pearl_rock_ore",
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.DEEPSLATE)
            .sound(SoundType.STONE).strength(4.5F, 3.0F).requiresCorrectToolForDrops().lightLevel { 15 })

}
