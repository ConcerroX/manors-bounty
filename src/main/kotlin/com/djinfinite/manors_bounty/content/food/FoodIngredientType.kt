package com.djinfinite.manors_bounty.content.food

import net.minecraft.util.StringRepresentable

enum class FoodIngredientType(val id: String) : StringRepresentable {
    PINEAPPLE("pineapple"),
    DURIAN("durian");

    val items = mutableListOf<FoodItem>()

    override fun getSerializedName() = id

    companion object {
        val CODEC: StringRepresentable.StringRepresentableCodec<FoodIngredientType> =
            StringRepresentable.fromEnum { entries.toTypedArray() }
    }
}