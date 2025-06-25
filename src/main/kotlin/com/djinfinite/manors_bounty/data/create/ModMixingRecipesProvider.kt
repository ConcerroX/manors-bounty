package com.djinfinite.manors_bounty.data.create

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.registry.ModFluids
import com.djinfinite.manors_bounty.registry.ModItems
import com.simibubi.create.api.data.recipe.MixingRecipeGen
import com.simibubi.create.content.processing.recipe.HeatCondition
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.material.Fluid
import java.util.concurrent.CompletableFuture

class ModMixingRecipesProvider(
    output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>
) : MixingRecipeGen(output, registries, ManorsBounty.ID) {

    init {
        mixPies(ModItems.PINEAPPLE_PIES, ModItems.PINEAPPLE_SLICE)
        mixJuice(ModFluids.PINEAPPLE_JUICE, ModItems.PINEAPPLE_SLICE)
    }

    private fun mixPies(result: Holder<Item>, ingredient: Holder<Item>) {
        create(result.unwrapKey().get().location().path) {
            it.require(ModFluids.PINEAPPLE_JUICE.get(), 25).require(ingredient.value()).requiresHeat(
                HeatCondition.HEATED
            ).output(result.value()) // TODO: olive oil
        }
    }

    private fun mixJuice(result: Holder<Fluid>, ingredient: Holder<Item>) {
        create(result.unwrapKey().get().location().path) {
            it.require(ingredient.value()).require(ingredient.value()).require(ingredient.value()).require(Items.SUGAR)
                .require(Items.SUGAR).output(result.value(), 250)
        }
    }

}