package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.fryer.FryerStartOrStopPayload
import com.djinfinite.manors_bounty.content.fryer.FryerUpdateProgressPayload
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachinePayload
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.neoforged.neoforge.network.registration.HandlerThread

@EventBusSubscriber(modid = ManorsBounty.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object ModPayloads {

    @SubscribeEvent
    fun register(event: RegisterPayloadHandlersEvent) {
        val registrar = event.registrar("1").executesOn(HandlerThread.NETWORK)
        registrar.playToServer(
            IceCreamMachinePayload.TYPE,
            IceCreamMachinePayload.STREAM_CODEC,
            IceCreamMachinePayload::handleOnServer,
        )
        registrar.playToServer(
            FryerStartOrStopPayload.TYPE,
            FryerStartOrStopPayload.STREAM_CODEC,
            FryerStartOrStopPayload::handleOnServer,
        )
        registrar.playToClient(
            FryerUpdateProgressPayload.TYPE,
            FryerUpdateProgressPayload.STREAM_CODEC,
            FryerUpdateProgressPayload::handleOnClient
        )
    }

}