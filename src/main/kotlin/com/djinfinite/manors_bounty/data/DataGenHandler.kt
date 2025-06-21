package com.djinfinite.manors_bounty.data

import com.djinfinite.manors_bounty.ManorsBounty
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
        generator.addProvider(
            event.includeServer(), ModRecipeProvider(output, lookupProvider)
        )
    }

}