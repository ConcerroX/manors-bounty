package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModCreativeModeTabs {

    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(
        Registries.CREATIVE_MODE_TAB, ManorsBounty.MOD_ID
    )

    @Suppress("Unused")
    private val MAIN_TAB: DeferredHolder<CreativeModeTab, CreativeModeTab> =
        CREATIVE_MODE_TABS.register("main_tab", Supplier {
            CreativeModeTab.builder().title(Component.translatable("item_group.manors_bounty.manors_bounty"))
                .icon(ModItems.MAIN_ICON_ITEM::toStack)
                .displayItems { _, output -> MAIN_TAB_ITEMS.forEach { output.accept(it) } }.build()
        })

    @Suppress("Unused")
    private val BUILDING_TAB: DeferredHolder<CreativeModeTab, CreativeModeTab> = CREATIVE_MODE_TABS.register(
        "building_tab",
        Supplier {
            CreativeModeTab.builder().title(Component.translatable("item_group.manors_bounty.manors_bounty_building"))
                .icon(ModItems.BUILDING_ICON_ITEM::toStack).withTabsBefore(MAIN_TAB.key).displayItems { _, output ->
                    BUILDING_TAB_ITEMS.forEach { itemLike ->
                        if (itemLike is ModWoodTypes.WoodType) {
                            itemLike.values().forEach { output.acceptNullable(it) }
                        } else {
                            output.accept(itemLike)
                        }
                    }
                }.build()
        },
    )

    private val MAIN_TAB_ITEMS by lazy {
        ModItems.ITEMS.entries.map { it as DeferredItem }
    }

    private val BUILDING_TAB_ITEMS by lazy {
        listOf(
            // Woods
            ModWoodTypes.SCOTS_PINE,
            ModWoodTypes.ALPINE_TREE,
            ModWoodTypes.CHERRY_TREE,
            ModWoodTypes.STARFRUIT_TREE,
            ModWoodTypes.OLIVE_TREE,
            ModWoodTypes.RUTACEAE_TREE,
            ModWoodTypes.ROSACEAE_TREE,
            ModWoodTypes.MANGO_TREE,
            ModWoodTypes.KIWIFRUIT_TREE,
            ModWoodTypes.AVOCADO_TREE,

            // Snow Color Blocks
//            ModItems.SNOW_TERRACOTTA,
//            ModItems.SNOW_GLAZED_TERRACOTTA,
//            ModItems.SNOW_CONCRETE,
//            ModItems.SNOW_CONCRETE_POWDER,
//            ModItems.SNOW_GLASS,
//            ModItems.SNOW_GLASS_PANE,
        )
    }

    private fun CreativeModeTab.Output.acceptNullable(item: ItemLike?) {
        if (item != null) {
            accept(item)
        }
    }

}