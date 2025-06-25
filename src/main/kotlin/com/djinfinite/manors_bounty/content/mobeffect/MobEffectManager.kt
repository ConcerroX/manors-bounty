package com.djinfinite.manors_bounty.content.mobeffect

import com.djinfinite.manors_bounty.ManorsBountyConfig
import com.djinfinite.manors_bounty.registry.ModMobEffects
import com.djinfinite.manors_bounty.util.chance
import com.djinfinite.manors_bounty.util.inWholeTicks
import com.djinfinite.manors_bounty.util.letChanced
import com.djinfinite.manors_bounty.util.realAmplifier
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent
import kotlin.math.min
import kotlin.time.Duration.Companion.seconds

object MobEffectManager {

    fun applyHackedThorns(event: LivingIncomingDamageEvent) {
        val entity = event.entity
        val source = event.source
        val causingEntity = source.entity
        val effect = entity.getEffect(ModMobEffects.HACKED_THORNS)
        val isProjectile = !source.isDirect
        if (effect != null && source.entity != null) {
            val force = ManorsBountyConfig.hackedThornsKnockbackAmount.get() * effect.realAmplifier
            if (isProjectile && chance(ManorsBountyConfig.hackedThornsKnockbackChance.get() * effect.realAmplifier)) {
                entity.invulnerableTime += ManorsBountyConfig.hackedThornsInvulnerableTime.get().seconds.inWholeTicks
                causingEntity?.let {
                    it.deltaMovement = causingEntity.position().subtract(entity.position()).normalize().scale(force)
                }
            } else {
                if (causingEntity !is LivingEntity) return
                causingEntity.deltaMovement =
                    causingEntity.position().subtract(entity.position()).normalize().scale(force)
                causingEntity.addEffect(
                    MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN,
                        ManorsBountyConfig.hackedThornsSlownessTime.get().seconds.inWholeTicks,
                        min(effect.amplifier, 3),
                        false,
                        true
                    )
                )
            }
            entity.level().playSound(
                entity, entity.blockPosition(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, SoundSource.PLAYERS, 0.8F, 1.5F
            )
        }
    }

    fun applyRosaHedge(event: LivingIncomingDamageEvent) {
        val entity = event.entity
        val effect = entity.getEffect(ModMobEffects.ROSA_HEDGE)
        if (effect != null && event.amount <= effect.realAmplifier * ManorsBountyConfig.rosaHedgeDamageImmunity.get()) {
            event.amount = 0F
        }
    }

    fun applyMomentaryMeteor(event: LivingIncomingDamageEvent) {
        val entity = event.source.entity
        if (entity !is LivingEntity) return
        val effect = entity.getEffect(ModMobEffects.MOMENTARY_METEOR)
        if (effect != null) {
            letChanced(effect.realAmplifier * ManorsBountyConfig.momentaryMeteorChance.get()) {
                event.amount = min(100F, event.amount * 5)
            }
        }
    }

    fun applyBurstingBerry(event: CriticalHitEvent) {
        val entity = event.entity
        val effect = entity.getEffect(ModMobEffects.BURSTING_BERRY)
        if (event.isCriticalHit && effect != null) {
            event.damageMultiplier *= ManorsBountyConfig.burstingBerryCriticalHitDamageExtraMultiplier.get()
                .toFloat() * effect.realAmplifier
        }
    }

    fun applyRutinLemonene(event: LivingExperienceDropEvent) {
        val player = event.attackingPlayer
        val effect = player?.getEffect(ModMobEffects.RUTIN_LEMONENE)
        if (effect != null) {
            val exp = event.droppedExperience.toDouble()
            val extraExpDropped =
                exp * ManorsBountyConfig.rutinLemoneneExperienceMultiplier.get() * (1 + effect.realAmplifier.toDouble() / ManorsBountyConfig.rutinLemoneneAmplifierMultiplier.get())
            val extraExpPlayer = exp * min(
                player.experienceLevel, 60
            ) * ManorsBountyConfig.rutinLemonenePlayerExperienceLevelMultiplier.get()
            event.droppedExperience += extraExpDropped.toInt() + extraExpPlayer.toInt()
        }
    }

}