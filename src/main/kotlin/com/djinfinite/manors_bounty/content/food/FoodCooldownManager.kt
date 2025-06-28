package com.djinfinite.manors_bounty.content.food

import com.djinfinite.manors_bounty.registry.ModDataComponents
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

object FoodCooldownManager {

    fun cooldown(stack: ItemStack, livingEntity: LivingEntity) {
        if (livingEntity !is Player) return
        if (stack.has(ModDataComponents.FRUIT_EFFECT)) {
            val data = stack.get(ModDataComponents.FRUIT_EFFECT) ?: return
            if (data.foodType.sharedCooldownTime) {
                data.foodIngredientType.items.filter {
                    it.components().get(ModDataComponents.FRUIT_EFFECT.get())?.foodType == data.foodType
                }.forEach {
                    it.components().get(ModDataComponents.FRUIT_EFFECT.get())?.cooldownTime?.let { t ->
                        livingEntity.cooldowns.addCooldown(it, t)
                    }
                }
            } else {
                livingEntity.cooldowns.addCooldown(stack.item, data.foodType.cooldownTime)
            }
        }
    }

}