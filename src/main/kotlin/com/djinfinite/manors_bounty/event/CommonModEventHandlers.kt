package com.djinfinite.manors_bounty.event

import com.djinfinite.manors_bounty.ManorsBounty
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent

@EventBusSubscriber(modid = ManorsBounty.ID, bus = EventBusSubscriber.Bus.MOD)
object CommonModEventHandlers {

    @SubscribeEvent
    fun modifyDefaultComponents(event: ModifyDefaultComponentsEvent) {

    }

}