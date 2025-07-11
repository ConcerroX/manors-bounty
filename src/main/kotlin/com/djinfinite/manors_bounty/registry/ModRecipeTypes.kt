package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.fryer.FryerRecipe
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineRecipe
import com.djinfinite.manors_bounty.res
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModRecipeTypes {

    val RECIPE_TYPES: DeferredRegister<RecipeType<*>> = DeferredRegister.create(Registries.RECIPE_TYPE, ManorsBounty.ID)

    val ICE_CREAM_MACHINE: DeferredHolder<RecipeType<*>, RecipeType<IceCreamMachineRecipe>> = RECIPE_TYPES.register(
        "ice_cream_machine", Supplier { RecipeType.simple(res("ice_cream_machine")) })
    val FRYER: DeferredHolder<RecipeType<*>, RecipeType<FryerRecipe>> = RECIPE_TYPES.register(
        "fryer", Supplier { RecipeType.simple(res("fryer")) })

}