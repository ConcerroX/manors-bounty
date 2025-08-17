package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.fryer.FryerRecipe
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineRecipe
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeSerializer
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModRecipeSerializers {

    val RECIPE_SERIALIZERS: DeferredRegister<RecipeSerializer<*>> = DeferredRegister.create(
        Registries.RECIPE_SERIALIZER, ManorsBounty.MOD_ID
    )

    val ICE_CREAM_MACHINE: DeferredHolder<RecipeSerializer<*>, IceCreamMachineRecipe.Serializer> =
        RECIPE_SERIALIZERS.register("ice_cream_machine", Supplier { IceCreamMachineRecipe.Serializer() })
    val FRYER: DeferredHolder<RecipeSerializer<*>, FryerRecipe.Serializer> = RECIPE_SERIALIZERS.register(
        "fryer", Supplier { FryerRecipe.Serializer() })

}