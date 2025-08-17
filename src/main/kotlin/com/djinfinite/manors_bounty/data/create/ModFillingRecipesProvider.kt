package com.djinfinite.manors_bounty.data.create

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.registry.ModFluids
import com.djinfinite.manors_bounty.registry.ModItems
import com.simibubi.create.api.data.recipe.FillingRecipeGen
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.minecraft.world.level.material.Fluid
import java.util.concurrent.CompletableFuture

class ModFillingRecipesProvider(
    output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>
) : FillingRecipeGen(output, registries, ManorsBounty.MOD_ID) {

    init {
        fillJuice(ModItems.PINEAPPLE_JUICE, ModFluids.PINEAPPLE_JUICE)
    }

    private fun fillJuice(result: Holder<Item>, fluid: Holder<Fluid>) {
        create(result.unwrapKey().get().location().path) {
            it.require(fluid.value(), 250).require(ModItems.DEFORMABLE_GLASS_BOTTLE).output(result.value())
        }
    }

}