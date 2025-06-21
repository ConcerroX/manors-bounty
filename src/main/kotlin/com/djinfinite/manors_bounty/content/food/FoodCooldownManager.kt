package com.djinfinite.manors_bounty.content.food

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player

object FoodCooldownManager {

    fun cooldown(item: FoodItem, livingEntity: LivingEntity) {
        val foodType = item.foodType
        if (livingEntity is Player) {
//                val data = livingEntity.getData(ModAttachmentTypes.COOLDOWN)
            if (foodType.sharedCooldownTime) {
//                    livingEntity.setData(ModAttachmentTypes.COOLDOWN, CooldownAttachmentData(data.ingredients.plus()))
                item.foodIngredientType.items.filter { it.foodType == foodType }.forEach {
                    livingEntity.cooldowns.addCooldown(it, it.cooldownTime)
                }
            } else {
                livingEntity.cooldowns.addCooldown(item, foodType.cooldownTime)
            }
        }
    }

}