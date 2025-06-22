package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.content.LogBlock
import com.djinfinite.manors_bounty.registry.ModBlocks.BLOCKS
import com.djinfinite.manors_bounty.registry.ModBlocks.newProperties
import net.minecraft.core.Direction
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ButtonBlock
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.PressurePlateBlock
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraft.world.level.material.MapColor
import net.neoforged.neoforge.registries.DeferredBlock

object ModWoodTypes {

    // Experimental APIs: WoodBlocks
    val SCOTS_PINE = registerWoodBlocks("scots_pine", MapColor.WOOD, MapColor.PODZOL)
    val ALPINE_TREE = registerWoodBlocks("alpine_tree", MapColor.WOOD, MapColor.PODZOL)
    val CHERRIES_TREE = registerWoodBlocks("cherries_tree", MapColor.WOOD, MapColor.PODZOL)
    val STARFRUIT_TREE = registerWoodBlocks("starfruit_tree", MapColor.WOOD, MapColor.PODZOL)
    val OLIVE_TREE = registerWoodBlocks("olive_tree", MapColor.WOOD, MapColor.PODZOL)
    val RUTACEAE_TREE = registerWoodBlocks("rutaceae_tree", MapColor.WOOD, MapColor.PODZOL)
    val ROSACEAE_TREE = registerWoodBlocks("rosaceae_tree", MapColor.WOOD, MapColor.PODZOL)
    val MANGO_TREE = registerWoodBlocks("mango_tree", MapColor.WOOD, MapColor.PODZOL)
    val KIWIFRUIT_TREE = registerWoodBlocks("kiwifruit_tree", MapColor.WOOD, MapColor.PODZOL)
    val AVOCADO_TREE = registerWoodBlocks("avocado_tree", MapColor.WOOD, MapColor.PODZOL)

    // ItemLike is only used when adding items to creative mode tabs
    // Will Kotlin support union type? 😭😥
    class WoodType : ItemLike {
        var log: DeferredBlock<Block>? = null
        var wood: DeferredBlock<Block>? = null
        var strippedLog: DeferredBlock<Block>? = null
        var strippedWood: DeferredBlock<Block>? = null
        var planks: DeferredBlock<Block>? = null
        var stairs: DeferredBlock<Block>? = null
        var slab: DeferredBlock<Block>? = null
        var fence: DeferredBlock<Block>? = null
        var fenceGate: DeferredBlock<Block>? = null
        var door: DeferredBlock<Block>? = null
        var trapdoor: DeferredBlock<Block>? = null
        var button: DeferredBlock<Block>? = null
        var pressurePlate: DeferredBlock<Block>? = null

        fun values() = listOfNotNull(
            log, wood, strippedLog, strippedWood, planks, stairs, slab, fence, fenceGate, door, trapdoor, button,
            pressurePlate,
        )

        override fun asItem() = throw UnsupportedOperationException()
    }

    private fun registerWoodBlocks(path: String, planksMapColor: MapColor, barkMapColor: MapColor) = WoodType().apply {
        val blockSetType = BlockSetType.register(BlockSetType(path))
        val mcWoodType =
            net.minecraft.world.level.block.state.properties.WoodType.register(WoodType(path, blockSetType))
        log = BLOCKS.registerBlock(
            path + "_log", { LogBlock(it, strippedLog) },
            newProperties(Blocks.OAK_LOG).mapColor { if (it.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y) planksMapColor else barkMapColor },
        )
        wood = BLOCKS.registerBlock(
            path + "_wood", { LogBlock(it, strippedWood) },
            newProperties(Blocks.OAK_WOOD, mapColor = barkMapColor),
        )
        strippedLog = BLOCKS.registerBlock(
            "stripped_" + path + "_log", ::LogBlock,
            newProperties(Blocks.STRIPPED_OAK_LOG, mapColor = planksMapColor),
        )
        strippedWood = BLOCKS.registerBlock(
            "stripped_" + path + "_wood", ::LogBlock,
            newProperties(Blocks.STRIPPED_OAK_WOOD, mapColor = planksMapColor),
        )
        planks = BLOCKS.registerBlock(
            path + "_planks", ::Block,
            newProperties(Blocks.OAK_PLANKS, mapColor = planksMapColor),
        )
        val planksChecked = planks
        if (planksChecked != null) {
            stairs = BLOCKS.registerBlock(
                path + "_stairs",
                { StairBlock(planksChecked.value().defaultBlockState(), it) },
                newProperties(Blocks.OAK_STAIRS, mapColor = planksMapColor),
            )
            slab = BLOCKS.registerBlock(
                path + "_slab", ::SlabBlock,
                newProperties(Blocks.OAK_SLAB, mapColor = planksMapColor),
            )
            fence = BLOCKS.registerBlock(
                path + "_fence", ::FenceBlock,
                newProperties(Blocks.OAK_FENCE, mapColor = planksMapColor),
            )
            fenceGate = BLOCKS.registerBlock(
                path + "_fence_gate", { FenceGateBlock(mcWoodType, it) },
                newProperties(Blocks.OAK_FENCE_GATE, mapColor = planksMapColor),
            )
            door = BLOCKS.registerBlock(
                path + "_door", { DoorBlock(blockSetType, it) },
                newProperties(Blocks.OAK_DOOR, mapColor = planksMapColor),
            )
            trapdoor = BLOCKS.registerBlock(
                path + "_trapdoor", { TrapDoorBlock(blockSetType, it) },
                newProperties(Blocks.OAK_TRAPDOOR, mapColor = planksMapColor),
            )
            button = BLOCKS.registerBlock(
                path + "_button", { ButtonBlock(blockSetType, 30, it) },
                newProperties(Blocks.OAK_BUTTON, mapColor = planksMapColor),
            )
            pressurePlate = BLOCKS.registerBlock(
                path + "_pressure_plate", { PressurePlateBlock(blockSetType, it) },
                newProperties(Blocks.OAK_PRESSURE_PLATE, mapColor = planksMapColor),
            )
        }
    }.also { woodBlocks ->
        woodBlocks.values().forEach {
            ModItems.ITEMS.registerSimpleBlockItem(it)
        }
    }

    fun register() {}

}