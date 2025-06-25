package com.djinfinite.manors_bounty.content.food

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.ItemLike

data class CooldownAttachmentData(
    val ingredients: Map<FoodIngredientType, Int>, val items: Map<ResourceLocation, Int>
) {

    companion object {
        val CODEC: Codec<CooldownAttachmentData> = RecordCodecBuilder.create {
            it.group(
                Codec.unboundedMap(FoodIngredientType.CODEC, Codec.INT).fieldOf("ingredients")
                    .forGetter(CooldownAttachmentData::ingredients),
                Codec.unboundedMap(ResourceLocation.CODEC, Codec.INT).fieldOf("items")
                    .forGetter(CooldownAttachmentData::items)
            ).apply(it, ::CooldownAttachmentData)
        }
    }

    fun containsItem(item: ItemLike): Boolean {
        return false
    }

}