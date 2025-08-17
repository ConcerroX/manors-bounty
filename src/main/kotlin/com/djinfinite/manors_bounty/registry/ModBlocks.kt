package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.FruitLeavesBlock
import com.djinfinite.manors_bounty.content.SimpleCakeBlock
import com.djinfinite.manors_bounty.content.SimpleCandleCakeBlock
import com.djinfinite.manors_bounty.content.SimpleLogBlock
import com.djinfinite.manors_bounty.content.durian.DurianBlock
import com.djinfinite.manors_bounty.content.durian.DurianFruitBlock
import com.djinfinite.manors_bounty.content.durian.DurianLeavesBlock
import com.djinfinite.manors_bounty.content.durian.DurianSaplingBlock
import com.djinfinite.manors_bounty.content.food.SimpleFluid
import com.djinfinite.manors_bounty.content.fryer.FryerBlock
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineBlock
import com.djinfinite.manors_bounty.content.pineapple.PineappleBlock
import com.djinfinite.manors_bounty.content.pineapple.PineapplePlantletBlock
import com.djinfinite.manors_bounty.util.FeatureUtils
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.*
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

    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(ManorsBounty.MOD_ID)

    // Pearl Rock Ore
    val PERLITE_ORE = registerBlock("pearl_rock_ore") {
        DropExperienceBlock(UniformInt.of(2, 6), newProperties(Blocks.IRON_ORE, lightLevel = 15))
    }
    val DEEPSLATE_PERLITE_ORE = registerBlock("deepslate_pearl_rock_ore") {
        DropExperienceBlock(UniformInt.of(2, 6), newProperties(Blocks.DEEPSLATE_IRON_ORE, lightLevel = 15))
    }

    // Ice Cream Machine
    val ICE_CREAM_MACHINE = registerBlock("ice_cream_machine") {
        IceCreamMachineBlock(
            newProperties(
                mapColor = MapColor.COLOR_PINK,
                sound = SoundType.METAL,
                destroyTime = 2F,
                noOcclusion = true,
                requiresCorrectToolForDrops = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }
    val FRYER = registerBlock("fryer") {
        FryerBlock(
            newProperties(
                mapColor = MapColor.WOOL,
                sound = SoundType.METAL,
                destroyTime = 2F,
                noOcclusion = true,
                requiresCorrectToolForDrops = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }
    val OVEN = registerBlock("oven") {
        IceCreamMachineBlock(
            newProperties(
                mapColor = MapColor.METAL,
                sound = SoundType.METAL,
                destroyTime = 2F,
                noOcclusion = true,
                requiresCorrectToolForDrops = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }

    // Chocolate
    val CARAMEL_CHOCOLATE_CAKE = registerBlock("caramel_chocolate_cake") {
        SimpleCakeBlock(newProperties(Blocks.CAKE, mapColor = MapColor.COLOR_BROWN))
    }
    val CARAMEL_CHOCOLATE_CANDLE_CAKE = registerBlock("caramel_chocolate_candle_cake") {
        SimpleCandleCakeBlock(
            CARAMEL_CHOCOLATE_CAKE.get(),
            Blocks.CANDLE,
            newProperties(Blocks.CANDLE_CAKE, mapColor = MapColor.COLOR_BROWN)
        )
    }

    val LAVENDER = registerBlock("lavender") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val DEBRISHROOM = registerBlock("debrishroom") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val POLISHED_MARBLE = registerBlock("polished_marble") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val POMEGRANATE_SEEDS = registerBlock("pomegranate_seeds") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val DRAGON_FRUIT_CACTUS = registerBlock("dragon_fruit_cactus") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val DRAGON_FRUIT_CACTUS_FLOWER = registerBlock("dragon_fruit_cactus_flower") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val CHRISTMAS_STOCKING = registerBlock("christmas_stocking") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val CHRISTMAS_WREATHS = registerBlock("christmas_wreaths") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }
    val CUTTING_BOARD = registerBlock("cutting_board") {
        Block(newProperties(Blocks.LILY_OF_THE_VALLEY))
    }

    // Chorus Fruit
    val CHORUS_FLOWER_JELLY_CAKE = registerBlock("chorus_flower_jelly_cake") {
        SimpleCakeBlock(newProperties(Blocks.CAKE, mapColor = MapColor.COLOR_PURPLE))
    }
    val CHORUS_FLOWER_JELLY_CANDLE_CAKE = registerBlock("candle_chorus_flower_jelly_cake") {
        SimpleCandleCakeBlock(
            CHORUS_FLOWER_JELLY_CAKE.get(),
            Blocks.CANDLE,
            newProperties(Blocks.CANDLE_CAKE, mapColor = MapColor.COLOR_PURPLE)
        )
    }

    // Nether Wart
    val NETHER_WART_SOUL_CAKE = registerBlock("nether_wart_soul_cake") {
        SimpleCakeBlock(newProperties(Blocks.CAKE, mapColor = MapColor.WARPED_HYPHAE))
    }
    val NETHER_WART_SOUL_CANDLE_CAKE = registerBlock("candle_nether_wart_soul_cake") {
        SimpleCandleCakeBlock(
            NETHER_WART_SOUL_CAKE.get(),
            Blocks.CANDLE,
            newProperties(Blocks.CANDLE_CAKE, mapColor = MapColor.WARPED_HYPHAE)
        )
    }

    // Sweet Berry
    val SWEET_BERRY_ICE_CREAM_CAKE = registerBlock("sweet_berry_ice_cream_cake") {
        SimpleCakeBlock(newProperties(Blocks.CAKE, mapColor = MapColor.COLOR_PINK))
    }
    val SWEET_BERRY_CANDLE_CAKE = registerBlock("candle_sweet_berry_ice_cream_cake") {
        SimpleCandleCakeBlock(
            SWEET_BERRY_ICE_CREAM_CAKE.get(), Blocks.CANDLE, newProperties(Blocks.CANDLE_CAKE, mapColor = MapColor.COLOR_PINK)
        )
    }

    // Olive
    val OLIVE_OIL = registerFluidBlock("olive_oil", ModFluids.OLIVE_OIL)

    // IDK
    val CAKE_LIQUID = registerFluidBlock("cake_liquid", ModFluids.CAKE_LIQUID)
    val VANILLA_ICE_CREAM = registerFluidBlock("vanilla_ice_cream", ModFluids.VANILLA_ICE_CREAM)
    val CHOCOLATE_ICE_CREAM = registerFluidBlock("chocolate_ice_cream", ModFluids.CHOCOLATE_ICE_CREAM)
    val BLUEBERRY_ICE_CREAM = registerFluidBlock("blueberry_ice_cream", ModFluids.BLUEBERRY_ICE_CREAM)
    val CHERRY_ICE_CREAM = registerFluidBlock("cherry_ice_cream", ModFluids.CHERRY_ICE_CREAM)
    val STARFRUIT_ICE_CREAM = registerFluidBlock("starfruit_ice_cream", ModFluids.STARFRUIT_ICE_CREAM)
    val JALAPENO_ICE_CREAM = registerFluidBlock("jalapeno_ice_cream", ModFluids.JALAPENO_ICE_CREAM)

    // Pineapple
    val PINEAPPLE = registerBlock("pineapple") {
        PineappleBlock(
            newProperties(
                instrument = NoteBlockInstrument.BASS,
                mapColor = MapColor.COLOR_ORANGE,
                sound = SoundType.BAMBOO,
                destroyTime = 0.6F,
                noOcclusion = true,
                isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }
    val PINEAPPLE_PLANTLET = registerBlock("pineapple_plantlet") {
        PineapplePlantletBlock(
            newProperties(
                Blocks.WHEAT, destroyTime = 0.6F, noOcclusion = true, isRedstoneConductor = IsRedstoneConductor.Never
            )
        )
    }
    val PINEAPPLE_JUICE = registerFluidBlock("pineapple_juice", ModFluids.PINEAPPLE_JUICE)

    // Durian
    val DURIAN_SEED = registerBlock("durian_seed") {
        DurianSaplingBlock(newProperties(Blocks.OAK_SAPLING))
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
        SimpleLogBlock(newProperties(base = Blocks.OAK_LOG), STRIPPED_DURIAN_TREE_LOG)
    }
    val STRIPPED_DURIAN_TREE_LOG = registerBlock("stripped_durian_tree_log") {
        SimpleLogBlock(newProperties(base = Blocks.STRIPPED_OAK_LOG))
    }

    //    com.djinfinite.manors_bounty.registries.ModBlocks.STRIPPED_DURIAN_TREE_WOOD = com.djinfinite.manors_bounty.registries.ModBlocks.REGISTRY.register("stripped_durian_tree_wood", com.djinfinite.manors_bounty.registries.ModBlocks.getStrippedWood())
//    com.djinfinite.manors_bounty.registries.ModBlocks.DURIAN_TREE_WOOD = com.djinfinite.manors_bounty.registries.ModBlocks.REGISTRY.register("durian_tree_wood", com.djinfinite.manors_bounty.registries.ModBlocks.getWood(com.djinfinite.manors_bounty.registries.ModBlocks.STRIPPED_DURIAN_TREE_WOOD))
    val DURIAN_LEAVES = registerBlock("durian_tree_leaves") {
        DurianLeavesBlock(newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.TERRACOTTA_LIGHT_GREEN))
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
    val CHERRY_TREE_LEAVES = registerBlockWithWoodType("cherry_tree_leaves", ModWoodTypes.CHERRY_TREE) {
        FruitLeavesBlock(
            ModItems.CHERRIES,
            fruitDropCount = 1..2,
            newProperties(Blocks.CHERRY_LEAVES, mapColor = MapColor.WARPED_WART_BLOCK)
        )
    }
    val CHERRY_SEEDS = registerBlockWithWoodType("cherry_seeds", ModWoodTypes.CHERRY_TREE) {
        SaplingBlock(
            TreeGrower(
                "cherry_tree",
                Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("cherry_tree_spawn"),
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
    val STARFRUIT_SEEDS = registerBlockWithWoodType("starfruit_seeds", ModWoodTypes.STARFRUIT_TREE) {
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
    val OLIVE_SEED = registerBlockWithWoodType("olive_fruit_seed", ModWoodTypes.OLIVE_TREE) {
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
    val ORANGE_SEEDS = registerBlockWithWoodType("orange_seeds", ModWoodTypes.RUTACEAE_TREE) {
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
    val PEAR_SEEDS = registerBlockWithWoodType("pear_seeds", ModWoodTypes.ROSACEAE_TREE) {
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
    val APPLE_SEEDS = registerBlockWithWoodType("apple_seeds", ModWoodTypes.ROSACEAE_TREE) {
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
            ModItems.MANGO,
            fruitDropCount = 1..2,
            newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.COLOR_LIGHT_GREEN)
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
            ModItems.AVOCADO,
            fruitDropCount = 1..1,
            newProperties(Blocks.OAK_LEAVES, mapColor = MapColor.TERRACOTTA_GREEN)
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

    // Snow Color Blocks
    val SNOW_TERRACOTTA = registerBlock("snow_terracotta") {
        Block(newProperties(Blocks.TERRACOTTA, mapColor = MapColor.DIAMOND))
    }
    val SNOW_GLAZED_TERRACOTTA = registerBlock("snow_glazed_terracotta") {
        Block(newProperties(Blocks.WHITE_GLAZED_TERRACOTTA, mapColor = MapColor.DIAMOND))
    }
    val SNOW_CONCRETE = registerBlock("snow_concrete") {
        Block(newProperties(Blocks.WHITE_CONCRETE, mapColor = MapColor.DIAMOND))
    }
    val SNOW_CONCRETE_POWDER = registerBlock("snow_concrete_powder") {
        Block(newProperties(Blocks.WHITE_CONCRETE_POWDER, mapColor = MapColor.DIAMOND))
    }
    val SNOW_GLASS = registerBlock("snow_glass") {
        Block(newProperties(Blocks.GLASS, mapColor = MapColor.DIAMOND))
    }
    val SNOW_GLASS_PANE = registerBlock("snow_glass_pane") {
        Block(newProperties(Blocks.GLASS_PANE, mapColor = MapColor.DIAMOND))
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

    private fun registerFluidBlock(
        id: String, fluid: DeferredHolder<Fluid, SimpleFluid.Source>
    ): DeferredBlock<LiquidBlock> {
        return BLOCKS.registerBlock(id) { LiquidBlock(fluid.get(), newProperties(Blocks.WATER)) }
    }

    fun newProperties(
        base: BlockBehaviour? = null,
        ignitedByLava: Boolean = false,
        instrument: NoteBlockInstrument = NoteBlockInstrument.BASEDRUM,
        mapColor: MapColor = MapColor.STONE,
        sound: SoundType = SoundType.STONE,
        lightLevel: Int = 0,
        destroyTime: Float = 0F,
        explosionResistance: Float? = null,
        noOcclusion: Boolean = false,
        noCollission: Boolean = false,
        noLootTable: Boolean = false,
        isLiquid: Boolean = false,
        randomTicks: Boolean = false,
        isReplaceable: Boolean = false,
        instabreak: Boolean = false,
        requiresCorrectToolForDrops: Boolean = false,
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
            lightLevel { lightLevel }
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
            if (requiresCorrectToolForDrops) requiresCorrectToolForDrops()
            if (isRedstoneConductor != null) isRedstoneConductor(isRedstoneConductor.predicate)
        }
    }

    sealed class IsRedstoneConductor(val predicate: BlockBehaviour.StatePredicate) {
        data object Always : IsRedstoneConductor({ _, _, _ -> true })
        data object Never : IsRedstoneConductor({ _, _, _ -> false })
    }

}