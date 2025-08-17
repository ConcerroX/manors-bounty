package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.mobeffect.CherryBlossomsWeepingMobEffect
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

    val MOB_EFFECTS: DeferredRegister<MobEffect> = DeferredRegister.create(Registries.MOB_EFFECT, ManorsBounty.MOD_ID)

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
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -3818659) // See MobEffectManager.applyHackedThornsEffect
    })
    val BERRY_BLOOD: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("berry_blood", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -7326925)
    })
    val EFFECT_HAUNTING: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("effect_haunting", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -14876706)
    })
    val HYPOTHERMIA: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("hypothermia", Supplier {
        SimpleMobEffect(MobEffectCategory.HARMFUL, -16741406)
    })
    val TRANSMIT: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("transmit", Supplier {
        SimpleMobEffect(MobEffectCategory.NEUTRAL, -1)
    })
    val WARM_SHIELD: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("warm_shield", Supplier {
        SimpleMobEffect(MobEffectCategory.HARMFUL, -18627)
    })
    val LAVENDER_MOOD: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("lavender_mood", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -4887082)
    })
    val ROSA_HEDGE: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("rosa_hedge", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -26215)
    })
    val RUTIN_LEMONENE: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("rutin_lemonene", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -13261)
    })
    val BURSTING_BERRY: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("bursting_berry", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -39271)
    })
    val TOUGH_AS_NUT: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("tough_as_nut", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -13261)
    })
    val CHERRY_BLOSSOMS_WEEPING: DeferredHolder<MobEffect, CherryBlossomsWeepingMobEffect> = MOB_EFFECTS.register(
        "cherry_blossoms_weeping",
        Supplier { CherryBlossomsWeepingMobEffect(MobEffectCategory.BENEFICIAL, -32367) },
    )
    val MOMENTARY_METEOR: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register(
        "momentary_meteor",
        Supplier { SimpleMobEffect(MobEffectCategory.BENEFICIAL, -2816) },
    )
    val ORIGINAL_EVOLUTION: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register(
        "original_evolution",
        Supplier { SimpleMobEffect(MobEffectCategory.BENEFICIAL, -10592990) },
    )
    val ORIGINAL_EVOLUTION_METAMORPHOSIS: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register(
        "original_evolution_metamorphosis",
        Supplier { SimpleMobEffect(MobEffectCategory.BENEFICIAL, -1048748) },
    )
    val MELON_GRAVITY: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("melon_gravity", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -7149951)
    })
    val SEA_TOUCH: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("sea_touch", Supplier {
        SimpleMobEffect(MobEffectCategory.BENEFICIAL, -14572560)
    })
    val KIWING_WHEREABOUTS: DeferredHolder<MobEffect, SimpleMobEffect> =
        MOB_EFFECTS.register("kiwing_whereabouts", Supplier {
            SimpleMobEffect(MobEffectCategory.BENEFICIAL, -7274702)
        })
    val LURKING_DANGER: DeferredHolder<MobEffect, SimpleMobEffect> = MOB_EFFECTS.register("lurking_danger", Supplier {
        SimpleMobEffect(MobEffectCategory.HARMFUL, -13679602)
    })

}