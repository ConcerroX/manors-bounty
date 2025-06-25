package com.djinfinite.manors_bounty

import net.neoforged.neoforge.common.ModConfigSpec

object ManorsBountyConfig {

    private val BUILDER = ModConfigSpec.Builder().apply {
        push("wth")
        fruitLeavesBearChance = defineInRange("fruitLeavesBearChance", 0.4, 0.0, 1.0)
        fruitLeavesBlossomRate = defineInRange("fruitLeavesBlossomRate", 0.3, 0.0, 1.0)
        fruitLeavesBearRate = defineInRange("fruitLeavesBearRate", 0.025, 0.0, 1.0)
        fruitLeavesDropRate = defineInRange("fruitLeavesDropRate", 0.025, -1.0, 1.0)
        pop()

        group("effects") {
            group("hackedThorns") {
                hackedThornsKnockbackChance = defineInRange("knockbackChance", 0.08, 0.0, 1.0)
                hackedThornsKnockbackAmount = defineInRange("knockbackAmount", 0.35, 0.0, 10.0)
                hackedThornsSlownessTime = defineInRange("slownessTime", 0.8, 0.0, 10.0)
                hackedThornsInvulnerableTime = defineInRange("invulnerableTime", 0.8, 0.0, 10.0)
            }
            burstingBerryCriticalHitDamageExtraMultiplier =
                defineInRange("burstingBerryCriticalHitDamageExtraMultiplier", 0.15, 0.0, 1.0)
            rosaHedgeDamageImmunity = defineInRange("rosaHedgeDamageImmunity", 0.5, 0.0, 10.0)
            group("rutinLemonene") {
                rutinLemoneneExperienceMultiplier = defineInRange("experienceMultiplier", 0.1, 0.0, 1.0)
                rutinLemoneneAmplifierMultiplier = defineInRange("amplifierMultiplier", 0.25, 0.0, 1.0)
                rutinLemonenePlayerExperienceLevelMultiplier =
                    defineInRange("playerExperienceLevelMultiplier", 0.0125, 0.0, 1.0)
            }
            cherryBlossomsWeepingKnockbackMultiplier =
                defineInRange("cherryBlossomsWeepingKnockbackMultiplier", 0.1, 0.0, 10.0)
            momentaryMeteorChance = defineInRange("momentaryMeteorChance", 0.01, 0.0, 1.0)
        }
    }
    val SPEC: ModConfigSpec = BUILDER.build()

    var fruitLeavesBearChance: ModConfigSpec.DoubleValue
    var fruitLeavesBlossomRate: ModConfigSpec.DoubleValue
    var fruitLeavesBearRate: ModConfigSpec.DoubleValue
    var fruitLeavesDropRate: ModConfigSpec.DoubleValue

    lateinit var hackedThornsKnockbackChance: ModConfigSpec.DoubleValue
    lateinit var hackedThornsKnockbackAmount: ModConfigSpec.DoubleValue
    lateinit var hackedThornsSlownessTime: ModConfigSpec.DoubleValue
    lateinit var hackedThornsInvulnerableTime: ModConfigSpec.DoubleValue

    lateinit var burstingBerryCriticalHitDamageExtraMultiplier: ModConfigSpec.DoubleValue
    lateinit var rosaHedgeDamageImmunity: ModConfigSpec.DoubleValue

    lateinit var rutinLemoneneExperienceMultiplier: ModConfigSpec.DoubleValue
    lateinit var rutinLemoneneAmplifierMultiplier: ModConfigSpec.DoubleValue
    lateinit var rutinLemonenePlayerExperienceLevelMultiplier: ModConfigSpec.DoubleValue

    lateinit var cherryBlossomsWeepingKnockbackMultiplier: ModConfigSpec.DoubleValue

    lateinit var momentaryMeteorChance: ModConfigSpec.DoubleValue

    private fun ModConfigSpec.Builder.group(name: String, block: ModConfigSpec.Builder.() -> Unit) =
        push(name).apply(block).pop()

}
