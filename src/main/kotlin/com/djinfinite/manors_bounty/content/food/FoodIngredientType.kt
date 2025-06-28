package com.djinfinite.manors_bounty.content.food

import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.item.Item
import java.util.function.IntFunction


enum class FoodIngredientType(val id: String) : StringRepresentable {
    PINEAPPLE("pineapple"), DURIAN("durian"), SWEET_BERRY("sweet_berry"), CHORUS_FRUIT("chorus_fruit");

    val items = mutableListOf<Item>()

    override fun getSerializedName() = id

    companion object {
        private val BY_ID: IntFunction<FoodIngredientType> = ByIdMap.continuous(
            FoodIngredientType::ordinal, entries.toTypedArray(), ByIdMap.OutOfBoundsStrategy.ZERO
        )
        val CODEC: StringRepresentable.StringRepresentableCodec<FoodIngredientType> =
            StringRepresentable.fromEnum { entries.toTypedArray() }
        val STREAM_CODEC: StreamCodec<ByteBuf, FoodIngredientType> =
            ByteBufCodecs.idMapper(FoodIngredientType.BY_ID, FoodIngredientType::ordinal)
    }
}