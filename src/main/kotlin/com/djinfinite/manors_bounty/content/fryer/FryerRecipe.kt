package com.djinfinite.manors_bounty.content.fryer

import com.djinfinite.manors_bounty.registry.ModRecipeSerializers
import com.djinfinite.manors_bounty.registry.ModRecipeTypes
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.*
import net.minecraft.world.level.Level

data class FryerRecipe(
    val ingredient: Ingredient, val result: ItemStack, val isLongTime: Boolean
) : Recipe<FryerRecipe.Input> {

    companion object {
        private val CODEC: MapCodec<FryerRecipe> = RecordCodecBuilder.mapCodec {
            it.group(
                Ingredient.CODEC.fieldOf("ingredients").forGetter(FryerRecipe::ingredient),
                ItemStack.CODEC.fieldOf("result").forGetter(FryerRecipe::result),
                Codec.BOOL.fieldOf("is_long_time").forGetter(FryerRecipe::isLongTime),
            ).apply(it, ::FryerRecipe)
        }
        private val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, FryerRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC,
            FryerRecipe::ingredient,
            ItemStack.STREAM_CODEC,
            FryerRecipe::result,
            ByteBufCodecs.BOOL,
            FryerRecipe::isLongTime,
            ::FryerRecipe
        )
    }

    override fun matches(input: Input, level: Level) = ingredient.test(input.stack) && input.isLongTime == isLongTime
    override fun canCraftInDimensions(width: Int, height: Int) = true
    override fun assemble(input: Input, registries: HolderLookup.Provider): ItemStack = result.copy()
    override fun getResultItem(registries: HolderLookup.Provider) = result
    override fun getSerializer(): Serializer = ModRecipeSerializers.FRYER.get()
    override fun getType(): RecipeType<*> = ModRecipeTypes.FRYER.get()

    data class Input(val stack: ItemStack, val isLongTime: Boolean) : RecipeInput {
        override fun getItem(index: Int) = stack
        override fun size() = 1
    }

    class Serializer : RecipeSerializer<FryerRecipe> {
        override fun codec() = CODEC
        override fun streamCodec() = STREAM_CODEC
    }

}