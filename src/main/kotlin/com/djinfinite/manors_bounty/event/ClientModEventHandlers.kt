package com.djinfinite.manors_bounty.event

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineScreen
import com.djinfinite.manors_bounty.registry.ModFluidTypes
import com.djinfinite.manors_bounty.registry.ModMenuTypes
import com.djinfinite.manors_bounty.res
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent


@EventBusSubscriber(modid = ManorsBounty.ID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object ClientModEventHandlers {

    @SubscribeEvent
    fun onRegisterMenuScreens(event: RegisterMenuScreensEvent) {
        event.register(ModMenuTypes.ICE_CREAM_MACHINE.get(), ::IceCreamMachineScreen)
    }

    @SubscribeEvent
    fun registerClientExtensions(event: RegisterClientExtensionsEvent) {
        event.registerFluidType(object : IClientFluidTypeExtensions {
            override fun getStillTexture() = res("block/pineapple_juice_still")
            override fun getFlowingTexture() = res("block/pineapple_juice_flow")
        }, ModFluidTypes.PINEAPPLE_JUICE)
    }

}