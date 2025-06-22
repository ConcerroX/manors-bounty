package com.djinfinite.manors_bounty.content.icecream

import com.djinfinite.manors_bounty.registry.ModRecipeSerializers
import com.djinfinite.manors_bounty.registry.ModRecipeTypes
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level
import net.neoforged.neoforge.fluids.capability.IFluidHandler
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.wrapper.RecipeWrapper


data class IceCreamMachineRecipe(
    val ingredients: List<Ingredient>, val fluidIngredient: SizedFluidIngredient, val result: ItemStack
) : Recipe<IceCreamMachineRecipe.Input> {

    override fun matches(input: Input, level: Level): Boolean {
        return ingredients[0].test(input.getItem(0)) && ingredients[1].test(input.getItem(1)) && ingredients[2].test(
            input.getItem(2)) && fluidIngredient.test(input.tank.getFluidInTank(0))
    }

    override fun canCraftInDimensions(width: Int, height: Int) = true
    override fun assemble(input: Input, registries: HolderLookup.Provider): ItemStack = result.copy()
    override fun getResultItem(registries: HolderLookup.Provider): ItemStack = result
    override fun getSerializer(): Serializer = ModRecipeSerializers.ICE_CREAM_MACHINE.get()
    override fun getType(): RecipeType<*> = ModRecipeTypes.ICE_CREAM_MACHINE.get()

    data class Input(val inv: IItemHandler, val tank: IFluidHandler) : RecipeWrapper(inv) {
        override fun size() = 3
    }

    class Serializer : RecipeSerializer<IceCreamMachineRecipe> {
        companion object {
            private val CODEC: MapCodec<IceCreamMachineRecipe> = RecordCodecBuilder.mapCodec { inst ->
                inst.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(IceCreamMachineRecipe::ingredients),
                    SizedFluidIngredient.FLAT_CODEC.fieldOf("fluid_ingredient")
                        .forGetter(IceCreamMachineRecipe::fluidIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(IceCreamMachineRecipe::result))
                    .apply(inst, ::IceCreamMachineRecipe)
            }
            private val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, IceCreamMachineRecipe> = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(3)), IceCreamMachineRecipe::ingredients,
                SizedFluidIngredient.STREAM_CODEC, IceCreamMachineRecipe::fluidIngredient, ItemStack.STREAM_CODEC,
                IceCreamMachineRecipe::result, ::IceCreamMachineRecipe)
        }

        override fun codec() = CODEC
        override fun streamCodec() = STREAM_CODEC
    }

}