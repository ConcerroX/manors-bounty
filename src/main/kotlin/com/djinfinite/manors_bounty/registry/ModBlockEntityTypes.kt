package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.fryer.FryerBlockEntity
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineBlockEntity
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

typealias DeferredBEType<T> = DeferredHolder<BlockEntityType<*>, BlockEntityType<T>>

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object ModBlockEntityTypes {

    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> = DeferredRegister.create(
        Registries.BLOCK_ENTITY_TYPE, ManorsBounty.ID
    )

    val ICE_CREAM_MACHINE: DeferredBEType<IceCreamMachineBlockEntity> = BLOCK_ENTITY_TYPES.register(
        "ice_cream_machine", Supplier {
            BlockEntityType.Builder.of(::IceCreamMachineBlockEntity, ModBlocks.ICE_CREAM_MACHINE.get()).build(null)
        })

    val FRYER: DeferredBEType<FryerBlockEntity> = BLOCK_ENTITY_TYPES.register(
        "fryer", Supplier {
            BlockEntityType.Builder.of(::FryerBlockEntity, ModBlocks.FRYER.get()).build(null)
        })
}