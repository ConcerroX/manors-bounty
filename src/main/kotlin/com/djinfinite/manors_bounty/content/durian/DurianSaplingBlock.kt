package com.djinfinite.manors_bounty.content.durian

import com.djinfinite.manors_bounty.util.FeatureUtils
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.SaplingBlock
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.world.level.block.state.BlockState
import java.util.*

class DurianSaplingBlock(properties: Properties) : SaplingBlock(DURIAN_TREE_GROWER, properties) {

    companion object {
        val DURIAN_TREE_GROWER by lazy {
            TreeGrower(
                "durian", Optional.empty(),
                FeatureUtils.createOptionalConfiguredFeatureKey("durian_tree_spawn"),
                Optional.empty(),
            )
        }
    }

    override fun getFlammability(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction) = 100
    override fun getFireSpreadSpeed(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction) = 60

}