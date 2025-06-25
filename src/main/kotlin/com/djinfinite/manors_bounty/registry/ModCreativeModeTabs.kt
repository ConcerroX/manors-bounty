package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModCreativeModeTabs {

    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(
        Registries.CREATIVE_MODE_TAB, ManorsBounty.ID
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
        listOf<ItemLike>(
            // Misc
            ModItems.PEARL_ROCK,
            ModItems.SNOW_DYE,
            ModItems.CHORUS_CHROME_CUBE,
            ModItems.CREATIVITY_MECHANISM,

            // Liquids
            ModItems.OLIVE_OIL_BUCKET,
            // ModItems.HOT_SPRING_BUCKET,
            ModItems.CAKE_LIQUID_BUCKET,

            // Machines
            ModItems.ICE_CREAM_MACHINE,

            // Cakes
            ModItems.VANILLA_CAKE_SLICE,
            ModItems.CARAMEL_CHOCOLATE_CAKE,
            ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE,
            ModItems.SWEET_BERRY_CAKE,
            ModItems.SWEEP_BERRY_CAKE_SLICE,
            ModItems.CHORUS_FLOWER_JELLY_CAKE,
            ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE,
            ModItems.NETHER_WART_SOUL_CAKE,
            ModItems.NETHER_WART_SOUL_CAKE_SLICE,

            // Bowl foods
            ModItems.MARBLE_BOWL,
            ModItems.BORSCHT,
            ModItems.CREAM_WITH_MUSHROOM_SOUP,
            ModItems.MISO_SOUP,
            ModItems.PRAWN_AND_CORN_SOUP,
            ModItems.COSMIC_DRAGON_SALAD,
            ModItems.ROSA_SALAD,
            ModItems.SEA_PEARL_SALAD,
            ModItems.MELON_SALAD,

            ModItems.COFFEE,
            ModItems.DEFORMED_GLASS_BOTTLE,
            ModItems.MELON_SODA,
            ModItems.BUBBLE_TEA,
            ModItems.GRIMACE_SHAKE,
            ModItems.ORANGE_JUICE,
            ModItems.LEMONADE,
            ModItems.APPLE_JUICE,
            ModItems.PEAR_WITH_ROCK_SUGAR,
            ModItems.BELLINI_BASE,
            ModItems.STRAWBERRY_SHAKE,
            ModItems.MANGO_JUICE,
            ModItems.PINEAPPLE_JUICE,
            ModItems.WATERMELON_JUICE,
            ModItems.MARTINI,
            ModItems.CHORUS_FRUIT_JUICE,
            ModItems.FRUIT_PUNCH,
            ModItems.BLUEBERRY_YOGURT,
            ModItems.MANGO_PUDDING,
            ModItems.RISALAMANDE,
            ModItems.ETON_MESS,
            ModItems.MARMALADE,
            ModItems.BOXED_CHOCOLATE_MILK,
            ModItems.BOXED_LEMON_TEA,
            ModItems.BOTTLED_OLIVE_OIL,
            ModItems.LAVENDER_ESSENTIAL_OIL,

            ModItems.ICE_CREAM_CONE,
            ModItems.VANILLA_ICE_CREAM,
            ModItems.CHOCOLATE_ICE_CREAM,
            ModItems.BLUEBERRY_ICE_CREAM,
            ModItems.CHERRIES_ICE_CREAM,
            ModItems.STARFRUIT_ICE_CREAM,
            ModItems.JALAPENO_ICE_CREAM,
            ModItems.VANILLA_CHOCOLATE_ICE_CREAM,
            ModItems.BLUEBERRY_CHERRIES_ICE_CREAM,
            ModItems.STARFRUIT_JALAPENO_ICE_CREAM,

            ModItems.VANILLA_ICE_CREAM_FLUID_BUCKET,
            ModItems.CHOCOLATE_ICE_CREAM_FLUID_BUCKET,
            ModItems.BLUEBERRY_ICE_CREAM_FLUID_BUCKET,
            ModItems.CHERRIES_ICE_CREAM_FLUID_BUCKET,
            ModItems.STARFRUIT_ICE_CREAM_FLUID_BUCKET,
            ModItems.JALAPENO_ICE_CREAM_FLUID_BUCKET,

            ModItems.POWSICLE_PALETERIA,
            ModItems.LUAU_PALETERIA,
            ModItems.WATERMELON_PALETERIA,
            ModItems.PINK_SNOWMAN_PALETERIA,
            ModItems.HEART_CRUSH_PALETERIA,
            ModItems.PINEAPPLE_PALETERIA,
            ModItems.HAMIMELON_PALETERIA,

            ModItems.CHERRIES_PIE,
            ModItems.CHERRIES_PIE_SLICE,
            ModItems.TRUFFLE_PIE,
            ModItems.TRUFFLE_PIE_SLICE,
            ModItems.PANCAKE,
            ModItems.DURIAN_MILE_CREPE_CAKE,
            ModItems.DURIAN_MILE_CREPE_CAKE_SLICE,

            ModItems.MUFFIN,
            ModItems.BLUEBERRY_MUFFIN,
            ModItems.CRANBERRY_MUFFIN,
            ModItems.CHOCOLATE_MUFFIN,
            ModItems.ORANGE_MUFFIN,

            ModItems.CUPCAKE_QAQ,
            ModItems.TACO,
            ModItems.FRIED_CHICKEN,
            ModItems.FRIED_DRUMSTICK,
            ModItems.FRIED_PORKCHOP,
            ModItems.FRIED_STEAK,
            ModItems.FRIED_FISH,
            ModItems.FRIED_TENPURA,

            ModItems.DONUT,
            ModItems.CHOCOLATE_DONUT,
            ModItems.STRAWBERRY_DONUT,
            ModItems.WHITE_CHOCOLATE_DONUT,
            ModItems.LEMON_DONUT,
            ModItems.SNOW_DONUT,

            ModItems.CRANBERRY_COOKIE,
            ModItems.BLUEBERRY_COOKIE,
            ModItems.LEMON_COOKIE,

            ModItems.HANAMI_DANGO,
            ModItems.SAKURA_MOCHI,
            ModItems.CHOCOLATE,

            ModItems.JALAPENO,
            ModItems.CORN,
            ModItems.CORN_SEED,
            ModItems.APPLE_SLICE,
            ModItems.PINEAPPLE,
            ModItems.PINEAPPLE_CROP,
            ModItems.PINEAPPLE_SLICE,
            ModItems.PINEAPPLE_PASTRIES,
            ModItems.PINEAPPLE_PIES,
            ModItems.DURIAN_SEED,
            ModItems.MUSANG_KING_DURIAN,
            ModItems.HALF_OF_MUSANG_KING_DURIAN,
            ModItems.MUSANG_KING_DURIAN_FLESH,
            ModItems.COOKED_DURIAN_FLESH,
            ModItems.DURIAN_CRISP,
            ModItems.HAMIMELON,
            ModItems.HAMIMELON_SLICE,
            ModItems.HAMIMELON_SEED,
            ModItems.STARFRUIT,
            ModItems.STARFRUIT_SLICE,
            ModItems.MANGO,
            ModItems.MANGO_SLICE,
            ModItems.KIWIFRUIT,
            ModItems.HALF_OF_KIWIFRUIT,
            ModItems.KIWIFRUIT_SLICE,
            ModItems.AVOCADO,
            ModItems.HALF_OF_AVOCADO,
            ModItems.AVOCADO_SLICE,
            ModItems.ORANGE,
            ModItems.ORANGE_SEGMENT,
            ModItems.LEMON,
            ModItems.LEMON_SLICE,
            ModItems.PEACH,
            ModItems.PEACH_SLICE,
            ModItems.PEAR,
            ModItems.PEAR_SLICE,
            ModItems.DRAGON_FRUIT,
            ModItems.DRAGON_FRUIT_SLICE,
            ModItems.DRAGON_FRUIT_CACTUS,
            ModItems.DRAGON_FRUIT_CACTUS_FLOWER,
            ModItems.STRAWBERRY,
            ModItems.PINEBERRY,
            ModItems.BLUEBERRY,
            ModItems.CRANBERRY,
            ModItems.CHERRIES,
            ModItems.OLIVE_FRUIT,

            ModItems.ALFALFA,
            ModItems.ALFALFA_SEED,
            ModItems.ACORN,
            ModItems.FILBERT,
            ModItems.TRUFFLE,
            ModItems.GARLIC,
            ModItems.BUTTON_MUSHROOM,
            ModItems.COOKED_BUTTON_MUSHROOM,
            ModItems.WOOD_MUSHROOM,
            ModItems.RAW_PRAWN,
            ModItems.COOKED_PRAWN,

            // Hats
            ModItems.FLOWER_RING_HELMET,
            ModItems.WITCH_HAT_HELMET,
            ModItems.CHEF_HAT_HELMET,
            ModItems.CHRISTMAS_HAT_HELMET,
            ModItems.MINER_HAT_HELMET,

            // Christmas
            ModItems.CHRISTMAS_MUFFLER_CHESTPLATE,
            ModItems.CHRISTMAS_WREATHS,
            ModItems.CHRISTMAS_SOCK,

            // Gifts
            ModItems.GIFT_SHORT_RED,
            ModItems.GIFT_TALL_RED,
            ModItems.GIFT_SHORT_GREEN,
            ModItems.GIFT_TALL_GREEN,
            ModItems.GIFT_SHORT_BLUE,
            ModItems.GIFT_TALL_BLUE,
            ModItems.GIFT_SHORT_PINK,
            ModItems.GIFT_TALL_PINK,
            ModItems.GIFT_SHORT_LUCKY,
            ModItems.GIFT_TALL_LUCKY,

            // Leaves
            ModItems.SCOTS_PINE_LEAVES,
            ModItems.ALPINE_TREE_LEAVES,
            ModItems.CHERRIES_TREE_LEAVES,
            ModItems.STARFRUIT_TREE_LEAVES,
            ModItems.OLIVE_TREE_LEAVES,
            ModItems.ORANGE_LEAVES,
            ModItems.PEACH_LEAVES,
            ModItems.PEAR_LEAVES,
            ModItems.APPLE_LEAVES,
            ModItems.MANGO_TREE_LEAVES,
            ModItems.KIWIFRUIT_TREE_LEAVES,
            ModItems.AVOCADO_TREE_LEAVES,

            // Saplings
            ModItems.SCOTS_PINE_SAPLING,
            // We still don't have alpine sapling
            ModItems.CHERRIES_SEED,
            ModItems.STARFRUIT_SEED,
            ModItems.OLIVE_FRUIT_SEED,
            ModItems.ORANGE_SEED,
            ModItems.PEACH_SEED,
            ModItems.PEAR_SEED,
            ModItems.APPLE_SEED,
            ModItems.MANGO_SEED,
            ModItems.KIWIFRUIT_SEED,
            ModItems.AVOCADO_SEED,

            // Ores
            ModItems.PEARL_ROCK_ORE,
            ModItems.DEEPSLATE_PEARL_ROCK_ORE,
        )
    }

    private val BUILDING_TAB_ITEMS by lazy {
        listOf(
            // Woods
            ModWoodTypes.SCOTS_PINE,
            ModWoodTypes.ALPINE_TREE,
            ModWoodTypes.CHERRIES_TREE,
            ModWoodTypes.STARFRUIT_TREE,
            ModWoodTypes.OLIVE_TREE,
            ModWoodTypes.RUTACEAE_TREE,
            ModWoodTypes.ROSACEAE_TREE,
            ModWoodTypes.MANGO_TREE,
            ModWoodTypes.KIWIFRUIT_TREE,
            ModWoodTypes.AVOCADO_TREE,

            // Snow Color Blocks
            ModItems.SNOW_TERRACOTTA,
            ModItems.SNOW_GLAZED_TERRACOTTA,
            ModItems.SNOW_CONCRETE,
            ModItems.SNOW_CONCRETE_POWDER,
            ModItems.SNOW_GLASS,
            ModItems.SNOW_GLASS_PANE,
        )
    }

    private fun CreativeModeTab.Output.acceptNullable(item: ItemLike?) {
        if (item != null) {
            accept(item)
        }
    }

}