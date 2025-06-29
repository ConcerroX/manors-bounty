package com.djinfinite.manors_bounty.content.food

import com.djinfinite.manors_bounty.util.inWholeTicks
import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ByIdMap
import java.util.function.IntFunction
import kotlin.time.Duration.Companion.seconds

enum class FoodType(
    val applyChance: Float,
    val upgradeChance: Float,
    val extensionChance: Float,
    val cooldownTime: Int,
    val applyTime: Int,
    val extensionTime: Int,
    val maxAmplifier: Int,
    val sharedCooldownTime: Boolean,
) {
    A(
        applyChance = 0F,
        upgradeChance = 1F,
        extensionChance = 0F,
        cooldownTime = 720.seconds.inWholeTicks,
        applyTime = 0,
        extensionTime = 0,
        maxAmplifier = 3,
        sharedCooldownTime = true,
    ),
    B(
        applyChance = 0.3F,
        upgradeChance = 0.3F,
        extensionChance = 0.7F,
        cooldownTime = 300.seconds.inWholeTicks,
        applyTime = 720.seconds.inWholeTicks,
        extensionTime = 360.seconds.inWholeTicks,
        maxAmplifier = 5,
        sharedCooldownTime = false,
    ),
    C(
        applyChance = 0.9F,
        upgradeChance = 0.15F,
        extensionChance = 0F,
        cooldownTime = 480.seconds.inWholeTicks,
        applyTime = 480.seconds.inWholeTicks,
        extensionTime = 0,
        maxAmplifier = 7,
        sharedCooldownTime = false,
    );

    companion object {
        private val BY_ID: IntFunction<FoodType> = ByIdMap.continuous(
            FoodType::ordinal, FoodType.entries.toTypedArray(), ByIdMap.OutOfBoundsStrategy.ZERO
        )
        val STREAM_CODEC: StreamCodec<ByteBuf, FoodType> = ByteBufCodecs.idMapper(FoodType.BY_ID, FoodType::ordinal)
    }
}