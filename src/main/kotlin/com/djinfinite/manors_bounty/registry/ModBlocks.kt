package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.FruitLeavesBlock
import com.djinfinite.manors_bounty.content.LogBlock
import com.djinfinite.manors_bounty.content.durian.DurianBlock
import com.djinfinite.manors_bounty.content.durian.DurianFruitBlock
import com.djinfinite.manors_bounty.content.durian.DurianLeavesBlock
import com.djinfinite.manors_bounty.content.durian.DurianSaplingBlock
import com.djinfinite.manors_bounty.content.food.FoodJuiceFluid
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineBlock
import com.djinfinite.manors_bounty.content.pineapple.PineappleBlock
import com.djinfinite.manors_bounty.content.pineapple.PineappleCropBlock
import com.djinfinite.manors_bounty.util.FeatureUtils
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.SaplingBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.*
import java.util.function.Function

object ModBlocks {

    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(ManorsBounty.ID)

    // Pearl Rock Ore
    val PEARL_ROCK_ORE: DeferredBlock<Block> = BLOCKS.registerSimpleBlock(
        "pearl_rock_ore",
        newProperties().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.STONE).sound(SoundType.STONE)
            .strength(3F).requiresCorrectToolForDrops().lightLevel { 15 })
    val DEEPSLATE_PEARL_ROCK_ORE: DeferredBlock<Block> = BLOCKS.registerSimpleBlock(
        "deepslate_pearl_rock_ore",
        newProperties().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.DEEPSLATE).sound(SoundType.STONE)
            .strength(4.5F, 3F).requiresCorrectToolForDrops().lightLevel { 15 })

    // Ice Cream Machine
    val ICE_CREAM_MACHINE: DeferredBlock<IceCreamMachineBlock> = BLOCKS.registerBlock("ice_cream_machine") {
        IceCreamMachineBlock(
            newProperties().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_PINK)
                .sound(SoundType.METAL).strength(2F).noOcclusion().isRedstoneConductor { _, _, _ -> false })
    }

    // Pineapple
    val PINEAPPLE: DeferredBlock<PineappleBlock> = BLOCKS.registerBlock("pineapple") {
        PineappleBlock(
            newProperties(
                ignitedByLava = true,
                instrument = NoteBlockInstrument.BASS,
                mapColor = MapColor.COLOR_ORANGE,
                sound = SoundType.BAMBOO,
                destroyTime = 0.6F,
                noOcclusion = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }
    val PINEAPPLE_CROP: DeferredBlock<PineappleCropBlock> = BLOCKS.registerBlock("pineapple_crop") {
        PineappleCropBlock(
            newProperties(
                ignitedByLava = true,
                instrument = NoteBlockInstrument.BASS,
                mapColor = MapColor.GRASS,
                sound = SoundType.CROP,
                destroyTime = 0.6F,
                noOcclusion = true,
                noCollission = true,
                randomTicks = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }
    val PINEAPPLE_JUICE: DeferredBlock<LiquidBlock> = registerJuiceBlock("pineapple_juice", ModFluids.PINEAPPLE_JUICE)

    // Durian
    val DURIAN_SEED: DeferredBlock<DurianSaplingBlock> = BLOCKS.registerBlock("durian_seed") {
        DurianSaplingBlock(
            newProperties(
                mapColor = MapColor.TERRACOTTA_LIGHT_GREEN,
                sound = SoundType.GRASS,
                instabreak = true,
                noCollission = true,
                randomTicks = true,
                offsetType = BlockBehaviour.OffsetType.NONE,
                pushReaction = PushReaction.DESTROY,
            )
        )
    }
    val MUSANG_KING_DURIAN = registerBlock("musang_king_durian") {
        DurianBlock(
            newProperties(
                ignitedByLava = true,
                instrument = NoteBlockInstrument.BASS,
                mapColor = MapColor.TERRACOTTA_LIGHT_GREEN,
                sound = SoundType.BAMBOO,
                destroyTime = 0.6F,
                noOcclusion = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }

    val DURIAN_TREE_LOG = registerBlock("durian_tree_log") {
        LogBlock(newProperties(base = Blocks.OAK_LOG), STRIPPED_DURIAN_TREE_LOG)
    }
    val STRIPPED_DURIAN_TREE_LOG = registerBlock("stripped_durian_tree_log") {
        LogBlock(newProperties(base = Blocks.STRIPPED_OAK_LOG))
    }

    //    com.djinfinite.manors_bounty.registries.ModBlocks.STRIPPED_DURIAN_TREE_WOOD = com.djinfinite.manors_bounty.registries.ModBlocks.REGISTRY.register("stripped_durian_tree_wood", com.djinfinite.manors_bounty.registries.ModBlocks.getStrippedWood())
//    com.djinfinite.manors_bounty.registries.ModBlocks.DURIAN_TREE_WOOD = com.djinfinite.manors_bounty.registries.ModBlocks.REGISTRY.register("durian_tree_wood", com.djinfinite.manors_bounty.registries.ModBlocks.getWood(com.djinfinite.manors_bounty.registries.ModBlocks.STRIPPED_DURIAN_TREE_WOOD))
    val DURIAN_LEAVES = registerBlock("durian_tree_leaves") {
        DurianLeavesBlock(
            newProperties(
                ignitedByLava = true,
                mapColor = MapColor.TERRACOTTA_LIGHT_GREEN,
                sound = SoundType.GRASS,
                destroyTime = 0.2F,
                noOcclusion = true,
                randomTicks = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }

    //    val DURIAN_TREE_PLANKS = registerBlock("durian_tree_planks", val getPlanks ())
//    val DURIAN_TREE_STAIRS =
//        registerBlock("durian_tree_stairs", val getWoodenStair (com.djinfinite.manors_bounty.registries.ModBlocks.DURIAN_TREE_PLANKS))
//    val DURIAN_TREE_DOOR = registerBlock("durian_tree_door", val getWoodenDoor ())
//    val DURIAN_TREE_TRAPDOOR = registerBlock("durian_tree_trapdoor", val getWoodenTrapdoor ())
//    val DURIAN_TREE_FENCE = registerBlock("durian_tree_fence", val getWoodenFence ())
//    val DURIAN_TREE_FENCE_GATE = registerBlock("durian_tree_fence_gate", val getFenceGate ())
//    val DURIAN_TREE_SLAB = registerBlock("durian_tree_slab", val getWoodenSlab ())
//    val DURIAN_TREE_BOTTON = registerBlock("durian_tree_botton", val getWoodenButton ())
//    val DURIAN_TREE_PRESSURE_PLATE = registerBlock("durian_tree_pressure_plate", val getWoodenPressurePlate ())
    val DURIAN_FRUIT = registerBlock("durian_fruit") {
        DurianFruitBlock(
            newProperties(
                ignitedByLava = true,
                mapColor = MapColor.TERRACOTTA_LIGHT_GREEN,
                sound = SoundType.BAMBOO,
                destroyTime = 0.6F,
                noOcclusion = true,
                noCollission = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }

    // Scots Pine
    val SCOTS_PINE_LEAVES = registerBlockWithWoodType("scots_pine_leaves", ModWoodTypes.SCOTS_PINE) {
        LeavesBlock(newProperties(Blocks.SPRUCE_LEAVES, mapColor = MapColor.GLOW_LICHEN))
    }
    val SCOTS_PINE_SAPLING = registerBlockWithWoodType("scots_pine_sapling", ModWoodTypes.SCOTS_PINE) {
        SaplingBlock(
            TreeGrower(
                "scots_pine",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("scots_pine_2"), // Scots Pine 1 is buggy: the log disappeared
                Optional.empty(),
            ), newProperties(Blocks.SPRUCE_SAPLING, mapColor = MapColor.GLOW_LICHEN)
        )
    }

    // Alpine Tree
    val ALPINE_TREE_LEAVES = registerBlockWithWoodType("alpine_tree_leaves", ModWoodTypes.ALPINE_TREE) {
        LeavesBlock(newProperties(Blocks.SPRUCE_LEAVES, mapColor = MapColor.TERRACOTTA_CYAN))
    }

    // Cherries
    val CHERRIES_TREE_LEAVES = registerBlockWithWoodType("cherries_tree_leaves", ModWoodTypes.CHERRIES_TREE) {
        FruitLeavesBlock(
            ModItems.CHERRIES,
            fruitDropCount = 1..2,
            newProperties(Blocks.CHERRY_LEAVES, mapColor = MapColor.WARPED_WART_BLOCK)
        )
    }
    val CHERRIES_SEED = registerBlockWithWoodType("cherries_seed", ModWoodTypes.CHERRIES_TREE) {
        SaplingBlock(
            TreeGrower(
                "cherries_tree",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("cherries_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.CHERRY_SAPLING, mapColor = MapColor.WARPED_WART_BLOCK)
        )
    }

    // Starfruit
    val STARFRUIT_TREE_LEAVES = registerBlockWithWoodType("starfruit_tree_leaves", ModWoodTypes.STARFRUIT_TREE) {
        FruitLeavesBlock(
            ModItems.STARFRUIT,
            fruitDropCount = 1..1,
            newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.TERRACOTTA_LIGHT_GREEN)
        )
    }
    val STARFRUIT_SEED = registerBlockWithWoodType("starfruit_seed", ModWoodTypes.STARFRUIT_TREE) {
        SaplingBlock(
            TreeGrower(
                "starfruit",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("starfruit_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.TERRACOTTA_LIGHT_GREEN)
        )
    }

    // Olive
    val OLIVE_TREE_LEAVES = registerBlockWithWoodType("olive_tree_leaves", ModWoodTypes.OLIVE_TREE) {
        FruitLeavesBlock(
            ModItems.OLIVE_FRUIT, fruitDropCount = 2..3, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.GRASS)
        )
    }
    val OLIVE_FRUIT_SEED = registerBlockWithWoodType("olive_fruit_seed", ModWoodTypes.OLIVE_TREE) {
        SaplingBlock(
            TreeGrower(
                "olive",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("olive_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.GRASS)
        )
    }

    // Orange
    val ORANGE_LEAVES = registerBlockWithWoodType("orange_leaves", ModWoodTypes.RUTACEAE_TREE) {
        FruitLeavesBlock(
            ModItems.ORANGE, fruitDropCount = 1..2, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.COLOR_GREEN)
        )
    }
    val ORANGE_SEED = registerBlockWithWoodType("orange_seed", ModWoodTypes.RUTACEAE_TREE) {
        SaplingBlock(
            TreeGrower(
                "orange",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("orange_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.COLOR_GREEN)
        )
    }

    // Peach
    val PEACH_LEAVES = registerBlockWithWoodType("peach_leaves", ModWoodTypes.ROSACEAE_TREE) {
        FruitLeavesBlock(
            ModItems.PEACH, fruitDropCount = 1..2, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.COLOR_PINK)
        )
    }
    val PEACH_SEED = registerBlockWithWoodType("peach_seed", ModWoodTypes.ROSACEAE_TREE) {
        SaplingBlock(
            TreeGrower(
                "peach",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("peach_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.COLOR_PINK)
        )
    }

    // Pear
    val PEAR_LEAVES = registerBlockWithWoodType("pear_leaves", ModWoodTypes.ROSACEAE_TREE) {
        FruitLeavesBlock(
            ModItems.PEAR, fruitDropCount = 1..2, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.QUARTZ)
        )
    }
    val PEAR_SEED = registerBlockWithWoodType("pear_seed", ModWoodTypes.ROSACEAE_TREE) {
        SaplingBlock(
            TreeGrower(
                "pear",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("pear_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.QUARTZ)
        )
    }

    // Apple
    val APPLE_LEAVES = registerBlockWithWoodType("apple_leaves", ModWoodTypes.ROSACEAE_TREE) {
        FruitLeavesBlock(
            Items.APPLE, fruitDropCount = 1..2, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.WARPED_WART_BLOCK)
        )
    }
    val APPLE_SEED = registerBlockWithWoodType("apple_seed", ModWoodTypes.ROSACEAE_TREE) {
        SaplingBlock(
            TreeGrower(
                "apple",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("apple_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.WARPED_WART_BLOCK)
        )
    }

    // Mango
    val MANGO_TREE_LEAVES = registerBlockWithWoodType("mango_tree_leaves", ModWoodTypes.ROSACEAE_TREE) {
        FruitLeavesBlock(
            ModItems.MANGO, fruitDropCount = 1..2, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.COLOR_LIGHT_GREEN)
        )
    }
    val MANGO_SEED = registerBlockWithWoodType("mango_seed", ModWoodTypes.ROSACEAE_TREE) {
        SaplingBlock(
            TreeGrower(
                "mango",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("mango_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.COLOR_LIGHT_GREEN)
        )
    }

    // Kiwifruit
    val KIWIFRUIT_TREE_LEAVES = registerBlockWithWoodType("kiwifruit_tree_leaves", ModWoodTypes.ROSACEAE_TREE) {
        FruitLeavesBlock(
            ModItems.KIWIFRUIT, fruitDropCount = 1..2, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.EMERALD)
        )
    }
    val KIWIFRUIT_SEED = registerBlockWithWoodType("kiwifruit_seed", ModWoodTypes.ROSACEAE_TREE) {
        SaplingBlock(
            TreeGrower(
                "kiwifruit",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("kiwifruit_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.EMERALD)
        )
    }

    // Avocado
    val AVOCADO_TREE_LEAVES = registerBlockWithWoodType("avocado_tree_leaves", ModWoodTypes.ROSACEAE_TREE) {
        FruitLeavesBlock(
            ModItems.AVOCADO, fruitDropCount = 1..1, newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.TERRACOTTA_GREEN)
        )
    }
    val AVOCADO_SEED = registerBlockWithWoodType("avocado_seed", ModWoodTypes.ROSACEAE_TREE) {
        SaplingBlock(
            TreeGrower(
                "avocado",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("avocado_tree_spawn"),
                Optional.empty(),
            ), newProperties(Blocks.OAK_SAPLING, mapColor = MapColor.TERRACOTTA_GREEN)
        )
    }

    val BLACK_DIRT = registerBlock("black_dirt") {
        Block(newProperties(Blocks.DIRT))
    }

    private fun <B : Block> registerBlock(
        id: String, func: Function<BlockBehaviour.Properties, out B>
    ): DeferredBlock<B> {
        return BLOCKS.registerBlock(id, func, BlockBehaviour.Properties.of())
    }

    private fun <B : Block> registerBlockWithWoodType(
        id: String, woodType: ModWoodTypes.WoodType, func: Function<BlockBehaviour.Properties, out B>
    ): DeferredBlock<B> {
        return BLOCKS.registerBlock(id, func, BlockBehaviour.Properties.of())
    }

    private fun registerJuiceBlock(
        id: String, fluid: DeferredHolder<Fluid, FoodJuiceFluid.Source>
    ): DeferredBlock<LiquidBlock> {
        return BLOCKS.registerBlock(id) {
            LiquidBlock(
                fluid.get(), newProperties(
                    mapColor = MapColor.WATER,
                    destroyTime = 100F,
                    noCollission = true,
                    noLootTable = true,
                    isLiquid = true,
                    pushReaction = PushReaction.DESTROY,
                    sound = SoundType.EMPTY,
                    isReplaceable = true
                )
            )
        }
    }

    fun newProperties(
        base: BlockBehaviour? = null,
        ignitedByLava: Boolean = false,
        instrument: NoteBlockInstrument = NoteBlockInstrument.BASEDRUM,
        mapColor: MapColor = MapColor.STONE,
        sound: SoundType = SoundType.STONE,
        destroyTime: Float = 0F,
        explosionResistance: Float? = null,
        noOcclusion: Boolean = false,
        noCollission: Boolean = false,
        noLootTable: Boolean = false,
        isLiquid: Boolean = false,
        randomTicks: Boolean = false,
        isReplaceable: Boolean = false,
        instabreak: Boolean = false,
        pushReaction: PushReaction? = null,
        offsetType: BlockBehaviour.OffsetType? = null,
        isRedstoneConductor: IsRedstoneConductor? = null
    ): BlockBehaviour.Properties {
        val prop = if (base == null) BlockBehaviour.Properties.of() else BlockBehaviour.Properties.ofFullCopy(base)
        return prop.apply {
            instrument(instrument)
            mapColor(mapColor)
            sound(sound)
            strength(destroyTime, explosionResistance ?: destroyTime)
            if (ignitedByLava) ignitedByLava()
            if (noOcclusion) noOcclusion()
            if (noCollission) noCollission()
            if (randomTicks) randomTicks()
            if (noLootTable) noLootTable()
            if (isLiquid) liquid()
            if (isReplaceable) replaceable()
            if (instabreak) instabreak()
            if (offsetType != null) offsetType(offsetType)
            if (pushReaction != null) pushReaction(pushReaction)
            if (isRedstoneConductor != null) isRedstoneConductor(isRedstoneConductor.predicate)
        }
    }

    sealed class IsRedstoneConductor(val predicate: BlockBehaviour.StatePredicate) {
        data object Always : IsRedstoneConductor({ _, _, _ -> true })
        data object Never : IsRedstoneConductor({ _, _, _ -> false })
    }

}