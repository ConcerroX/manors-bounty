package com.djinfinite.manors_bounty.util

import kotlin.random.Random

internal fun chance(chance: Double): Boolean {
    return Random.nextDouble() < chance
}

internal fun chance(chance: Float): Boolean {
    return Random.nextFloat() < chance
}

internal fun <R : Any?> letChanced(chance: Float, action: () -> R): R? {
    return if (Random.nextFloat() < chance) action() else null
}

internal fun <R : Any?> valueChancedOrNull(chance: Float, value: R?, default: R?): R? {
    return if (Random.nextFloat() < chance) value else default
}

internal fun <R : Any> valueChanced(chance: Float, value: R, default: R): R {
    return if (Random.nextFloat() < chance) value else default
}