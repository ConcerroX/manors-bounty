package com.djinfinite.manors_bounty.content.food

import com.djinfinite.manors_bounty.util.valueChanced
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import kotlin.random.Random

data class FruitEffectDataComponent(
    val foodIngredientType: FoodIngredientType,
    val foodType: FoodType,
    val foodEffect: Holder<MobEffect>? = null,
    val foodEffects: List<Holder<MobEffect>>? = null,
    val sliceCount: Int = 1,
) {

    private val isInCooldown = false // TODO: add this
    private val applyChance: Float
        get() = foodType.applyChance / sliceCount
    private val upgradeChance: Float
        get() = foodType.upgradeChance / sliceCount
    private val extensionChance: Float
        get() = foodType.extensionChance / sliceCount
    val cooldownTime: Int
        get() = foodType.cooldownTime / sliceCount

    fun consume(level: Level, entity: LivingEntity, stack: ItemStack) {
        val foodEffects = foodEffects ?: listOf(foodEffect!!)
        if (!isInCooldown) {
            val extensionDuration = valueChanced(extensionChance, foodType.extensionTime, 0)
            val extraAmplifier = valueChanced(upgradeChance, 1, 0)
            val apply = Random.nextFloat()
            foodEffects.forEach { foodEffect ->
                val effect = entity.getEffect(foodEffect)
                if (effect != null) {
                    if (foodType == FoodType.A) FoodCooldownManager.cooldown(stack, entity)
                    if (effect.amplifier < foodType.maxAmplifier) {
                        entity.addEffect(
                            MobEffectInstance(
                                foodEffect, effect.duration + extensionDuration, effect.amplifier + extraAmplifier
                            )
                        )
                        if (foodType == FoodType.B) FoodCooldownManager.cooldown(stack, entity)
                    }
                } else if (apply < applyChance) {
                    entity.addEffect(MobEffectInstance(foodEffect, foodType.applyTime, 0))
                    if (foodType == FoodType.B) FoodCooldownManager.cooldown(stack, entity)
                }
            }
            if (foodType == FoodType.C) FoodCooldownManager.cooldown(stack, entity)
        }
    }

    companion object {
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, FruitEffectDataComponent> = StreamCodec.composite(
            FoodIngredientType.STREAM_CODEC,
            FruitEffectDataComponent::foodIngredientType,
            FoodType.STREAM_CODEC,
            FruitEffectDataComponent::foodType,
            ByteBufCodecs.holderRegistry(Registries.MOB_EFFECT),
            FruitEffectDataComponent::foodEffect,
            ByteBufCodecs.holderRegistry(Registries.MOB_EFFECT).apply(ByteBufCodecs.list(256)),
            FruitEffectDataComponent::foodEffects,
            ByteBufCodecs.INT,
            FruitEffectDataComponent::sliceCount,
            ::FruitEffectDataComponent,
        )
    }

}