package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.res
import net.minecraft.core.registries.Registries
import net.minecraft.sounds.SoundEvent
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModSoundEvents {

    val SOUND_EVENTS: DeferredRegister<SoundEvent> = DeferredRegister.create(Registries.SOUND_EVENT, ManorsBounty.ID)

    val FRYING: DeferredHolder<SoundEvent, SoundEvent> =
        SOUND_EVENTS.register("frying", Supplier { SoundEvent.createVariableRangeEvent(res("frying")) })

}