package com.djinfinite.manors_bounty.content.food

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.UseAnim

open class FoodItem2(
    private val useAnim: UseAnim?, properties: Properties
) : Item(properties) {

    override fun getUseAnimation(stack: ItemStack): UseAnim = useAnim ?: super.getUseAnimation(stack)

}