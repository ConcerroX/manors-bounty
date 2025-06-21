package com.djinfinite.manors_bounty.content.food

import com.djinfinite.manors_bounty.util.letChanced
import com.djinfinite.manors_bounty.util.valueChanced
import net.minecraft.core.Holder
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.UseAnim
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level

open class FoodItem(
    properties: Properties,
    val foodIngredientType: FoodIngredientType,
    val foodType: FoodType,
    private val foodEffect: Holder<MobEffect>,
    private val returnItem: ItemLike? = null,
    private val sliceCount: Int = 1,
    private val useAnimation: UseAnim? = null
) : Item(properties) {

    private val isInCooldown = false
    private val applyChance: Float
        get() = foodType.applyChance / sliceCount
    private val upgradeChance: Float
        get() = foodType.upgradeChance / sliceCount
    val cooldownTime: Int
        get() = foodType.cooldownTime / sliceCount

    override fun getUseAnimation(stack: ItemStack): UseAnim = useAnimation ?: super.getUseAnimation(stack)

    override fun finishUsingItem(stack: ItemStack, level: Level, livingEntity: LivingEntity): ItemStack {
        if (!isInCooldown) {
            val effect = livingEntity.getEffect(foodEffect)
            if (effect != null) {
                if (foodType == FoodType.A) FoodCooldownManager.cooldown(this, livingEntity)
                if (effect.amplifier < foodType.maxAmplifier) {
                    livingEntity.addEffect(
                        MobEffectInstance(
                            foodEffect,
                            effect.duration + valueChanced(upgradeChance, foodType.extensionTime, 0),
                            effect.amplifier + valueChanced(upgradeChance, 1, 0),
                        )
                    )
                    if (foodType == FoodType.B) FoodCooldownManager.cooldown(this, livingEntity)
                }
            } else letChanced(applyChance) {
                livingEntity.addEffect(MobEffectInstance(foodEffect, foodType.applyTime, 0))
                if (foodType == FoodType.B) FoodCooldownManager.cooldown(this, livingEntity)
            }
            if (foodType == FoodType.C) FoodCooldownManager.cooldown(this, livingEntity)
        }
        val result = super.finishUsingItem(stack, level, livingEntity)
        return getReturnItem(livingEntity, result, returnItem)
    }

    private fun getReturnItem(livingEntity: LivingEntity, result: ItemStack, returnItem: ItemLike?): ItemStack {
        return if ((livingEntity is Player && livingEntity.isCreative) || returnItem == null) {
            result
        } else {
            if (result.isEmpty) {
                ItemStack(returnItem)
            } else {
                if (livingEntity is Player) livingEntity.inventory.add(ItemStack(returnItem))
                result
            }
        }
    }

}