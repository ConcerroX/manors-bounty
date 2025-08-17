package com.djinfinite.manors_bounty.event

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.mobeffect.MobEffectManager
import com.djinfinite.manors_bounty.registry.ModDataComponents
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent

@EventBusSubscriber(modid = ManorsBounty.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
object CommonGameEventHandlers {

    @SubscribeEvent
    fun onLivingIncomingDamage(event: LivingIncomingDamageEvent) {
        MobEffectManager.applyHackedThorns(event)
        MobEffectManager.applyRosaHedge(event)
        MobEffectManager.applyMomentaryMeteor(event)
    }

    @SubscribeEvent
    fun onCriticalHit(event: CriticalHitEvent) {
        MobEffectManager.applyBurstingBerry(event)
    }

    @SubscribeEvent
    fun onLivingExperienceDrop(event: LivingExperienceDropEvent) {
        MobEffectManager.applyRutinLemonene(event)
    }

    @SubscribeEvent
    fun onLivingEntityUseItem(event: LivingEntityUseItemEvent.Finish) {
        val itemStack = event.item
        if (itemStack.has(ModDataComponents.FRUIT_EFFECT)) {
            val fruitEffectData = itemStack.get(ModDataComponents.FRUIT_EFFECT)
            fruitEffectData?.consume(event.entity.level(), event.entity, itemStack)
        }
    }

}