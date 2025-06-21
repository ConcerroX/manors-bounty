package com.djinfinite.manors_bounty.event

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.mobeffect.MobEffectManager
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent

@EventBusSubscriber(modid = ManorsBounty.ID, bus = EventBusSubscriber.Bus.GAME)
object CommonGameEventHandlers {

    @SubscribeEvent
    fun onLivingIncomingDamage(event: LivingIncomingDamageEvent) {
        MobEffectManager.applyHackedThornsEffect(event)
    }

}