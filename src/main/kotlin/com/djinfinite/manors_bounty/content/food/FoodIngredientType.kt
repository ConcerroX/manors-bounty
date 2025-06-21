package com.djinfinite.manors_bounty.content.food

import net.minecraft.core.Holder
import net.minecraft.util.StringRepresentable
import net.neoforged.neoforge.registries.DeferredItem

enum class FoodIngredientType(val id: String) : StringRepresentable {
    PINEAPPLE("pineapple");

    val items = mutableListOf<FoodItem>()

    override fun getSerializedName() = id

    companion object {
        val CODEC: StringRepresentable.StringRepresentableCodec<FoodIngredientType> =
            StringRepresentable.fromEnum { entries.toTypedArray() }
    }
}