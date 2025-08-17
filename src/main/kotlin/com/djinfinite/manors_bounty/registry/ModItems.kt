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

    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(ManorsBounty.MOD_ID)
    val FOOD_ITEM_HOLDERS = mutableListOf<DeferredItem<out FoodItem>>()

    val MAIN_ICON_ITEM = item("main_icon")
    val BUILDING_ICON_ITEM = item("building_blocks_icon")
    val SLOT_BAN_ITEM = item("slot_ban_icon")

    val PERLITE = item("perlite")
    val SNOW_DYE = item("snow_dye")
    val LAVENDER_DYE = item("lavender_dye")
    val TURQUOISE_DYE = item("turquoise_dye")
    val SUPER_ANCIENT_MUSHROOM = item("super_ancient_mushroom")
    val INCOMPLETE_CREATIVITY_MECHANISM = item("incomplete_creativity_mechanism")
    val CREATIVITY_MECHANISM = item("creativity_mechanism")
    val OLIVE_OIL_BUCKET = bucketItem("olive_oil_bucket", ModFluids.OLIVE_OIL)
    val CAKE_LIQUID_BUCKET = bucketItem("cake_liquid_bucket", ModFluids.CAKE_LIQUID)
    val ICE_CREAM_MACHINE = blockItem(ModBlocks.ICE_CREAM_MACHINE)
    val FRYER = blockItem(ModBlocks.FRYER)
    val OVEN = blockItem(ModBlocks.OVEN)
    val CUTTING_BOARD = blockItem(ModBlocks.CUTTING_BOARD)
    val BUTTER = item("butter")
    val VANILLA_CAKE_SLICE = foodItez("vanilla_cake_slice", food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F)
    })
    val CARAMEL_CHOCOLATE_CAKE = blockItem(ModBlocks.CARAMEL_CHOCOLATE_CAKE)
    val CARAMEL_CHOCOLATE_CAKE_SLICE = foodItez("caramel_chocolate_cake_slice", food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true)
    })
    val SWEET_BERRY_ICE_CREAM_CAKE = blockItem(ModBlocks.SWEET_BERRY_ICE_CREAM_CAKE)
    val SWEET_BERRY_ICE_CREAM_CAKE_SLICE = foodItez(
        "sweet_berry_ice_cream_cake_slice",
        food = { FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true) },
        fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.SWEET_BERRY, FoodType.C, ModMobEffects.BURSTING_BERRY
        ),
    )
    val NETHER_WART_SOUL_CAKE = blockItem(ModBlocks.NETHER_WART_SOUL_CAKE)
    val NETHER_WART_SOUL_CAKE_SLICE = foodItez("nether_wart_soul_cake_slice", food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true)
    })
    val CHORUS_FLOWER_JELLY_CAKE = blockItem(ModBlocks.CHORUS_FLOWER_JELLY_CAKE)
    val CHORUS_FLOWER_JELLY_CAKE_SLICE = foodItez(
        "chorus_flower_jelly_cake_slice",
        food = { FoodProperties(nutrition = 2, saturationModifier = 0.1F, eatFast = true) },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHORUS_FRUIT, FoodType.C, ModMobEffects.TRANSMIT),
    )
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
    val MARBLE_BOWL = item("marble_bowl")
    val BORSCHT = foodItez(
        "borscht", stackSize = 16, food = {
            FoodProperties(
                nutrition = 20, saturationModifier = 0.8F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL
            ) {
                effect({ MobEffectInstance(MobEffects.REGENERATION, 20.minutes.inWholeTicks, 0, false, true) }, 1F)
                effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 20.minutes.inWholeTicks, 0, false, true) }, 1F)
            }
        })
    val CREAM_OF_MUSHROOM_SOUP = foodItez("cream_of_mushroom_soup", stackSize = 16, food = {
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
    val SHRIMP_AND_GRITS = foodItez("shrimp_and_grits", stackSize = 16, food = {
        FoodProperties(nutrition = 16, saturationModifier = 0.8F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL) {
            effect({ MobEffectInstance(MobEffects.DAMAGE_BOOST, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
            effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
        }
    })
    val CANTONESE_FLOWER_TONG_SUI = foodItez("cantonese_flower_tong_sui", stackSize = 16, food = {
        FoodProperties(nutrition = 16, saturationModifier = 0.8F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL) {
            effect({ MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
            effect({ MobEffectInstance(ModMobEffects.WARM_SHIELD, 16.minutes.inWholeTicks, 0, false, true) }, 1F)
        }
    })
    val COSMIC_DRAGON_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("cosmic_dragon_salad", Item.Properties())
    val ROSE_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("rose_salad", Item.Properties())
    val OCEANIC_PEARL_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("oceanic_pearl_salad", Item.Properties())
    val MELON_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("melon_salad", Item.Properties())
    val JEWELERY_BOX_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("jewelery_box_salad", Item.Properties())
    val PRIMARY_COLORS_SALAD: DeferredItem<Item> = ITEMS.registerSimpleItem("primary_colors_salad", Item.Properties())
    val COFFEE = foodItez("coffee", stackSize = 16, useAnim = UseAnim.DRINK, food = {
        FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = MARBLE_BOWL)
    })
    val DEFORMABLE_GLASS_BOTTLE = item("deformable_glass_bottle")
    val CANTALOUPE_SODA = foodItez(
        "cantaloupe_soda",
        stackSize = 16,
        useAnim = UseAnim.DRINK,
        food = {
            FoodProperties(
                nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.CANTALOUPE, FoodType.B, ModMobEffects.MELON_GRAVITY)
    )
    val BUBBLE_TEA = foodItez(
        "bubble_tea", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        })
    val BERRY_MILKSHAKE = foodItez(
        "berry_milkshake", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.STRAWBERRY,
            FoodType.B,
            foodEffects = listOf(ModMobEffects.BURSTING_BERRY, ModMobEffects.CHERRY_BLOSSOMS_WEEPING)
        )
    )
    val ORANGE_JUICE = foodItez(
        "orange_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.ORANGE, FoodType.B, ModMobEffects.RUTIN_LEMONENE)
    )
    val LEMONADE = foodItez(
        "lemonade", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 5, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }) // TODO: effect
    val APPLE_JUICE = foodItez(
        "apple_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.APPLE, FoodType.B, ModMobEffects.ROSA_HEDGE)
    )
    val PEAR_JUICE_WITH_ROCK_SUGAR = foodItez(
        "pear_juice_with_rock_sugar", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.PEAR, FoodType.B, ModMobEffects.ROSA_HEDGE)
    )
    val BELLINI_BASE = foodItez(
        "bellini_base", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.PEACH, FoodType.B, ModMobEffects.ROSA_HEDGE)
    )
    val STRAWBERRY_MILKSHAKE = foodItez(
        "strawberry_milkshake",
        stackSize = 16,
        useAnim = UseAnim.DRINK,
        food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.STRAWBERRY, FoodType.B, ModMobEffects.BURSTING_BERRY)
    )
    val MANGO_JUICE = foodItez(
        "mango_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.MANGO,
            FoodType.B,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.ORIGINAL_EVOLUTION)
        )
    )
    val PINEAPPLE_JUICE = foodItem("pineapple_juice") {
        PineappleFoodItems.Juice(
            it.build(
                food = FoodProperties(nutrition = 4, saturationModifier = 0.1F, alwaysEdible = true), stackSize = 16
            )
        )
    }
    val WATERMELON_JUICE = foodItez(
        "watermelon_juice",
        stackSize = 16,
        useAnim = UseAnim.DRINK,
        food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.WATERMELON, FoodType.B, ModMobEffects.MELON_GRAVITY)
    )
    val MARTINI = foodItez(
        "martini", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }) // TODO: effect
    val CHORUS_FRUIT_JUICE = foodItez(
        "chorus_fruit_juice", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHORUS_FRUIT, FoodType.B, ModMobEffects.TRANSMIT)
    )
    val BLUEBERRY_YOGURT = foodItez(
        "blueberry_yogurt",
        stackSize = 16,
        useAnim = UseAnim.DRINK,
        food = {
            FoodProperties(
                nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.BLUEBERRY, FoodType.B, ModMobEffects.BURSTING_BERRY)
    )
    val MANGO_PUDDING = foodItez(
        "mango_pudding", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.3F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.MANGO,
            FoodType.C,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.ORIGINAL_EVOLUTION)
        )
    )
    val RISALAMANDE = foodItez(
        "risalamande", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.CHERRY, FoodType.C, ModMobEffects.CHERRY_BLOSSOMS_WEEPING
        )
    )
    val ETON_MESS = foodItez(
        "eton_mess",
        stackSize = 16,
        useAnim = UseAnim.DRINK,
        food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.6F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.SWEET_BERRY, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )
    val KIWIFRUIT_JUICE = foodItez(
        "kiwifruit_juice",
        stackSize = 16,
        useAnim = UseAnim.DRINK,
        food = {
            FoodProperties(
                nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.STRAWBERRY, FoodType.C, ModMobEffects.RUTIN_LEMONENE)
    )
    val FRUIT_PUNCH = foodItez(
        "fruit_punch", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(
                nutrition = 5, saturationModifier = 0.1F, alwaysEdible = true, usingConvertsTo = DEFORMABLE_GLASS_BOTTLE
            )
        })
    val POMEGRANATE_JUICE = item("pomegranate_juice")
    val AVOCADO_MILKSHAKE = item("avocado_milkshake")
    val BANANA_MILKSHAKE = item("banana_milkshake")
    val GRAPE_JUICE = item("grape_juice")
    val GRAPEFRUIT_JUICE = item("grapefruit_juice")
    val CRANBERRY_JELLY = item("cranberry_jelly")
    val CREME_CARAMEL = item("creme_caramel")
    val BANANA_SPLIT = item("banana_split")
    val PUDDING_A_LA_MODE = item("pudding_a_la_mode")
    val MEDITERRANEAN_SUNDAE = item("mediterranean_sundae")
    val RAINBOW_FRUIT_PARFAIT = item("rainbow_fruit_parfait")
    val MARMALADE = foodItez(
        "marmalade", stackSize = 16, food = {
            FoodProperties(nutrition = 12, saturationModifier = 0.1F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.ORANGE, FoodType.B, ModMobEffects.RUTIN_LEMONENE)
    )
    val BOXED_CHOCOLATE_MILK = foodItez(
        "boxed_chocolate_milk", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true)
        })
    val BOXED_LEMON_TEA = foodItez(
        "boxed_lemon_tea", stackSize = 16, useAnim = UseAnim.DRINK, food = {
            FoodProperties(nutrition = 2, saturationModifier = 0.1F, alwaysEdible = true)
        }) // TODO: effect
    val BOXED_MILK = item("boxed_milk")
    val BOXED_WHIPPED_CREAM = item("boxed_whipped_cream")
    val BOTTLED_OLIVE_OIL = item("bottled_olive_oil")
    val LAVENDER_ESSENTIAL_OIL = item("lavender_essential_oil")
    val CHORUS_ALLOY = foodItez(
        "chorus_alloy", stackSize = 16, food = {
            FoodProperties(nutrition = 3, saturationModifier = 0.1F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.CHORUS_FRUIT, FoodType.C, ModMobEffects.TRANSMIT)
    )
    val ICE_CREAM_CONE = item("ice_cream_cone")
    val VANILLA_ICE_CREAM = foodItez("vanilla_ice_cream", food = {
        FoodProperties(nutrition = 6, saturationModifier = 0.3F)
    })
    val CHOCOLATE_ICE_CREAM = foodItez("chocolate_ice_cream", food = {
        FoodProperties(nutrition = 6, saturationModifier = 0.3F)
    })
    val BLUEBERRY_ICE_CREAM = foodItez(
        "blueberry_ice_cream",
        food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true)
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.BLUEBERRY, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )
    val CHERRY_ICE_CREAM = foodItez(
        "cherry_ice_cream", food = {
            FoodProperties(nutrition = 6, saturationModifier = 0.3F, alwaysEdible = true)
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.CHERRY, FoodType.C, ModMobEffects.CHERRY_BLOSSOMS_WEEPING
        )
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
    val BLUEBERRY_CHERRY_ICE_CREAM = foodItez(
        "blueberry_cherry_ice_cream", food = {
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
    val VANILLA_ICE_CREAM_BUCKET = bucketItem("vanilla_ice_cream_bucket", ModFluids.VANILLA_ICE_CREAM)
    val CHOCOLATE_ICE_CREAM_BUCKET = bucketItem("chocolate_ice_cream_bucket", ModFluids.CHOCOLATE_ICE_CREAM)
    val BLUEBERRY_ICE_CREAM_BUCKET = bucketItem("blueberry_ice_cream_bucket", ModFluids.BLUEBERRY_ICE_CREAM)
    val CHERRY_ICE_CREAM_BUCKET = bucketItem("cherry_ice_cream_bucket", ModFluids.CHERRY_ICE_CREAM)
    val STARFRUIT_ICE_CREAM_BUCKET = bucketItem("starfruit_ice_cream_bucket", ModFluids.STARFRUIT_ICE_CREAM)
    val JALAPENO_ICE_CREAM_BUCKET = bucketItem("jalapeno_ice_cream_bucket", ModFluids.JALAPENO_ICE_CREAM)
    val SCOOP_OF_ICE_CREAM = item("scoop_of_ice_cream")
    val SCOOP_OF_CHERRY_ICE_CREAM = item("scoop_of_cherry_ice_cream")
    val SCOOP_OF_CHOCOLATE_ICE_CREAM = item("scoop_of_chocolate_ice_cream")
    val POWSICLE = foodItez(
        "powsicle", stackSize = 16, food = {
            FoodProperties(nutrition = 8, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.OTHER, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )
    val LUAU_POPSICLE = foodItez(
        "luau_popsicle", stackSize = 16, food = {
            FoodProperties(
                nutrition = 12, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK
            )
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.OTHER,
            FoodType.C,
            foodEffects = listOf(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.ORIGINAL_EVOLUTION)
        )
    )
    val WATERMELON_POPSICLE = foodItez(
        "watermelon_popsicle",
        stackSize = 16,
        food = {
            FoodProperties(nutrition = 8, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.WATERMELON, FoodType.C, ModMobEffects.MELON_GRAVITY)
    )
    val PINK_SNOWMAN_POPSICLE = foodItez(
        "pink_snowman_popsicle", stackSize = 16, food = {
            FoodProperties(
                nutrition = 12, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK
            )
        }, fruitEffect = FruitEffectDataComponent(FoodIngredientType.OTHER, FoodType.C, ModMobEffects.BURSTING_BERRY)
    )
    val HEART_CRUSH_POPSICLE = foodItez(
        "heart_crush_popsicle", stackSize = 16, food = {
            FoodProperties(
                nutrition = 10, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK
            )
        }, fruitEffect = FruitEffectDataComponent(
            FoodIngredientType.OTHER, FoodType.C, ModMobEffects.CHERRY_BLOSSOMS_WEEPING
        )
    )
    val PINEAPPLE_POPSICLE = foodItem("pineapple_popsicle") {
        PineappleFoodItems.Popsicle(it.build(food = FoodProperties(nutrition = 10, saturationModifier = 0.2F)))
    }
    val CANTALOUPE_POPSICLE = foodItez(
        "cantaloupe_popsicle",
        stackSize = 16,
        food = {
            FoodProperties(nutrition = 8, saturationModifier = 0.2F, alwaysEdible = true, usingConvertsTo = Items.STICK)
        },
        fruitEffect = FruitEffectDataComponent(FoodIngredientType.CANTALOUPE, FoodType.C, ModMobEffects.MELON_GRAVITY)
    )
    val POM_BLOSSOM_POPSICLE = item("pom_blossom_popsicle")
    val KIWIFRUIT_POPSICLE = item("kiwifruit_popsicle")
    val HAWPSICLE = item("hawpsicle")
    val DRAGON_BLOOM_POPSICLE = item("dragon_bloom_popsicle")
    val GRAPE_TWIN_POPSICLE = item("grape_twin_popsicle")
    val MUSCAT_GRAPE_POPSICLE = item("muscat_grape_popsicle")
    val GRAPE_POPSICLE = item("grape_popsicle")
    val CHERRY_PIE: DeferredItem<Item> = ITEMS.registerSimpleItem("cherry_pie", Item.Properties())
    val CHERRY_PIE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("cherry_pie_slice", Item.Properties())
    val TRUFFLE_PIE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle_pie", Item.Properties())
    val TRUFFLE_PIE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle_pie_slice", Item.Properties())
    val PANCAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("pancake", Item.Properties())
    val DURIAN_MILLE_CREPE_CAKE = foodItem("durian_mille_crepe_cake") {
        DurianFoodItems.Cake(
            it.build(
                stackSize = 16, food = FoodProperties(nutrition = 8, saturationModifier = 0.3F, alwaysEdible = true)
            )
        )
    }
    val DURIAN_MILLE_CREPE_CAKE_SLICE = foodItem("durian_mille_crepe_cake_slice") {
        DurianFoodItems.Cake(
            it.build(
                stackSize = 16, food = FoodProperties(nutrition = 2, saturationModifier = 0.3F, alwaysEdible = true)
            )
        )
    }
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
    val FRIED_TEMPURA: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_tempura", Item.Properties())
    val FRIED_FISH: DeferredItem<Item> = ITEMS.registerSimpleItem("fried_fish", Item.Properties())
    val FRIED_ABALONE = item("fried_abalone")
    val BAKED_APPLE = item("baked_apple")
    val MINCE_PIE = item("mince_pie")
    val SPRINKLES = item("sprinkles")
    val CHOCOLATE: DeferredItem<Item> = ITEMS.registerSimpleItem("chocolate", Item.Properties())
    val WHITE_CHOCOLATE: DeferredItem<Item> = ITEMS.registerSimpleItem("white_chocolate", Item.Properties())
    val DARK_CHOCOLATE: DeferredItem<Item> = ITEMS.registerSimpleItem("dark_chocolate", Item.Properties())
    val CHOCOLATE_CHIPS = item("chocolate_chips")
    val WHITE_CHOCOLATE_CHIPS = item("white_chocolate_chips")
    val DARK_CHOCOLATE_CHIPS = item("dark_chocolate_chips")
    val DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("donut", Item.Properties())
    val CHOCOLATE_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("chocolate_donut", Item.Properties())
    val STRAWBERRY_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("strawberry_donut", Item.Properties())
    val WHITE_CHOCOLATE_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("white_chocolate_donut", Item.Properties())
    val LEMON_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon_donut", Item.Properties())
    val SNOWFLAKE_DONUT: DeferredItem<Item> = ITEMS.registerSimpleItem("snowflake_donut", Item.Properties())
    val CRANBERRY_COOKIE: DeferredItem<Item> = ITEMS.registerSimpleItem("cranberry_cookie", Item.Properties())
    val BLUEBERRY_COOKIE: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberry_cookie", Item.Properties())
    val LEMON_COOKIE: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon_cookie", Item.Properties())
    val CHERRY_COOKIE = item("cherry_cookie")
    val BUTTER_COOKIE = item("butter_cookie")
    val CHECKERBOARD_COOKIE = item("checkerboard_cookie")
    val HANAMI_DANGO: DeferredItem<Item> = ITEMS.registerSimpleItem("hanami_dango", Item.Properties())
    val SAKURA_MOCHI: DeferredItem<Item> = ITEMS.registerSimpleItem("sakura_mochi", Item.Properties())
    val COCA_COLA = item("coca_cola")
    val HOT_DOG = item("hot_dog")
    val NORI = item("nori")
    val ONIGIRI = item("onigiri")
    val TEMPURA_NIGIRI = item("tempura_nigiri")
    val SHRIMP_NIGIRI = item("shrimp_nigiri")
    val AVOCADO_NIGIRI = item("avocado_nigiri")
    val ABALONE_NIGIRI = item("abalone_nigiri")
    val CHEESE_PIZZA = item("cheese_pizza")
    val CHEESE_PIZZA_SLICE = item("cheese_pizza_slice")
    val PEPPERONI_PIZZA = item("pepperoni_pizza")
    val PEPPERONI_PIZZA_SLICE = item("pepperoni_pizza_slice")
    val SUPREME_PIZZA = item("supreme_pizza")
    val SUPREME_PIZZA_SLICE = item("supreme_pizza_slice")
    val SEAFOOD_PIZZA = item("seafood_pizza")
    val SEAFOOD_PIZZA_SLICE = item("seafood_pizza_slice")
    val DURIAN_PIZZA = item("durian_pizza")
    val DURIAN_PIZZA_SLICE = item("durian_pizza_slice")
    val HAWAIIAN_PIZZA = item("hawaiian_pizza")
    val HAWAIIAN_PIZZA_SLICE = item("hawaiian_pizza_slice")
    val BACON_AND_MUSHROOM_PIZZA = item("bacon_and_mushroom_pizza")
    val BACON_AND_MUSHROOM_PIZZA_SLICE = item("bacon_and_mushroom_pizza_slice")
    val HAWTHORN = item("hawthorn")
    val TANGHULU = item("tanghulu")
    val HAWTHORN_ROLL = item("hawthorn_roll")
    val SNOWFLAKE_HAWTHORN_BALLS = item("snowflake_hawthorn_balls")
    val GUILINGGAO_WITH_HAWTHORN = item("guilinggao_with_hawthorn")
    val HAW_FLAKES = item("haw_flakes")
    val GREEN_PEPPER = item("green_pepper")
    val JALAPENO: DeferredItem<Item> = ITEMS.registerSimpleItem("jalapeno", Item.Properties())
    val CORN: DeferredItem<Item> = ITEMS.registerSimpleItem("corn", Item.Properties())
    val CORN_KERNELS: DeferredItem<Item> = ITEMS.registerSimpleItem("corn_kernels", Item.Properties())
    val POPCORN = item("popcorn")
    val POPCORN_BUCKET = item("popcorn_bucket")
    val EMPTY_POPCORN_BUCKET = item("empty_popcorn_bucket")
    val SALTED_POPCORN_BUCKET = item("salted_popcorn_bucket")
    val EMPTY_SALTED_POPCORN_BUCKET = item("empty_salted_popcorn_bucket")
    val PINEAPPLE = blockItem(ModBlocks.PINEAPPLE)
    val PINEAPPLE_PLANTLET = blockItem(ModBlocks.PINEAPPLE_PLANTLET)
    val PINEAPPLE_SLICE = foodItem("pineapple_slice") {
        PineappleFoodItems.Slice(it.build(food = FoodProperties(nutrition = 3, saturationModifier = 0.3F)))
    }
    val COOKED_PINEAPPLE_SLICE = item("cooked_pineapple_slice")
    val PINEAPPLE_PIES = foodItem("pineapple_pies") {
        PineappleFoodItems.Pie(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.35F)))
    }
    val PINEAPPLE_CAKES = foodItem("pineapple_cakes") {
        PineappleFoodItems.Cakes(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.6F)))
    }
    val MUSANG_KING_DURIAN = item("musang_king_durian")
    val HALF_OF_DURIAN = foodItem("half_of_durian") {
        DurianFoodItems.Half(it.build(food = FoodProperties(nutrition = 5, saturationModifier = 0.3F)))
    }
    val DURIAN_FLESH = foodItem("durian_flesh") {
        DurianFoodItems.Flesh(it.build(food = FoodProperties(nutrition = 2, saturationModifier = 0.3F)))
    }
    val COOKED_DURIAN_FLESH = foodItem("cooked_durian_flesh") {
        DurianFoodItems.CookedFlesh(it.build(food = FoodProperties(nutrition = 4, saturationModifier = 0.3F)))
    }
    val DURIAN_PASTRY = foodItem("durian_pastry") {
        DurianFoodItems.Pastry(
            it.build(food = FoodProperties(nutrition = 6, saturationModifier = 0.35F, alwaysEdible = true))
        )
    }
    val CANTALOUPE: DeferredItem<Item> = ITEMS.registerSimpleItem("cantaloupe", Item.Properties())
    val CANTALOUPE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("cantaloupe_slice", Item.Properties())
    val CANTALOUPE_SEEDS: DeferredItem<Item> = ITEMS.registerSimpleItem("cantaloupe_seeds", Item.Properties())
    val BUNCH_OF_BANANAS = item("bunch_of_bananas")
    val BANANA = item("banana")
    val BANANA_SLICE = item("banana_slice")
    val BANANA_CHIPS = item("banana_chips")
    val COOKED_BANANA = item("cooked_banana")
    val FRIED_BANANA = item("fried_banana")
    val CHOCOLATE_BANANA_POP = item("chocolate_banana_pop")
    val BANANA_FLOWER = item("banana_flower")
    val BANANA_LEAF = item("banana_leaf")
    val STARFRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("starfruit", Item.Properties())
    val STARFRUIT_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("starfruit_slice", Item.Properties())
    val MANGO: DeferredItem<Item> = ITEMS.registerSimpleItem("mango", Item.Properties())
    val MANGO_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("mango_slice", Item.Properties())
    val KIWIFRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("kiwifruit", Item.Properties())
    val HALF_OF_KIWIFRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("half_of_kiwifruit", Item.Properties())
    val KIWIFRUIT_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("kiwifruit_slice", Item.Properties())
    val GOLDEN_KIWIFRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("golden_kiwifruit", Item.Properties())
    val HALF_OF_GOLDEN_KIWIFRUIT: DeferredItem<Item> =
        ITEMS.registerSimpleItem("half_of_golden_kiwifruit", Item.Properties())
    val GOLDEN_KIWIFRUIT_SLICE: DeferredItem<Item> =
        ITEMS.registerSimpleItem("golden_kiwifruit_slice", Item.Properties())
    val AVOCADO: DeferredItem<Item> = ITEMS.registerSimpleItem("avocado", Item.Properties())
    val HALF_OF_AVOCADO: DeferredItem<Item> = ITEMS.registerSimpleItem("half_of_avocado", Item.Properties())
    val AVOCADO_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("avocado_slice", Item.Properties())
    val BAKED_AVOCADO_WITH_EGG = item("baked_avocado_with_egg")
    val ORANGE: DeferredItem<Item> = ITEMS.registerSimpleItem("orange", Item.Properties())
    val ORANGE_SEGMENT: DeferredItem<Item> = ITEMS.registerSimpleItem("orange_segment", Item.Properties())
    val GRAPEFRUIT = item("grapefruit")
    val GRAPEFRUIT_SLICE = item("grapefruit_slice")
    val LEMON: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon", Item.Properties())
    val LEMON_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("lemon_slice", Item.Properties())
    val LIME: DeferredItem<Item> = ITEMS.registerSimpleItem("lime", Item.Properties())
    val LIME_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("lime_slice", Item.Properties())
    val POMELO = item("pomelo")
    val POMELO_WEDGE = item("pomelo_wedge")
    val APPLE_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("apple_slice", Item.Properties())
    val GREEN_APPLE = item("green_apple")
    val GREEN_APPLE_SLICE = item("green_apple_slice")
    val PEACH: DeferredItem<Item> = ITEMS.registerSimpleItem("peach", Item.Properties())
    val PEACH_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("peach_slice", Item.Properties())
    val PEAR: DeferredItem<Item> = ITEMS.registerSimpleItem("pear", Item.Properties())
    val PEAR_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("pear_slice", Item.Properties())
    val POMEGRANATE: DeferredItem<Item> = ITEMS.registerSimpleItem("pomegranate", Item.Properties())
    val POMEGRANATE_SEGMENT: DeferredItem<Item> = ITEMS.registerSimpleItem("pomegranate_segment", Item.Properties())
    val POMEGRANATE_ARILS = blockItem(ModBlocks.POMEGRANATE_SEEDS)
    val FIG = item("fig")
    val FIG_SLICE = item("fig_slice")
    val FIGGY_PUDDING = item("figgy_pudding")
    val GRAPE = item("grape")
    val MUSCAT_GRAPE = item("muscat_grape")
    val RAISINS = item("raisins")
    val DRAGON_FRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("dragon_fruit", Item.Properties())
    val DRAGON_FRUIT_SLICE: DeferredItem<Item> = ITEMS.registerSimpleItem("dragon_fruit_slice", Item.Properties())
    val DRAGON_FRUIT_CACTUS = blockItem(ModBlocks.DRAGON_FRUIT_CACTUS)
    val DRAGON_FRUIT_CACTUS_FLOWER = blockItem(ModBlocks.DRAGON_FRUIT_CACTUS_FLOWER)
    val STRAWBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("strawberry", Item.Properties())
    val PINEBERRY: DeferredItem<Item> = ITEMS.registerSimpleItem("pineberry", Item.Properties())
    val BLUEBERRIES: DeferredItem<Item> = ITEMS.registerSimpleItem("blueberries", Item.Properties())
    val CRANBERRIES: DeferredItem<Item> = ITEMS.registerSimpleItem("cranberries", Item.Properties())
    val CHERRIES: DeferredItem<Item> = ITEMS.registerSimpleItem("cherries", Item.Properties())
    val OLIVE_FRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("olive_fruit", Item.Properties())
    val BLACK_OLIVE_FRUIT: DeferredItem<Item> = ITEMS.registerSimpleItem("black_olive_fruit", Item.Properties())
    val ALFALFA: DeferredItem<Item> = ITEMS.registerSimpleItem("alfalfa", Item.Properties())
    val ALFALFA_SEEDS: DeferredItem<Item> = ITEMS.registerSimpleItem("alfalfa_seeds", Item.Properties())
    val ACORN: DeferredItem<Item> = ITEMS.registerSimpleItem("acorn", Item.Properties())
    val FILBERT: DeferredItem<Item> = ITEMS.registerSimpleItem("filbert", Item.Properties())
    val TRUFFLE: DeferredItem<Item> = ITEMS.registerSimpleItem("truffle", Item.Properties())
    val GARLIC: DeferredItem<Item> = ITEMS.registerSimpleItem("garlic", Item.Properties())
    val BUTTON_MUSHROOM: DeferredItem<Item> = ITEMS.registerSimpleItem("button_mushroom", Item.Properties())
    val COOKED_BUTTON_MUSHROOM: DeferredItem<Item> =
        ITEMS.registerSimpleItem("cooked_button_mushroom", Item.Properties())
    val SHIITAKE: DeferredItem<Item> = ITEMS.registerSimpleItem("shiitake", Item.Properties())
    val RAW_PRAWN: DeferredItem<Item> = ITEMS.registerSimpleItem("raw_prawn", Item.Properties())
    val COOKED_PRAWN: DeferredItem<Item> = ITEMS.registerSimpleItem("cooked_prawn", Item.Properties())
    val ABALONE = item("abalone")
    val COOKED_ABALONE = item("cooked_abalone")
    val FLOWER_RING: DeferredItem<Item> = ITEMS.registerSimpleItem("flower_ring", Item.Properties())
    val WITCH_HAT: DeferredItem<Item> = ITEMS.registerSimpleItem("witch_hat", Item.Properties())
    val TOQUE: DeferredItem<Item> = ITEMS.registerSimpleItem("toque", Item.Properties())
    val CHRISTMAS_HAT: DeferredItem<Item> = ITEMS.registerSimpleItem("christmas_hat", Item.Properties())
    val MINER_HAT: DeferredItem<Item> = ITEMS.registerSimpleItem("miner_hat", Item.Properties())
    val CHRISTMAS_SCARF: DeferredItem<Item> = ITEMS.registerSimpleItem("christmas_scarf", Item.Properties())
    val CHRISTMAS_WREATHS = blockItem(ModBlocks.CHRISTMAS_WREATHS)
    val CHRISTMAS_STOCKING = blockItem(ModBlocks.CHRISTMAS_STOCKING)
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
    val SNOWY_BLACK_DIRT = blockItem(ModBlocks.SNOWY_BLACK_DIRT)
    val BLACK_DIRT = blockItem(ModBlocks.BLACK_DIRT)
    val BLACK_DIRT_WITH_TRUFFLE = blockItem(ModBlocks.BLACK_DIRT_WITH_TRUFFLE)
    val BLACK_RICH_SOIL = blockItem(ModBlocks.RICH_BLACK_DIRT)
    val BLACK_RICH_SOIL_FARMLAND = blockItem(ModBlocks.RICH_BLACK_DIRT_FARMLAND)
    val SNOWY_MYCELIUM = blockItem(ModBlocks.SNOWY_MYCELIUM)
    val VOLCANIC_MUD = blockItem(ModBlocks.VOLCANIC_MUD)
    val BUTTON_MUSHROOM_BLOCK = blockItem(ModBlocks.BUTTON_MUSHROOM_BLOCK)
    val FRIGID_GRASS = blockItem(ModBlocks.FRIGID_GRASS)
    val LAVENDER = blockItem(ModBlocks.LAVENDER)
    val ALFALFA_PLANT = blockItem(ModBlocks.ALFALFA_PLANT)
    val WINTER_ROSE = blockItem(ModBlocks.WINTER_ROSE)
    val SNOWDROP = blockItem(ModBlocks.SNOWDROP)
    val AMUR_ADONIS = blockItem(ModBlocks.AMUR_ADONIS)
    val HYACINTH = blockItem(ModBlocks.HYACINTH)
    val BLUE_TULIP = blockItem(ModBlocks.BLUE_TULIP)
    val SEMPER_AUGUSTUS = blockItem(ModBlocks.SEMPER_AUGUSTUS)
    val SCOTS_PINE_SAPLING = blockItem(ModBlocks.SCOTS_PINE_SAPLING)
    val CHERRY_SEEDS = blockItem(ModBlocks.CHERRY_SEEDS)
    val STARFRUIT_SEEDS = blockItem(ModBlocks.STARFRUIT_SEEDS)
    val OLIVE_SEED = blockItem(ModBlocks.OLIVE_SEED)
    val ORANGE_SEEDS = blockItem(ModBlocks.ORANGE_SEEDS)
    val APPLE_SEEDS = blockItem(ModBlocks.APPLE_SEEDS)
    val PEACH_SEED = blockItem(ModBlocks.PEACH_SEED)
    val DURIAN_SEED = blockItem(ModBlocks.DURIAN_SEED)
    val LEMON_SEEDS = blockItem(ModBlocks.LEMON_SEEDS)
    val PEAR_SEEDS = blockItem(ModBlocks.PEAR_SEEDS)
    val MANGO_SEED = blockItem(ModBlocks.MANGO_SEED)
    val KIWIFRUIT_SEEDS = blockItem(ModBlocks.KIWIFRUIT_SEEDS)
    val AVOCADO_SEED = blockItem(ModBlocks.AVOCADO_SEED)
    val HAWTHORN_SEEDS = blockItem(ModBlocks.HAWTHORN_SEEDS)
    val BANANA_STEM = blockItem(ModBlocks.BANANA_STEM)
    val GRAPE_SEEDS = blockItem(ModBlocks.GRAPE_SEEDS)
    val POMELO_SEEDS = blockItem(ModBlocks.POMELO_SEEDS)
    val FIG_SEEDS = blockItem(ModBlocks.FIG_SEEDS)
    val PERLITE_ORE = blockItem(ModBlocks.PERLITE_ORE)
    val DEEPSLATE_PERLITE_ORE = blockItem(ModBlocks.DEEPSLATE_PERLITE_ORE)
    val DEBRISHROOM = blockItem(ModBlocks.DEBRISHROOM)

//    // === Leaves ===
//    val SCOTS_PINE_LEAVES = blockItem(ModBlocks.SCOTS_PINE_LEAVES)
//    val ALPINE_TREE_LEAVES = blockItem(ModBlocks.ALPINE_TREE_LEAVES)
//    val CHERRY_TREE_LEAVES = blockItem(ModBlocks.CHERRY_TREE_LEAVES)
//    val STARFRUIT_TREE_LEAVES = blockItem(ModBlocks.STARFRUIT_TREE_LEAVES)
//    val OLIVE_TREE_LEAVES = blockItem(ModBlocks.OLIVE_TREE_LEAVES)
//    val ORANGE_LEAVES = blockItem(ModBlocks.ORANGE_LEAVES)
//    val PEACH_LEAVES = blockItem(ModBlocks.PEACH_LEAVES)
//    val PEAR_LEAVES = blockItem(ModBlocks.PEAR_LEAVES)
//    val APPLE_LEAVES = blockItem(ModBlocks.APPLE_LEAVES)
//    val MANGO_TREE_LEAVES = blockItem(ModBlocks.MANGO_TREE_LEAVES)
//    val KIWIFRUIT_TREE_LEAVES = blockItem(ModBlocks.KIWIFRUIT_TREE_LEAVES)
//    val AVOCADO_TREE_LEAVES = blockItem(ModBlocks.AVOCADO_TREE_LEAVES)

    // === Saplings & Seeds ===

//
//    // === Snow Color Blocks ===
//    val SNOW_TERRACOTTA = blockItem(ModBlocks.SNOW_TERRACOTTA)
//    val SNOW_GLAZED_TERRACOTTA = blockItem(ModBlocks.SNOW_GLAZED_TERRACOTTA)
//    val SNOW_CONCRETE = blockItem(ModBlocks.SNOW_CONCRETE)
//    val SNOW_CONCRETE_POWDER = blockItem(ModBlocks.SNOW_CONCRETE_POWDER)
//    val SNOW_GLASS = blockItem(ModBlocks.SNOW_GLASS)
//    val SNOW_GLASS_PANE = blockItem(ModBlocks.SNOW_GLASS_PANE)

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