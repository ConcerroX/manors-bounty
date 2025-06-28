package com.djinfinite.manors_bounty.content.mobeffect

import com.djinfinite.manors_bounty.ManorsBountyConfig
import com.djinfinite.manors_bounty.res
import com.djinfinite.manors_bounty.util.chance
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeMap
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes

class CherryBlossomsWeepingMobEffect(category: MobEffectCategory, color: Int) : MobEffect(category, color) {

    companion object {
        val MODIFIER_ID = res("effect.cherry_blossoms_weeping.attack_knockback")
    }

    override fun addAttributeModifiers(attributeMap: AttributeMap, amplifier: Int) {
        attributeMap.getInstance(Attributes.ATTACK_KNOCKBACK)?.addPermanentModifier(
            AttributeModifier(
                MODIFIER_ID,
                1 + (amplifier + 1) * ManorsBountyConfig.cherryBlossomsWeepingKnockbackMultiplier.get(),
                AttributeModifier.Operation.ADD_VALUE
            )
        )
        super.addAttributeModifiers(attributeMap, amplifier)
    }

    override fun removeAttributeModifiers(attributeMap: AttributeMap) {
        attributeMap.getInstance(Attributes.ATTACK_KNOCKBACK)?.removeModifier(MODIFIER_ID)
        super.removeAttributeModifiers(attributeMap)
    }

    override fun applyEffectTick(livingEntity: LivingEntity, amplifier: Int): Boolean {
        val level = livingEntity.level()
        if (chance(0.2) && level is ServerLevel) {
            level.sendParticles(
                ParticleTypes.CHERRY_LEAVES,
                livingEntity.x,
                livingEntity.y + livingEntity.eyeHeight.toDouble(),
                livingEntity.z,
                Mth.nextInt(RandomSource.create(), 1, 3),
                1.0,
                1.0,
                1.0,
                0.05
            )
        }
        return super.applyEffectTick(livingEntity, amplifier)
    }

    override fun shouldApplyEffectTickThisTick(duration: Int, amplifier: Int) = true

}