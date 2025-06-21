package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.food.FoodJuiceFluid
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineBlock
import com.djinfinite.manors_bounty.content.pineapple.PineappleBlock
import com.djinfinite.manors_bounty.content.pineapple.PineappleCropBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModBlocks {

    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(ManorsBounty.ID)

    val PEARL_ROCK_ORE: DeferredBlock<Block> = BLOCKS.registerSimpleBlock(
        "pearl_rock_ore",
        newProperties().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.STONE).sound(SoundType.STONE)
            .strength(3F).requiresCorrectToolForDrops().lightLevel { 15 })
    val DEEPSLATE_PEARL_ROCK_ORE: DeferredBlock<Block> = BLOCKS.registerSimpleBlock(
        "deepslate_pearl_rock_ore",
        newProperties().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.DEEPSLATE).sound(SoundType.STONE)
            .strength(4.5F, 3F).requiresCorrectToolForDrops().lightLevel { 15 })

    val ICE_CREAM_MACHINE: DeferredBlock<IceCreamMachineBlock> = BLOCKS.registerBlock("ice_cream_machine") {
        IceCreamMachineBlock(
            newProperties().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_PINK)
                .sound(SoundType.METAL).strength(2F).noOcclusion().isRedstoneConductor { _, _, _ -> false })
    }

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
    val PINEAPPLE_CROP: DeferredBlock<PineappleCropBlock> = BLOCKS.registerBlock(
        "pineapple_crop"
    ) {
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

    private fun newProperties(
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
        pushReaction: PushReaction? = null,
        isRedstoneConductor: IsRedstoneConductor? = null
    ): BlockBehaviour.Properties = BlockBehaviour.Properties.of().apply {
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
        if (pushReaction != null) pushReaction(pushReaction)
        if (isRedstoneConductor != null) isRedstoneConductor(isRedstoneConductor.predicate)
    }

    private sealed class IsRedstoneConductor(val predicate: BlockBehaviour.StatePredicate) {
        data object Always : IsRedstoneConductor({ _, _, _ -> true })
        data object Never : IsRedstoneConductor({ _, _, _ -> false })
    }

}
