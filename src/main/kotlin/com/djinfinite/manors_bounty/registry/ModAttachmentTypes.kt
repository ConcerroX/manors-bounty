package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.food.CooldownAttachmentData
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.function.Supplier


object ModAttachmentTypes {

    val ATTACHMENT_TYPES: DeferredRegister<AttachmentType<*>> = DeferredRegister.create(
        NeoForgeRegistries.Keys.ATTACHMENT_TYPES, ManorsBounty.ID
    )

    val COOLDOWN: DeferredHolder<AttachmentType<*>, AttachmentType<CooldownAttachmentData>> = ATTACHMENT_TYPES.register(
        "cooldown", Supplier {
            AttachmentType.builder(Supplier { CooldownAttachmentData(mapOf(), mapOf()) })
                .serialize(CooldownAttachmentData.CODEC).copyOnDeath().build()
        })

}