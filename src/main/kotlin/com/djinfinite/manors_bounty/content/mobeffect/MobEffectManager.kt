package com.djinfinite.manors_bounty.content.mobeffect

import com.djinfinite.manors_bounty.registry.ModMobEffects
import com.djinfinite.manors_bounty.util.chance
import com.djinfinite.manors_bounty.util.inWholeTicks
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent
import kotlin.math.min
import kotlin.time.Duration.Companion.seconds

object MobEffectManager {

    private const val HACKED_THORNS_KNOCKBACK_CHANCE = 0.08
    private const val HACKED_THORNS_KNOCKBACK_AMOUNT = 0.35
    private val HACKED_THORNS_SLOWNESS_TIME = 0.8.seconds.inWholeTicks
    private val HACKED_THORNS_INVULNERABLE_TIME = 0.8.seconds.inWholeTicks

    fun applyHackedThornsEffect(event: LivingIncomingDamageEvent) {
        val entity = event.entity
        val source = event.source
        val causingEntity = source.entity
        val effect = entity.getEffect(ModMobEffects.HACKED_THORNS)
        val isProjectile = !source.isDirect
        if (effect != null && source.entity != null) {
            val amplifier = effect.amplifier + 1
            val force = HACKED_THORNS_KNOCKBACK_AMOUNT * amplifier
            if (isProjectile && chance(HACKED_THORNS_KNOCKBACK_CHANCE * amplifier)) {
                entity.invulnerableTime += HACKED_THORNS_INVULNERABLE_TIME
                causingEntity?.let {
                    it.deltaMovement = causingEntity.position().subtract(entity.position()).normalize().scale(force)
                }
            } else {
                if (causingEntity !is LivingEntity) return
                causingEntity.deltaMovement =
                    causingEntity.position().subtract(entity.position()).normalize().scale(force)
                causingEntity.addEffect(
                    MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN, HACKED_THORNS_SLOWNESS_TIME, min(effect.amplifier, 3), false, true
                    )
                )
            }
            entity.level().playSound(
                entity, entity.blockPosition(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, SoundSource.PLAYERS, 0.8F, 1.5F
            )
        }
    }

}