package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import com.djinfinite.manors_bounty.content.durian.DurianFoodItems
import com.djinfinite.manors_bounty.content.food.*
import com.djinfinite.manors_bounty.content.pineapple.PineappleFoodItems
import com.djinfinite.manors_bounty.util.inWholeTicks
import net.minecraft.core.Holder
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.*
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Function
import java.util.function.Supplier
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

object ModItems {

    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(ManorsBounty.ID)
    val FOOD_ITEM_HOLDERS = mutableListOf<DeferredItem<out FoodItem>>()

    // === Icons ===
    val MAIN_ICON_ITEM = item("main_icon_item")
    val BUILDING_ICON_ITEM = item("building_icon_item")
    val SLOT_BAN_ITEM = item("slot_ban_item")

    // === Misc ===
    val PEARL_ROCK = item("pearl_rock")
    val SNOW_DYE = item("snow_dye")
    val LAVENDER_DYE = item("lavender_dye")
    val TURQUOISE_DYE = item("turquoise_dye")
    val SUPER_ANCIENT_MUSHROOM = item("super_ancient_mushroom")
    val INCOMPLETE_CREATIVE_MECHANISM = item("incomplete_creativity_mechanism")
    val CREATIVITY_MECHANISM = item("creativity_mechanism")

    // UNC
    val LAVENDER = item("lavender")
    val DEBRISHROOM = item("debrishroom")
    val BUTTER = item("butter")
    val BOXED_MILK = item("boxed_milk")
    val DARK_CHOCOLATE_CHIPS = item("dark_chocolate_chips")
    val SCOOP_OF_ICE_CREAM = item("scoop_of_ice_cream")
    val SAUSAGE = item("sausage")
    val EMMENTAL_CHEESE_WHEEL = item("emmental_cheese_wheel")
    val EMMENTAL_CHEESE_WEDGE = item("emmental_cheese_wedge")
    val EMMENTAL_CHEESE_SLICE = item("emmental_cheese_slice")
    val CHEDDAR_CHEESE_WHEEL = item("cheddar_cheese_wheel")
    val CHEDDAR_CHEESE_WEDGE = item("cheddar_cheese_wedge")
    val CHEDDAR_CHEESE_SLICE = item("cheddar_cheese_slice")
    val MOZZARELLA_CHEESE_WHEEL = item("mozzarella_cheese_wheel")
    val MOZZARELLA_CHEESE_WEDGE = item("mozzarella_cheese_wedge")
    val MOZZARELLA_CHEESE_SLICE = item("mozzarella_cheese_slice")
    val POLISHED_MARBLE = item("polished_marble")
    val POMEGRANATE_SEEDS = item("pomegranate_seeds")
    val GOLD_KIWIFRUIT_SLICE = item("gold_kiwifruit_slice")
    val BOXED_WHIPPING_CREAM = item("boxed_whipping_cream")
    val SCOOP_OF_CHERRY_ICE_CREAM = item("scoop_of_cherry_ice_cream")
    val SCOOP_OF_CHOCOLATE_ICE_CREAM = item("scoop_of_chocolate_ice_cream")
    val CHOCOLATE_CHIPS = item("chocolate_chips")

    // === Liquids ===
    val OLIVE_OIL_BUCKET = bucketItem("olive_oil_bucket", ModFluids.OLIVE_OIL)
    val CAKE_LIQUID_BUCKET = bucketItem("cake_liquid_bucket", ModFluids.CAKE_LIQUID)

    // === Machines ===
    val ICE_CREAM_MACHINE = blockItem(ModBlocks.ICE_CREAM_MACHINE)
    val FRYER = blockItem(ModBlocks.FRYER)
    val OVEN = blockItem(ModBlocks.OVEN)

    // === Cakes ===
    val VANILLA_CAKE_SLICE = foodItez("vanilla_cake_slice", food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F)
    })
    val CARAMEL_CHOCOLATE_CAKE = blockItem(ModBlocks.CARAMEL_CHOCOLATE_CAKE)
    val CARAMEL_CHOCOLATE_CAKE_SLICE = foodItez("caramel_chocolate_cake_slice", food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true)
    })
    val SWEET_BERRY_CAKE = blockItem(ModBlocks.SWEET_BERRY_CAKE)
    val SWEET_BERRY_CAKE_SLICE = foodItez(
        "sweet_berry_cake_slice",
        food = { FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true) },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.SWEET_BERRY, FoodType.C, ModMobEffects.BURSTING_BERRY),
    )
    val CHORUS_FLOWER_JELLY_CAKE = blockItem(ModBlocks.CHORUS_FLOWER_JELLY_CAKE)
    val CHORUS_FLOWER_JELLY_CAKE_SLICE = foodItez(
        "chorus_flower_jelly_cake_slice",
        food = { FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true) },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHORUS_FRUIT, FoodType.C, ModMobEffects.TRANSMIT),
    )
    val NETHER_WART_SOUL_CAKE = blockItem(ModBlocks.NETHER_WART_SOUL_CAKE)
    val NETHER_WART_SOUL_CAKE_SLICE = foodItez("nether_wart_soul_cake_slice", food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true)
    })

    // === Bowled ===
    val MARBLE_BOWL = item("marble_bowl")
    val COFFEE = foodItez("coffee", stackSize = 16, useAnim = UseAnim.DRINK, food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL)
    })

    // === Soups ===
    val BORSCHT = foodItez(
        "borscht", stackSize = 16, food = {
            FoodProperties(nutrition = 20, saturationModifier = 0.8F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL) {
                effect({ MobEffectInstance(MobEffects.REGENERATION, 20.minutes.inWholeTicks, 0, false, true) }, 1F)
                effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 20.minutes.inWholeTicks, 0, false, true) }, 1F)
            }
        })
    val CREAM_WITH_MUSHROOM_SOUP = foodItez("cream_with_mushroom_soup", stackSize = 16, food = {
        FoodProperties(nutrition = 16, saturationModifier = 0.8F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL) {
            effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
            effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
        }
    })
    val MISO_SOUP = foodItez("miso_soup", stackSize = 16, food = {
        FoodProperties(nutrition = 16, saturationModifier = 0.8F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL) {
            effect({ MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
            effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
        }
    })
    val PRAWN_AND_CORN_SOUP = foodItez("prawn_and_corn_soup", stackSize = 16, food = {
        FoodProperties(nutrition = 16, saturationModifier = 0.8F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL) {
            effect({ MobEffectInstance(MobEffects.DAMAGE_BOOST, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
            effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
        }
    })

    // === Salads ===
    val COSMIC_DRAGON_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("cosmic_dragon_salad", Item.Properties())
    val ROSA_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("rosa_salad", Item.Properties())
    val SEA_PEARL_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("sea_pearl_salad", Item.Properties())
    val MELON_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("melon_salad", Item.Properties())
    val JEWELLERY_BOX_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("jewellery_box_salad", Item.Properties())
    val PRIMARY_COLORS_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("primary_colors_salad", Item.Properties())

    // === Juices ===
    val DEFORMED_GLASS_BOTTLE = item("deformed_glass_bottle")
    val MELON_SODA = foodItez(
        "melon_soda", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.HAMI_MELON, FoodType.B, ModMobEffects.MELON_GRAVITY)
    )

    val BUBBLE_TEA = foodItez(
        "bubble_tea", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        })

    val GRIMACE_SHAKE = foodItez(
        "grimace_shake", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.STRAWBERRY,
            FoodType.B,
            foodEffects = listOf(ModMobEffects.BURSTING_BERRY, ModMobEffects.CHERRY_BLOSSOMS_WEEPING)
        )
    )

    val ORANGE_JUICE = foodItez(
        "orange_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.ORANGE, FoodType.B, ModMobEffects.RUTIN_LEMONENE)
    )

    val LEMONADE = foodItez(
        "lemonade", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 5, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }) // TODO: effect

    val APPLE_JUICE = foodItez(
        "apple_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.APPLE, FoodType.B, ModMobEffects.ROSA_HEDGE)
    )

    val PEAR_WITH_ROCK_SUGAR = foodItez(
        "pear_with_rock_sugar", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.PEAR, FoodType.B, ModMobEffects.ROSA_HEDGE)
    )

    val BELLINI_BASE = foodItez(
        "bellini_base", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.PEACH, FoodType.B, ModMobEffects.ROSA_HEDGE)
    )

    val STRAWBERRY_SHAKE = foodItez(
        "strawberry_shake", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.STRAWBERRY, FoodType.B, ModMobEffects.BURSTING_BERRY)
    )

    val MANGO_JUICE = foodItez(
        "mango_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.MANGO,
            FoodType.B,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.ORIGINAL_EVOLUTION)
        )
    )

    val WATERMELON_JUICE = foodItez(
        "watermelon_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.WATERMELON, FoodType.B, ModMobEffects.MELON_GRAVITY)
    )

    val MARTINI = foodItez(
        "martini", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }) // TODO: effect

    val CHORUS_FRUIT_JUICE = foodItez(
        "chorus_fruit_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHORUS_FRUIT, FoodType.B, ModMobEffects.TRANSMIT)
    )

    val FRUIT_PUNCH = foodItez(
        "fruit_punch", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 5, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        })

    val BLUEBERRY_YOGURT = foodItez(
        "blueberry_yogurt", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.BLUEBERRY, FoodType.B, ModMobEffects.BURSTING_BERRY)
    )

    // === C-type Drink ===
    val MANGO_PUDDING = foodItez(
        "mango_pudding", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.3F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.MANGO,
            FoodType.C,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.ORIGINAL_EVOLUTION)
        )
    )

    val RISALAMANDE = foodItez(
        "risalamande", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHERRY, FoodType.C, ModMobEffects.CHERRY_BLOSSOMS_WEEPING)
    )

    val ETON_MESS = foodItez(
        "eton_mess", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.6F, alwaysEdible = true, usingConvertsTo = DEFORMED_GLASS_BOTTLE)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.SWEET_BERRY, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )

    // === Other Drink ===
    val BOXED_CHOCOLATE_MILK = foodItez(
        "boxed_chocolate_milk", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true)
        })

    val BOXED_LEMON_TEA = foodItez(
        "boxed_lemon_tea", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true)
        }) // TODO: effect

    // === Oils ===
    val BOTTLED_OLIVE_OIL = item("bottled_olive_oil")
    val LAVENDER_ESSENTIAL_OIL = item("lavender_essential_oil")

    // === IDK ===
    val MARMALADE = foodItez(
        "marmalade", stackSize = 16, food = {
            FoodProperties(nutrition = 12, saturationModifier = 0.1F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.ORANGE, FoodType.B, ModMobEffects.RUTIN_LEMONENE)
    )

    val CHORUS_CHROME = foodItez(
        "chorus_chrome", stackSize = 16, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHORUS_FRUIT, FoodType.C, ModMobEffects.TRANSMIT)
    )

    // === Ice Cream ===
    val ICE_CREAM_CONE = item("ice_cream_cone")
    val VANILLA_ICE_CREAM = foodItez("vanilla_ice_cream", food = {
        FoodProperties(nutrition = 6, saturationModifier = 0.3F)
    })

    val CHOCOLATE_ICE_CREAM = foodItez("chocolate_ice_cream", food = {
        FoodProperties(nutrition = 6, saturationModifier = 0.3F)
    })

    val BLUEBERRY_ICE_CREAM = foodItez(
        "blueberry_ice_cream", food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.BLUEBERRY, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )

    val CHERRIES_ICE_CREAM = foodItez(
        "cherries_ice_cream", food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHERRY, FoodType.C, ModMobEffects.CHERRY_BLOSSOMS_WEEPING)
    )

    val STARFRUIT_ICE_CREAM = foodItez(
        "starfruit_ice_cream", food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.STARFRUIT,
            FoodType.C,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.MOMENTARY_METEOR)
        )
    )

    val JALAPENO_ICE_CREAM = foodItez(
        "jalapeno_ice_cream", food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true) {
                effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 2.seconds.inWholeTicks, 0, false, true) }, 1F)
            }
        })

    val VANILLA_CHOCOLATE_ICE_CREAM = foodItez("vanilla_chocolate_ice_cream", food = {
        FoodProperties(nutrition = 6, saturationModifier = 0.3F)
    })

    val BLUEBERRY_CHERRIES_ICE_CREAM = foodItez(
        "blueberry_cherries_ice_cream", food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.CHERRY,
            FoodType.C,
            foodEffects = listOf(ModMobEffects.CHERRY_BLOSSOMS_WEEPING, ModMobEffects.BURSTING_BERRY)
        )
    )

    val STARFRUIT_JALAPENO_ICE_CREAM = foodItez(
        "starfruit_jalapeno_ice_cream", food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true) {
                effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 2.seconds.inWholeTicks, 0, false, true) }, 1F)
            }
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.STARFRUIT,
            FoodType.C,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.MOMENTARY_METEOR)
        )
    )

    // === Ice Cream Fluids ===
    val VANILLA_ICE_CREAM_BUCKET = bucketItem("vanilla_ice_cream_bucket", ModFluids.VANILLA_ICE_CREAM)
    val CHOCOLATE_ICE_CREAM_BUCKET = bucketItem("chocolate_ice_cream_bucket", ModFluids.CHOCOLATE_ICE_CREAM)
    val BLUEBERRY_ICE_CREAM_BUCKET = bucketItem("blueberry_ice_cream_bucket", ModFluids.BLUEBERRY_ICE_CREAM)
    val CHERRIES_ICE_CREAM_BUCKET = bucketItem("cherries_ice_cream_bucket", ModFluids.CHERRIES_ICE_CREAM)
    val STARFRUIT_ICE_CREAM_BUCKET = bucketItem("starfruit_ice_cream_bucket", ModFluids.STARFRUIT_ICE_CREAM)
    val JALAPENO_ICE_CREAM_BUCKET = bucketItem("jalapeno_ice_cream_bucket", ModFluids.JALAPENO_ICE_CREAM)

    // === Paleteria ===
    val POWSICLE_POPSICLE = foodItez(
        "powsicle_popsicle", stackSize = 16, food = {
            FoodProperties(nutrition = 8, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.OTHER, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )
    val LUAU_POPSICLE = foodItez(
        "luau_popsicle", stackSize = 16, food = {
            FoodProperties(nutrition = 12, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.OTHER,
            FoodType.C,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.ORIGINAL_EVOLUTION)
        )
    )
    val WATERMELON_POPSICLE = foodItez(
        "watermelon_popsicle", stackSize = 16, food = {
            FoodProperties(nutrition = 8, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.WATERMELON, FoodType.C, ModMobEffects.MELON_GRAVITY)
    )
    val PINK_SNOWMAN_POPSICLE = foodItez(
        "pink_snowman_popsicle", stackSize = 16, food = {
            FoodProperties(nutrition = 12, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.OTHER, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )
    val HEART_CRUSH_POPSICLE = foodItez(
        "heart_crush_popsicle", stackSize = 16, food = {
            FoodProperties(nutrition = 10, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.OTHER, FoodType.C, ModMobEffects.CHERRY_BLOSSOMS_WEEPING)
    )
    val HAMI_MELON_POPSICLE = foodItez(
        "hami_melon_popsicle", stackSize = 16, food = {
            FoodProperties(nutrition = 8, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.HAMI_MELON, FoodType.C, ModMobEffects.MELON_GRAVITY)
    )

    val CHERRIES_PIE: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries_pie", Item.Properties())
    val CHERRIES_PIE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries_pie_slice", Item.Properties())
    val TRUFFLE_PIE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle_pie", Item.Properties())
    val TRUFFLE_PIE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle_pie_slice", Item.Properties())
    val PANCAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("pancake", Item.Properties())

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
    val PINEAPPLE = blockItem(ModBlocks.PINEAPPLE)
    val PINEAPPLE_CROP = blockItem(ModBlocks.PINEAPPLE_CROP)
    val PINEAPPLE_JUICE_BUCKET = bucketItem("pineapple_juice_bucket", ModFluids.PINEAPPLE_JUICE)
    val PINEAPPLE_SLICE = foodItem("pineapple_slice") {
        PineappleFoodItems.Slice(it.build(food = FoodProperties(nutrition = 3, saturationModifier = 0.3F)))
    }
    val PINEAPPLE_JUICE = foodItem("pineapple_juice") {
        PineappleFoodItems.Juice(
            it.build(
                food = FoodProperties(nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true), stackSize = 16
            )
        )
    }
    val PINEAPPLE_PASTRIES = foodItem("pineapple_pastries") {
        PineappleFoodItems.Pastries(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.6F)))
    }
    val PINEAPPLE_PIES = foodItem("pineapple_pies") {
        PineappleFoodItems.Pie(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.35F)))
    }
    val PINEAPPLE_PALETERIA = foodItem("pineapple_paleteria") {
        PineappleFoodItems.Paleteria(it.build(food = FoodProperties(nutrition = 10, saturationModifier = 0.2F)))
    }

    // Durian
    val DURIAN_SEED = blockItem(ModBlocks.DURIAN_SEED)
    val MUSANG_KING_DURIAN = item("musang_king_durian")
    val HALF_OF_MUSANG_KING_DURIAN = foodItem("half_of_musang_king_durian") {
        DurianFoodItems.Half(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.3F)))
    }
    val MUSANG_KING_DURIAN_FLESH = foodItem("musang_king_durian_flesh") {
        DurianFoodItems.Flesh(it.build(food = FoodProperties(nutrition = 2, saturationModifier = 0.3F)))
    }
    val COOKED_DURIAN_FLESH = foodItem("cooked_durian_flesh") {
        DurianFoodItems.CookedFlesh(it.build(food = FoodProperties(nutrition = 4, saturationModifier = 0.3F)))
    }
    val DURIAN_CRISP = foodItem("durian_crisp") {
        DurianFoodItems.Crisp(
            it.build(food = FoodProperties(nutrition = 6, saturationModifier = 0.35F, alwaysEdible = true))
        )
    }
    val DURIAN_MILE_CREPE_CAKE = foodItem("durian_mile_crepe_cake") {
        DurianFoodItems.Cake(
            it.build(
                stackSize = 16, food = FoodProperties(nutrition = 8, saturationModifier = 0.3F, alwaysEdible = true)
            )
        )
    }
    val DURIAN_MILE_CREPE_CAKE_SLICE = foodItem("durian_mile_crepe_cake_slice") {
        DurianFoodItems.Cake(
            it.build(
                stackSize = 16, food = FoodProperties(nutrition = 2, saturationModifier = 0.3F, alwaysEdible = true)
            )
        )
    }


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
    val DRAGON_FRUIT_CACTUS_FLOWER: DeferredItem<Item> = ITEMS.registerSimpleItem("dragon_fruit_cactus_flower", Item.Properties())
    val STRAWBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("strawberry", Item.Properties())
    val PINEBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("pineberry", Item.Properties())
    val BLUEBERRIES: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberries", Item.Properties())
    val CRANBERRIES: DeferredItem<Item> = ITEMS.registerSimpleItem("cranberries", Item.Properties())
    val CHERRIES: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries", Item.Properties())
    val OLIVE_FRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("olive_fruit", Item.Properties())

    val ALFALFA: DeferredItem<Item> = ITEMS.registerSimpleItem("alfalfa", Item.Properties())
    val ALFALFA_SEED: DeferredItem<Item> = ITEMS.registerSimpleItem("alfalfa_seed", Item.Properties())
    val ACORN: DeferredItem<Item> = ITEMS.registerSimpleItem("acorn", Item.Properties())
    val FILBERT: DeferredItem<Item> = ITEMS.registerSimpleItem("filbert", Item.Properties())
    val TRUFFLE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle", Item.Properties())
    val GARLIC: DeferredItem<Item> = ITEMS.registerSimpleItem("garlic", Item.Properties())
    val BUTTON_MUSHROOM: DeferredItem<Item> = ITEMS.registerSimpleItem("button_mushroom", Item.Properties())
    val COOKED_BUTTON_MUSHROOM: DeferredItem<Item> = ITEMS.registerSimpleItem("cooked_button_mushroom", Item.Properties())
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

    // === Ores ===
    val PEARL_ROCK_ORE = blockItem(ModBlocks.PEARL_ROCK_ORE)
    val DEEPSLATE_PEARL_ROCK_ORE = blockItem(ModBlocks.DEEPSLATE_PEARL_ROCK_ORE)

    // === Leaves ===
    val SCOTS_PINE_LEAVES = blockItem(ModBlocks.SCOTS_PINE_LEAVES)
    val ALPINE_TREE_LEAVES = blockItem(ModBlocks.ALPINE_TREE_LEAVES)
    val CHERRIES_TREE_LEAVES = blockItem(ModBlocks.CHERRIES_TREE_LEAVES)
    val STARFRUIT_TREE_LEAVES = blockItem(ModBlocks.STARFRUIT_TREE_LEAVES)
    val OLIVE_TREE_LEAVES = blockItem(ModBlocks.OLIVE_TREE_LEAVES)
    val ORANGE_LEAVES = blockItem(ModBlocks.ORANGE_LEAVES)
    val PEACH_LEAVES = blockItem(ModBlocks.PEACH_LEAVES)
    val PEAR_LEAVES = blockItem(ModBlocks.PEAR_LEAVES)
    val APPLE_LEAVES = blockItem(ModBlocks.APPLE_LEAVES)
    val MANGO_TREE_LEAVES = blockItem(ModBlocks.MANGO_TREE_LEAVES)
    val KIWIFRUIT_TREE_LEAVES = blockItem(ModBlocks.KIWIFRUIT_TREE_LEAVES)
    val AVOCADO_TREE_LEAVES = blockItem(ModBlocks.AVOCADO_TREE_LEAVES)

    // === Saplings & Seeds ===
    val SCOTS_PINE_SAPLING = blockItem(ModBlocks.SCOTS_PINE_SAPLING)
    val CHERRIES_SEED = blockItem(ModBlocks.CHERRIES_SEED)
    val STARFRUIT_SEED = blockItem(ModBlocks.STARFRUIT_SEED)
    val OLIVE_FRUIT_SEED = blockItem(ModBlocks.OLIVE_FRUIT_SEED)
    val ORANGE_SEED = blockItem(ModBlocks.ORANGE_SEED)
    val PEACH_SEED = blockItem(ModBlocks.PEACH_SEED)
    val PEAR_SEED = blockItem(ModBlocks.PEAR_SEED)
    val APPLE_SEED = blockItem(ModBlocks.APPLE_SEED)
    val MANGO_SEED = blockItem(ModBlocks.MANGO_SEED)
    val KIWIFRUIT_SEED = blockItem(ModBlocks.KIWIFRUIT_SEED)
    val AVOCADO_SEED = blockItem(ModBlocks.AVOCADO_SEED)

    // === Snow Color Blocks ===
    val SNOW_TERRACOTTA = blockItem(ModBlocks.SNOW_TERRACOTTA)
    val SNOW_GLAZED_TERRACOTTA = blockItem(ModBlocks.SNOW_GLAZED_TERRACOTTA)
    val SNOW_CONCRETE = blockItem(ModBlocks.SNOW_CONCRETE)
    val SNOW_CONCRETE_POWDER = blockItem(ModBlocks.SNOW_CONCRETE_POWDER)
    val SNOW_GLASS = blockItem(ModBlocks.SNOW_GLASS)
    val SNOW_GLASS_PANE = blockItem(ModBlocks.SNOW_GLASS_PANE)

    private fun item(name: String, func: (Item.Properties) -> Item.Properties = { it }): DeferredItem<Item> {
        return ITEMS.registerItem(name) { Item(func(it)) }
    }

    private fun <I : FoodItem> foodItem(name: String, func: Function<Item.Properties, out I>): DeferredItem<I> {
        val holder = ITEMS.registerItem(name, func, Item.Properties())
        FOOD_ITEM_HOLDERS.add(holder)
        return holder
    }

    private fun foodItez(
        name: String,
        food: (() -> FoodProperties)? = null,
        fruitEffect: FruitEffectDataComponent? = null,
        stackSize: Int = 64,
        useAnim: UseAnim? = null,
        func: ((Item.Properties) -> Item.Properties) = { it },
    ): DeferredItem<FoodItem2> {
        return ITEMS.registerItem(name) { props ->
            props.stacksTo(stackSize)
            fruitEffect?.let { props.component(ModDataComponents.FRUIT_EFFECT, fruitEffect) }
            food?.let { props.food(food()) }
            FoodItem2(useAnim, func(props))
        }
//        FOOD_ITEM_HOLDERS.add(holder) // TODO: fix this
    }

    private fun blockItem(block: Holder<Block>): DeferredItem<BlockItem> {
        return ITEMS.registerSimpleBlockItem(block)
    }

    private fun bucketItem(name: String, fluid: Supplier<out Fluid>): DeferredItem<BucketItem> {
        return ITEMS.registerItem(name) { BucketItem(fluid.get(), it.craftRemainder(Items.BUCKET).stacksTo(1)) }
    }

    private fun Item.Properties.build(
        food: FoodProperties? = null, stackSize: Int = 64, fruitEffect: FruitEffectDataComponent? = null
    ): Item.Properties = apply {
        stacksTo(stackSize)
        if (food != null) food(food)
        if (fruitEffect != null) component(ModDataComponents.FRUIT_EFFECT, fruitEffect)
    }

    private fun FoodProperties(
        nutrition: Int = 0,
        saturationModifier: Float = 0F,
        alwaysEdible: Boolean = false,
        eatFast: Boolean = false,
        usingConvertsTo: ItemLike? = null,
        action: (FoodProperties.Builder.() -> Unit)? = null
    ): FoodProperties {
        return FoodProperties.Builder().apply {
            nutrition(nutrition)
            saturationModifier(saturationModifier)
            if (usingConvertsTo != null) usingConvertsTo(usingConvertsTo)
            if (alwaysEdible) alwaysEdible()
            if (eatFast) fast()
            action?.invoke(this)
        }.build()
    }

}