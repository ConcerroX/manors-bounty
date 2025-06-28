package com.djinfinite.manors_bounty.content

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult

class SimpleCakeBlock(properties: Properties) : CakeBlock(properties) {

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hitResult: BlockHitResult
    ): InteractionResult {
        val result = super.useWithoutItem(state, level, pos, player, hitResult)
        if (result == InteractionResult.SUCCESS) {

        } else {

        }
        return result
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        return if (stack.`is`(ItemTags.CANDLES) && state.getValue(BITES) == 0 && Block.byItem(stack.item) is CandleBlock) {
            stack.consume(1, player)
            level.playSound(null, pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0f, 1.0f)
            SimpleCandleCakeBlock.byCandle(this, Block.byItem(stack.item) as CandleBlock)
                ?.let { level.setBlockAndUpdate(pos, it) }
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos)
            player.awardStat(Stats.ITEM_USED[stack.item])
            ItemInteractionResult.SUCCESS
        } else {
            ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
        }
    }

}