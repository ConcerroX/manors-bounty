package com.djinfinite.manors_bounty.content

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.CandleCakeBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class SimpleCandleCakeBlock(
    cakeBlock: Block, candleBlock: Block, properties: Properties
) : CandleCakeBlock(candleBlock, properties) {

    var cakeBlock: CakeBlock
    var candleBlock: CandleBlock

    init {
        if (cakeBlock !is CakeBlock) {
            throw IllegalArgumentException("Expected block to be of " + CakeBlock::class.java + " was " + cakeBlock.javaClass)
        } else if (candleBlock !is CandleBlock) {
            throw IllegalArgumentException("Expected block to be of " + CandleBlock::class.java + " was " + candleBlock.javaClass)
        } else {
            this.cakeBlock = cakeBlock
            this.candleBlock = candleBlock
            BY_CANDLE.getOrPut(cakeBlock) { mutableMapOf() }[candleBlock] = this
        }
    }

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hitResult: BlockHitResult
    ): InteractionResult {
        val result = CakeBlock.eat(level, pos, cakeBlock.defaultBlockState(), player)
        if (result.consumesAction()) {
            dropResources(state, level, pos)
        }
        return result
    }

    @Deprecated("Deprecated in Java", ReplaceWith("ItemStack(cakeBlock)", "net.minecraft.world.item.ItemStack"))
    override fun getCloneItemStack(level: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(cakeBlock)
    }

    companion object {
        private val BY_CANDLE = mutableMapOf<CakeBlock, MutableMap<CandleBlock, CandleCakeBlock>>()

        val CODEC: MapCodec<SimpleCandleCakeBlock> = RecordCodecBuilder.mapCodec {
            it.group(
                BuiltInRegistries.BLOCK.byNameCodec().fieldOf("cake").forGetter(SimpleCandleCakeBlock::cakeBlock),
                BuiltInRegistries.BLOCK.byNameCodec().fieldOf("candle").forGetter(SimpleCandleCakeBlock::candleBlock),
                propertiesCodec(),
            ).apply(it, ::SimpleCandleCakeBlock)
        }

        fun byCandle(cakeBlock: CakeBlock, candle: CandleBlock): BlockState? {
            return BY_CANDLE[cakeBlock]?.get(candle)?.defaultBlockState()
        }
    }
}