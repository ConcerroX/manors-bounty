package com.djinfinite.manors_bounty.event

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.fryer.FryerBlockEntityRenderer
import com.djinfinite.manors_bounty.content.fryer.FryerScreen
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineScreen
import com.djinfinite.manors_bounty.registry.ModBlockEntityTypes
import com.djinfinite.manors_bounty.registry.ModFluidTypes
import com.djinfinite.manors_bounty.registry.ModMenuTypes
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent

@EventBusSubscriber(modid = ManorsBounty.ID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object ClientModEventHandlers {

    @SubscribeEvent
    fun onRegisterMenuScreens(event: RegisterMenuScreensEvent) {
        event.register(ModMenuTypes.ICE_CREAM_MACHINE.get(), ::IceCreamMachineScreen)
        event.register(ModMenuTypes.FRYER.get(), ::FryerScreen)
    }

    @SubscribeEvent
    fun registerClientExtensions(event: RegisterClientExtensionsEvent) {
        ModFluidTypes.registerTextures(event)
    }

    @SubscribeEvent
    fun onRegisterEntityRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.FRYER.get(), ::FryerBlockEntityRenderer)
    }

}