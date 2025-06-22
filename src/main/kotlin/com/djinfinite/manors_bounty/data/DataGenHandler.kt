package com.djinfinite.manors_bounty.data

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.data.create.ModFillingRecipesProvider
import com.djinfinite.manors_bounty.data.create.ModMixingRecipesProvider
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ManorsBounty.ID)
object DataGenHandler {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput
        val lookupProvider = event.lookupProvider
        val existingFileHelper = event.existingFileHelper
        generator.addProvider(event.includeServer(), ModRecipesProvider(output, lookupProvider))
        generator.addProvider(event.includeServer(), ModFillingRecipesProvider(output, lookupProvider))
        generator.addProvider(event.includeServer(), ModMixingRecipesProvider(output, lookupProvider))
        generator.addProvider(event.includeServer(), ModBiomeTagsProvider(output, lookupProvider, existingFileHelper))
        generator.addProvider(event.includeServer(), ModBiomeModifiersProvider(output, lookupProvider))
    }

}