package com.djinfinite.manors_bounty.data

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.registry.ModItems
import net.minecraft.data.PackOutput
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.BucketItem
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModItemModelProvider(output: PackOutput, existingFileHelper: ExistingFileHelper) : ItemModelProvider(
    output, ManorsBounty.MOD_ID, existingFileHelper
) {

    override fun registerModels() {
        ModItems.ITEMS.entries.forEach {
            when (it.get()) {
                is BlockItem -> {}
                is BucketItem -> {}
                else -> { basicItem(it.get()) }
            }
        }
    }

}