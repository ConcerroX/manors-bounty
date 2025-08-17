package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.fryer.FryerMenu
import com.djinfinite.manors_bounty.content.icecream.IceCreamMachineMenu
import net.minecraft.core.registries.Registries
import net.minecraft.world.inventory.MenuType
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModMenuTypes {

    val MENU_TYPES: DeferredRegister<MenuType<*>> = DeferredRegister.create(Registries.MENU, ManorsBounty.MOD_ID)

    val ICE_CREAM_MACHINE: DeferredHolder<MenuType<*>, MenuType<IceCreamMachineMenu>> = MENU_TYPES.register(
        "ice_cream_machine", Supplier { IMenuTypeExtension.create(::IceCreamMachineMenu) })
    val FRYER: DeferredHolder<MenuType<*>, MenuType<FryerMenu>> = MENU_TYPES.register(
        "fryer", Supplier { IMenuTypeExtension.create(::FryerMenu) })

}