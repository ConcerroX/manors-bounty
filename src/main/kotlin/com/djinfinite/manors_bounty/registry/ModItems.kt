package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.food.FoodItem
import com.djinfinite.manors_bounty.content.pineapple.PineappleFoodItems
import net.minecraft.core.Holder
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.BucketItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Function
import java.util.function.Supplier

object ModItems {

    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(ManorsBounty.ID)
    val FOOD_ITEM_HOLDERS = mutableListOf<DeferredItem<out FoodItem>>()

    val MAIN_ICON_ITEM: DeferredItem<Item> = ITEMS.registerSimpleItem("main_icon_item", Item.Properties())
    val SLOT_BAN_ITEM: DeferredItem<Item> = ITEMS.registerSimpleItem("slot_ban_item", Item.Properties())

    val PEARL_ROCK: DeferredItem<Item> = ITEMS.registerSimpleItem("pearl_rock", Item.Properties())
    val SNOW_DYE: DeferredItem<Item> = ITEMS.registerSimpleItem("snow_dye", Item.Properties())
    val CREATIVITY_MECHANISM: DeferredItem<Item> = ITEMS.registerSimpleItem("creativity_mechanism", Item.Properties())

    val OLIVE_OIL_BUCKET: DeferredItem<Item> = ITEMS.registerSimpleItem("olive_oil_bucket", Item.Properties())
    val HOT_SPRING_BUCKET: DeferredItem<Item> = ITEMS.registerSimpleItem("hot_spring_bucket", Item.Properties())
    val CAKE_LIQUID_BUCKET: DeferredItem<Item> = ITEMS.registerSimpleItem("cake_liquid_bucket", Item.Properties())

    val ICE_CREAM_MACHINE: DeferredItem<BlockItem> =
        ITEMS.registerSimpleBlockItem("ice_cream_machine", ModBlocks.ICE_CREAM_MACHINE)

    val VANILLA_CAKE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("vanilla_cake_slice", Item.Properties())
    val CARAMEL_CHOCOLATE_CAKE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("caramel_chocolate_cake", Item.Properties())
    val CARAMEL_CHOCOLATE_CAKE_SLICE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("caramel_chocolate_cake_slice", Item.Properties())
    val SWEET_BERRY_CAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("sweet_berry_cake", Item.Properties())
    val SWEEP_BERRY_CAKE_SLICE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("sweet_berry_cake_slice", Item.Properties())
    val CHORUS_FLOWER_JELLY_CAKE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("chorus_flower_jelly_cake", Item.Properties())
    val CHORUS_FLOWER_JELLY_CAKE_SLICE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("chorus_flower_jelly_cake_slice", Item.Properties())
    val NETHER_WART_SOUL_CAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("nether_wart_soul_cake", Item.Properties())
    val NETHER_WART_SOUL_CAKE_SLICE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("nether_wart_soul_cake_slice", Item.Properties())

    val MARBLE_BOWL: DeferredItem<Item> = ITEMS.registerSimpleItem("marble_bowl", Item.Properties())
    val BORSCHT: DeferredItem<Item> = ITEMS.registerSimpleItem("borscht", Item.Properties())
    val CREAM_WITH_MUSHROOM_SOUP: DeferredItem<Item> =
        ITEMS.registerSimpleItem("cream_with_mushroom_soup", Item.Properties())
    val MISO_SOUP: DeferredItem<Item> = ITEMS.registerSimpleItem("miso_soup", Item.Properties())
    val PRAWN_AND_CORN_SOUP: DeferredItem<Item> = ITEMS.registerSimpleItem("prawn_and_corn_soup", Item.Properties())
    val COSMIC_DRAGON_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("cosmic_dragon_salad", Item.Properties())
    val ROSA_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("rosa_salad", Item.Properties())
    val SEA_PEARL_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("sea_pearl_salad", Item.Properties())
    val MELON_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("melon_salad", Item.Properties())

    val COFFEE: DeferredItem<Item> = ITEMS.registerSimpleItem("coffee", Item.Properties())
    val DEFORMED_GLASS_BOTTLE: DeferredItem<Item> = ITEMS.registerSimpleItem("deformed_glass_bottle", Item.Properties())
    val MELON_SODA: DeferredItem<Item> = ITEMS.registerSimpleItem("melon_soda", Item.Properties())
    val BUBBLE_TEA: DeferredItem<Item> = ITEMS.registerSimpleItem("bubble_tea", Item.Properties())
    val GRIMACE_SHAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("grimace_shake", Item.Properties())
    val ORANGE_JUICE: DeferredItem<Item> = ITEMS.registerSimpleItem("orange_juice", Item.Properties())
    val LEMONADE: DeferredItem<Item> = ITEMS.registerSimpleItem("lemonade", Item.Properties())
    val APPLE_JUICE: DeferredItem<Item> = ITEMS.registerSimpleItem("apple_juice", Item.Properties())
    val PEAR_WITH_ROCK_SUGAR: DeferredItem<Item> = ITEMS.registerSimpleItem("pear_with_rock_sugar", Item.Properties())
    val BELLINI_BASE: DeferredItem<Item> = ITEMS.registerSimpleItem("bellini_base", Item.Properties())
    val STRAWBERRY_SHAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("strawberry_shake", Item.Properties())
    val MANGO_JUICE: DeferredItem<Item> = ITEMS.registerSimpleItem("mango_juice", Item.Properties())

    val WATERMELON_JUICE: DeferredItem<Item> = ITEMS.registerSimpleItem("watermelon_juice", Item.Properties())
    val MARTINI: DeferredItem<Item> = ITEMS.registerSimpleItem("martini", Item.Properties())
    val CHORUS_FRUIT_JUICE: DeferredItem<Item> = ITEMS.registerSimpleItem("chorus_fruit_juice", Item.Properties())
    val FRUIT_PUNCH: DeferredItem<Item> = ITEMS.registerSimpleItem("fruit_punch", Item.Properties())
    val BLUEBERRY_YOGURT: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberry_yogurt", Item.Properties())
    val MANGO_PUDDING: DeferredItem<Item> = ITEMS.registerSimpleItem("mango_pudding", Item.Properties())
    val RISALAMANDE: DeferredItem<Item> = ITEMS.registerSimpleItem("risalamande", Item.Properties())
    val ETON_MESS: DeferredItem<Item> = ITEMS.registerSimpleItem("eton_mess", Item.Properties())
    val MARMALADE: DeferredItem<Item> = ITEMS.registerSimpleItem("marmalade", Item.Properties())
    val BOXED_CHOCOLATE_MILK: DeferredItem<Item> = ITEMS.registerSimpleItem("boxed_chocolate_milk", Item.Properties())
    val BOXED_LEMON_TEA: DeferredItem<Item> = ITEMS.registerSimpleItem("boxed_lemon_tea", Item.Properties())
    val BOTTLED_OLIVE_OIL: DeferredItem<Item> = ITEMS.registerSimpleItem("bottled_olive_oil", Item.Properties())
    val LAVENDER_ESSENTIAL_OIL: DeferredItem<Item> =
        ITEMS.registerSimpleItem("lavender_essential_oil", Item.Properties())

    val CHORUS_CHROME_CUBE: DeferredItem<Item> = ITEMS.registerSimpleItem("chorus_chrome_cube", Item.Properties())

    val ICE_CREAM_CONE: DeferredItem<Item> = ITEMS.registerSimpleItem("ice_cream_cone", Item.Properties())
    val VANILLA_ICE_CREAM: DeferredItem<Item> = ITEMS.registerSimpleItem("vanilla_ice_cream", Item.Properties())
    val CHOCOLATE_ICE_CREAM: DeferredItem<Item> = ITEMS.registerSimpleItem("chocolate_ice_cream", Item.Properties())
    val BLUEBERRY_ICE_CREAM: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberry_ice_cream", Item.Properties())
    val CHERRIES_ICE_CREAM: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries_ice_cream", Item.Properties())
    val STARFRUIT_ICE_CREAM: DeferredItem<Item> = ITEMS.registerSimpleItem("starfruit_ice_cream", Item.Properties())
    val JALAPENO_ICE_CREAM: DeferredItem<Item> = ITEMS.registerSimpleItem("jalapeno_ice_cream", Item.Properties())
    val VANILLA_CHOCOLATE_ICE_CREAM: DeferredItem<Item> =
        ITEMS.registerSimpleItem("vanilla_chocolate_ice_cream", Item.Properties())
    val BLUEBERRY_CHERRIES_ICE_CREAM: DeferredItem<Item> =
        ITEMS.registerSimpleItem("blueberry_cherries_ice_cream", Item.Properties())
    val STARFRUIT_JALAPENO_ICE_CREAM: DeferredItem<Item> =
        ITEMS.registerSimpleItem("starfruit_jalapeno_ice_cream", Item.Properties())

    val VANILLA_ICE_CREAM_FLUID_BUCKET: DeferredItem<Item> =
        ITEMS.registerSimpleItem("vanilla_ice_cream_fluid_bucket", Item.Properties())
    val CHOCOLATE_ICE_CREAM_FLUID_BUCKET: DeferredItem<Item> =
        ITEMS.registerSimpleItem("chocolate_ice_cream_fluid_bucket", Item.Properties())
    val BLUEBERRY_ICE_CREAM_FLUID_BUCKET: DeferredItem<Item> =
        ITEMS.registerSimpleItem("blueberry_ice_cream_fluid_bucket", Item.Properties())
    val CHERRIES_ICE_CREAM_FLUID_BUCKET: DeferredItem<Item> =
        ITEMS.registerSimpleItem("cherries_ice_cream_fluid_bucket", Item.Properties())
    val STARFRUIT_ICE_CREAM_FLUID_BUCKET: DeferredItem<Item> =
        ITEMS.registerSimpleItem("starfruit_ice_cream_fluid_bucket", Item.Properties())
    val JALAPENO_ICE_CREAM_FLUID_BUCKET: DeferredItem<Item> =
        ITEMS.registerSimpleItem("jalapeno_ice_cream_fluid_bucket", Item.Properties())

    val POWSICLE_PALETERIA: DeferredItem<Item> = ITEMS.registerSimpleItem("powsicle_paleteria", Item.Properties())
    val LUAU_PALETERIA: DeferredItem<Item> = ITEMS.registerSimpleItem("luau_paleteria", Item.Properties())
    val WATERMELON_PALETERIA: DeferredItem<Item> = ITEMS.registerSimpleItem("watermelon_paleteria", Item.Properties())
    val PINK_SNOWMAN_PALETERIA: DeferredItem<Item> =
        ITEMS.registerSimpleItem("pink_snowman_paleteria", Item.Properties())
    val HEART_CRUSH_PALETERIA: DeferredItem<Item> = ITEMS.registerSimpleItem("heart_crush_paleteria", Item.Properties())
    val HAMIMELON_PALETERIA: DeferredItem<Item> = ITEMS.registerSimpleItem("hamimelon_paleteria", Item.Properties())

    val CHERRIES_PIE: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries_pie", Item.Properties())
    val CHERRIES_PIE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries_pie_slice", Item.Properties())
    val TRUFFLE_PIE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle_pie", Item.Properties())
    val TRUFFLE_PIE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle_pie_slice", Item.Properties())
    val PANCAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("pancake", Item.Properties())
    val DURIAN_MILE_CREPE_CAKE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("durian_mile_crepe_cake", Item.Properties())
    val DURIAN_MILE_CREPE_CAKE_SLICE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("durian_mile_crepe_cake_slice", Item.Properties())

    val MUFFIN: DeferredItem<Item> = ITEMS.registerSimpleItem("muffin", Item.Properties())
    val BLUEBERRY_MUFFIN: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberry_muffin", Item.Properties())
    val CRANBERRY_MUFFIN: DeferredItem<Item> = ITEMS.registerSimpleItem("cranberry_muffin", Item.Properties())
    val CHOCOLATE_MUFFIN: DeferredItem<Item> = ITEMS.registerSimpleItem("chocolate_muffin", Item.Properties())
    val ORANGE_MUFFIN: DeferredItem<Item> = ITEMS.registerSimpleItem("orange_muffin", Item.Properties())

    val CUPCAKE_QAQ: DeferredItem<Item> = ITEMS.registerSimpleItem("cupcake_qaq", Item.Properties())
    val TACO: DeferredItem<Item> = ITEMS.registerSimpleItem("taco", Item.Properties())
    val FRIED_CHICKEN: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_chicken", Item.Properties())
    val FRIED_DRUMSTICK: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_drumstick", Item.Properties())
    val FRIED_PORKCHOP: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_porkchop", Item.Properties())
    val FRIED_STEAK: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_steak", Item.Properties())
    val FRIED_FISH: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_fish", Item.Properties())
    val FRIED_TENPURA: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_tenpura", Item.Properties())

    val DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("donut", Item.Properties())
    val CHOCOLATE_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("chocolate_donut", Item.Properties())
    val STRAWBERRY_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("strawberry_donut", Item.Properties())
    val WHITE_CHOCOLATE_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("white_chocolate_donut", Item.Properties())
    val LEMON_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon_donut", Item.Properties())
    val SNOW_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("snow_donut", Item.Properties())

    val CRANBERRY_COOKIE: DeferredItem<Item> = ITEMS.registerSimpleItem("cranberry_cookie", Item.Properties())
    val BLUEBERRY_COOKIE: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberry_cookie", Item.Properties())
    val LEMON_COOKIE: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon_cookie", Item.Properties())

    val HANAMI_DANGO: DeferredItem<Item> = ITEMS.registerSimpleItem("hanami_dango", Item.Properties())
    val SAKURA_MOCHI: DeferredItem<Item> = ITEMS.registerSimpleItem("sakura_mochi", Item.Properties())
    val CHOCOLATE: DeferredItem<Item> = ITEMS.registerSimpleItem("chocolate", Item.Properties())

    val JALAPENO: DeferredItem<Item> = ITEMS.registerSimpleItem("jalapeno", Item.Properties())
    val CORN: DeferredItem<Item> = ITEMS.registerSimpleItem("corn", Item.Properties())
    val CORN_SEED: DeferredItem<Item> = ITEMS.registerSimpleItem("corn_seed", Item.Properties())
    val APPLE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("apple_slice", Item.Properties())

    // Pineapple
    val PINEAPPLE = registerSimpleBlockItem(ModBlocks.PINEAPPLE)
    val PINEAPPLE_CROP = registerSimpleBlockItem(ModBlocks.PINEAPPLE_CROP)
    val PINEAPPLE_JUICE_BUCKET = registerBucketItem("pineapple_juice_bucket", ModFluids.PINEAPPLE_JUICE)
    val PINEAPPLE_SLICE = registerFoodItem("pineapple_slice") {
        PineappleFoodItems.Slice(it.build(food = FoodProperties(nutrition = 3, saturationModifier = 0.3F)))
    }
    val PINEAPPLE_JUICE = registerFoodItem("pineapple_juice") {
        PineappleFoodItems.Juice(
            it.build(
                food = FoodProperties(nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true), stackSize = 16
            )
        )
    }
    val PINEAPPLE_PASTRIES = registerFoodItem("pineapple_pastries") {
        PineappleFoodItems.Pastries(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.6F)))
    }
    val PINEAPPLE_PIES = registerFoodItem("pineapple_pies") {
        PineappleFoodItems.Pie(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.35F)))
    }
    val PINEAPPLE_PALETERIA = registerFoodItem("pineapple_paleteria") {
        PineappleFoodItems.Paleteria(it.build(food = FoodProperties(nutrition = 10, saturationModifier = 0.2F)))
    }

    val MUSANG_KING_DURIAN: DeferredItem<Item> = ITEMS.registerSimpleItem("musang_king_durian", Item.Properties())
    val HALF_OF_MUSANG_KING_DURIAN: DeferredItem<Item> =
        ITEMS.registerSimpleItem("half_of_musang_king_durian", Item.Properties())
    val MUSANG_KING_DURIAN_FLESH: DeferredItem<Item> =
        ITEMS.registerSimpleItem("musang_king_durian_flesh", Item.Properties())
    val COOKED_DURIAN_FLESH: DeferredItem<Item> = ITEMS.registerSimpleItem("cooked_durian_flesh", Item.Properties())
    val DURIAN_CRISP: DeferredItem<Item> = ITEMS.registerSimpleItem("durian_crisp", Item.Properties())
    val HAMIMELON: DeferredItem<Item> = ITEMS.registerSimpleItem("hamimelon", Item.Properties())
    val HAMIMELON_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("hamimelon_slice", Item.Properties())
    val HAMIMELON_SEED: DeferredItem<Item> = ITEMS.registerSimpleItem("hamimelon_seed", Item.Properties())
    val STARFRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("starfruit", Item.Properties())
    val STARFRUIT_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("starfruit_slice", Item.Properties())
    val MANGO: DeferredItem<Item> = ITEMS.registerSimpleItem("mango", Item.Properties())
    val MANGO_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("mango_slice", Item.Properties())
    val KIWIFRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("kiwifruit", Item.Properties())
    val HALF_OF_KIWIFRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("half_of_kiwifruit", Item.Properties())
    val KIWIFRUIT_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("kiwifruit_slice", Item.Properties())
    val AVOCADO: DeferredItem<Item> = ITEMS.registerSimpleItem("avocado", Item.Properties())
    val HALF_OF_AVOCADO: DeferredItem<Item> = ITEMS.registerSimpleItem("half_of_avocado", Item.Properties())
    val AVOCADO_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("avocado_slice", Item.Properties())
    val ORANGE: DeferredItem<Item> = ITEMS.registerSimpleItem("orange", Item.Properties())
    val ORANGE_SEGMENT: DeferredItem<Item> = ITEMS.registerSimpleItem("orange_segment", Item.Properties())
    val LEMON: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon", Item.Properties())
    val LEMON_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon_slice", Item.Properties())
    val PEACH: DeferredItem<Item> = ITEMS.registerSimpleItem("peach", Item.Properties())
    val PEACH_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("peach_slice", Item.Properties())
    val PEAR: DeferredItem<Item> = ITEMS.registerSimpleItem("pear", Item.Properties())
    val PEAR_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("pear_slice", Item.Properties())
    val DRAGON_FRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("dragon_fruit", Item.Properties())
    val DRAGON_FRUIT_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("dragon_fruit_slice", Item.Properties())
    val DRAGON_FRUIT_CACTUS: DeferredItem<Item> = ITEMS.registerSimpleItem("dragon_fruit_cactus", Item.Properties())
    val DRAGON_FRUIT_CACTUS_FLOWER: DeferredItem<Item> =
        ITEMS.registerSimpleItem("dragon_fruit_cactus_flower", Item.Properties())
    val STRAWBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("strawberry", Item.Properties())
    val PINEBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("pineberry", Item.Properties())
    val BLUEBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberry", Item.Properties())
    val CRANBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("cranberry", Item.Properties())
    val CHERRIES: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries", Item.Properties())
    val OLIVE_FRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("olive_fruit", Item.Properties())

    val ALFALFA: DeferredItem<Item> = ITEMS.registerSimpleItem("alfalfa", Item.Properties())
    val ALFALFA_SEED: DeferredItem<Item> = ITEMS.registerSimpleItem("alfalfa_seed", Item.Properties())
    val ACORN: DeferredItem<Item> = ITEMS.registerSimpleItem("acorn", Item.Properties())
    val FILBERT: DeferredItem<Item> = ITEMS.registerSimpleItem("filbert", Item.Properties())
    val TRUFFLE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle", Item.Properties())
    val GARLIC: DeferredItem<Item> = ITEMS.registerSimpleItem("garlic", Item.Properties())
    val BOTTON_MUSHROOM: DeferredItem<Item> = ITEMS.registerSimpleItem("botton_mushroom", Item.Properties())
    val COOKED_BOTTON_MUSHROOM: DeferredItem<Item> =
        ITEMS.registerSimpleItem("cooked_botton_mushroom", Item.Properties())
    val WOOD_MUSHROOM: DeferredItem<Item> = ITEMS.registerSimpleItem("wood_mushroom", Item.Properties())
    val RAW_PRAWN: DeferredItem<Item> = ITEMS.registerSimpleItem("raw_prawn", Item.Properties())
    val COOKED_PRAWN: DeferredItem<Item> = ITEMS.registerSimpleItem("cooked_prawn", Item.Properties())

    val FLOWER_RING_HELMET: DeferredItem<Item> = ITEMS.registerSimpleItem("flower_ring_helmet", Item.Properties())
    val WITCH_HAT_HELMET: DeferredItem<Item> = ITEMS.registerSimpleItem("witch_hat_helmet", Item.Properties())
    val CHEF_HAT_HELMET: DeferredItem<Item> = ITEMS.registerSimpleItem("chef_hat_helmet", Item.Properties())
    val CHRISTMAS_HAT_HELMET: DeferredItem<Item> = ITEMS.registerSimpleItem("christmas_hat_helmet", Item.Properties())
    val MINER_HAT_HELMET: DeferredItem<Item> = ITEMS.registerSimpleItem("miner_hat_helmet", Item.Properties())

    val CHRISTMAS_MUFFLER_CHESTPLATE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("christmas_muffler_chestplate", Item.Properties())
    val CHRISTMAS_WREATHS: DeferredItem<Item> = ITEMS.registerSimpleItem("christmas_wreaths", Item.Properties())
    val CHRISTMAS_SOCK: DeferredItem<Item> = ITEMS.registerSimpleItem("christmas_sock", Item.Properties())

    val GIFT_SHORT_RED: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_short_red", Item.Properties())
    val GIFT_TALL_RED: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_tall_red", Item.Properties())
    val GIFT_SHORT_GREEN: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_short_green", Item.Properties())
    val GIFT_TALL_GREEN: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_tall_green", Item.Properties())
    val GIFT_SHORT_BLUE: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_short_blue", Item.Properties())
    val GIFT_TALL_BLUE: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_tall_blue", Item.Properties())
    val GIFT_SHORT_PINK: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_short_pink", Item.Properties())
    val GIFT_TALL_PINK: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_tall_pink", Item.Properties())
    val GIFT_SHORT_LUCKY: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_short_lucky", Item.Properties())
    val GIFT_TALL_LUCKY: DeferredItem<Item> = ITEMS.registerSimpleItem("gift_tall_lucky", Item.Properties())

    val PEARL_ROCK_ORE: DeferredItem<BlockItem> = ITEMS.registerSimpleBlockItem(ModBlocks.PEARL_ROCK_ORE)
    val DEEPSLATE_PEARL_ROCK_ORE: DeferredItem<BlockItem> =
        ITEMS.registerSimpleBlockItem(ModBlocks.DEEPSLATE_PEARL_ROCK_ORE)

    private fun <I : FoodItem> registerFoodItem(name: String, func: Function<Item.Properties, out I>): DeferredItem<I> {
        val holder = ITEMS.registerItem(name, func, Item.Properties())
        FOOD_ITEM_HOLDERS.add(holder)
        return holder
    }

    private fun registerSimpleBlockItem(block: Holder<Block>): DeferredItem<BlockItem> {
        return ITEMS.registerSimpleBlockItem(block)
    }

    private fun registerBucketItem(name: String, fluid: Supplier<out Fluid>): DeferredItem<BucketItem> {
        return ITEMS.registerItem(name) {
            BucketItem(fluid.get(), Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
        }
    }

    private fun Item.Properties.build(
        food: FoodProperties? = null, stackSize: Int = 64,
    ): Item.Properties = apply {
        stacksTo(stackSize)
        if (food != null) food(food)
    }

    private fun FoodProperties(
        nutrition: Int = 0, saturationModifier: Float = 0F, alwaysEdible: Boolean = false
    ): FoodProperties {
        return FoodProperties.Builder().apply {
            nutrition(nutrition)
            saturationModifier(saturationModifier)
            if (alwaysEdible) alwaysEdible()
        }.build()
    }

}