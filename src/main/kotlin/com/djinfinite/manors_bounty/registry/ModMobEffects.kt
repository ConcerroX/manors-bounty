package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.mobeffect.SimpleMobEffect
import com.djinfinite.manors_bounty.res
import net.minecraft.core.registries.Registries
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.neoforged.neoforge.common.NeoForgeMod
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModMobEffects {

    val MOB_EFFECTS: DeferredRegister<MobEffect> = DeferredRegister.create(Registries.MOB_EFFECT, ManorsBounty.ID)

    val SUMMER_HEATWAVE: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("summer_heatwave", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, 3402751).apply {
            addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                res("effect.summer_heatwave.speed"),
                0.2,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            )
            addAttributeModifier(
                NeoForgeMod.SWIM_SPEED,
                res("effect.summer_heatwave.swim_speed"),
                0.04,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            )
        }
    })

    val HACKED_THORNS: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("hacked_thorns", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -3818659)
    })

}