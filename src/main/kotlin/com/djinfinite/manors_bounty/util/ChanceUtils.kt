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

internal fun <R : Any?> letChanced(chance: Double, action: () -> R): R? {
    return if (Random.nextDouble() < chance) action() else null
}

internal fun <R : Any?> letChanced(chance: Float, action: () -> R, default: () -> R): R? {
    return if (Random.nextFloat() < chance) action() else default()
}

internal fun <R : Any?> letChanced(chance: Double, success: () -> R, failure: () -> R): R? {
    return if (Random.nextDouble() < chance) success() else failure()
}

internal fun <R : Any?> valueChancedOrNull(chance: Float, value: R?, default: R? = null): R? {
    return if (Random.nextFloat() < chance) value else default
}

internal fun <R : Any?> valueChancedOrNull(chance: Double, value: R?, default: R? = null): R? {
    return if (Random.nextDouble() < chance) value else default
}

internal fun <R : Any> valueChanced(chance: Float, success: R, failure: R): R {
    return if (Random.nextFloat() < chance) success else failure
}

internal fun <R : Any> valueChanced(chance: Double, success: R, failure: R): R {
    return if (Random.nextDouble() < chance) success else failure
}