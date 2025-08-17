package com.djinfinite.manors_bounty.data

import com.djinfinite.manors_bounty.content.fryer.FryerRecipeBuilder
import com.djinfinite.manors_bounty.registry.ModBlocks
import com.djinfinite.manors_bounty.registry.ModItems
import com.djinfinite.manors_bounty.res
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.data.recipes.RecipeCategory.FOOD
import net.minecraft.data.recipes.RecipeCategory.MISC
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.conditions.ModLoadedCondition
import net.neoforged.neoforge.common.conditions.NotCondition
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab
import vectorwing.farmersdelight.common.tag.CommonTags
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder
import java.util.concurrent.CompletableFuture

private val ItemLike.ingredient: Ingredient
    get() = Ingredient.of(this)

class ModRecipesProvider(output: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    RecipeProvider(output, lookupProvider) {

    override fun buildRecipes(output: RecipeOutput) {
//        shapedPaleteria(ModItems.PINEAPPLE_PALETERIA, ModItems.PINEAPPLE_SLICE, "has_pineapple_slice").save(output)
//        shapedPastries(ModItems.PINEAPPLE_PASTRIES, ModItems.PINEAPPLE_SLICE, "has_pineapple_slice").save(output)
//        cutSlice(ModItems.PINEAPPLE_SLICE, 4, ModItems.PINEAPPLE, "has_pineapple", ModItems.PINEAPPLE_CROP, 1).save(
//            output
//        )
//        cookJuice(ModItems.PINEAPPLE_JUICE, ModItems.PINEAPPLE_SLICE).save(output)

        val createLoaded = ModLoadedCondition("create")
        val createNotLoaded = NotCondition(createLoaded)

        //TODO: remove the test
        FryerRecipeBuilder(
            ModItems.PINEAPPLE.ingredient, ModItems.PINEAPPLE_CAKES, false
        ).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE)).save(output, res("fryer_pineapple"))

        FryerRecipeBuilder(
            ModItems.RAW_PRAWN.ingredient, ModItems.COOKED_PRAWN, true
        ).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE)).save(output, res("fryer_prawn"))

        val blasting: (Ingredient, RecipeCategory, ItemLike, Float, Int) -> SimpleCookingRecipeBuilder =
            SimpleCookingRecipeBuilder::blasting
        val smelting: (Ingredient, RecipeCategory, ItemLike, Float, Int) -> SimpleCookingRecipeBuilder =
            SimpleCookingRecipeBuilder::smelting
        val shaped: (RecipeCategory, ItemLike) -> ShapedRecipeBuilder = ShapedRecipeBuilder::shaped

        // Pearl Rock
        blasting(
            ModBlocks.PERLITE_ORE.ingredient, MISC, ModItems.PERLITE, 1F, 100
        ).unlockedBy("has_pearl_rock_ore", has(ModItems.PEARL_ROCK_ORE)).save(
            output, res("pearl_rock_from_blasting_pearl_rock_ore")
        )
        smelting(
            ModBlocks.PERLITE_ORE.ingredient, MISC, ModItems.PERLITE, 1F, 200
        ).unlockedBy("has_pearl_rock_ore", has(ModItems.PEARL_ROCK_ORE)).save(
            output, res("pearl_rock_from_smelting_pearl_rock_ore")
        )
        blasting(
            ModBlocks.DEEPSLATE_PERLITE_ORE.ingredient, MISC, ModItems.PERLITE, 1F, 200
        ).unlockedBy("has_deepslate_pearl_rock_ore", has(ModItems.DEEPSLATE_PERLITE_ORE)).save(
            output, res("pearl_rock_from_blasting_deepslate_pearl_rock_ore")
        )
        smelting(
            ModBlocks.DEEPSLATE_PERLITE_ORE.ingredient, MISC, ModItems.PERLITE, 1F, 200
        ).unlockedBy("has_deepslate_pearl_rock_ore", has(ModItems.DEEPSLATE_PERLITE_ORE)).save(
            output, res("pearl_rock_from_smelting_deepslate_pearl_rock_ore")
        )

        // Snow Dye
        shapeless(
            MISC,
            ModItems.SNOW_DYE,
            4,
            "has_light_blue_dye" to Items.LIGHT_BLUE_DYE,
            arrayOf(Items.WHITE_DYE, Items.WHITE_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_BLUE_DYE),
        ).save(output)

        // Lavender Dye
        shapeless(
            MISC,
            ModItems.LAVENDER_DYE,
            1,
            "has_lavender" to ModItems.LAVENDER,
            arrayOf(ModItems.LAVENDER),
        ).save(output)

        // Turquoise Dye
        smelting(
            ModItems.DRAGON_FRUIT_CACTUS.ingredient, MISC, ModItems.TURQUOISE_DYE, 1F, 200
        ).unlockedBy("has_dragon_fruit_cactus", has(ModItems.DRAGON_FRUIT_CACTUS)).save(output)

        // Super Ancient Mushroom
        smelting(
            ModItems.DEBRISHROOM.ingredient, MISC, ModItems.SUPER_ANCIENT_MUSHROOM, 1F, 300
        ).unlockedBy("has_debrishroom", has(ModItems.DEBRISHROOM)).save(output)

        // Creativity Mechanism
        shaped(MISC, ModItems.CREATIVITY_MECHANISM).pattern(
            "IPI",
            "IDI",
            " I ",
        ).define('I' to Items.IRON_INGOT, 'D' to Items.DIAMOND, 'P' to Items.PINK_DYE)
            .unlockedBy("has_diamond", has(Items.DIAMOND)).save(output.withConditions(createNotLoaded))

        val hasCreativityMechanism = "has_creativity_mechanism" to ModItems.CREATIVITY_MECHANISM
        // Ice Cream Machine
        shaped(MISC, ModItems.ICE_CREAM_MACHINE, hasCreativityMechanism).pattern(
            "IPI",
            "ICI",
            "IEI",
        ).define(
            'I' to Items.IRON_INGOT,
            'C' to ModItems.CREATIVITY_MECHANISM,
            'P' to Items.PINK_DYE,
            'E' to Items.ICE,
        ).save(output)

        // Fryer
        shaped(MISC, ModItems.FRYER, hasCreativityMechanism).pattern(
            "ILI",
            "ICI",
            "IBI",
        ).define(
            'I' to Items.IRON_INGOT,
            'C' to ModItems.CREATIVITY_MECHANISM,
            'L' to Items.LIGHT_GRAY_DYE,
            'B' to Items.BUCKET
        ).save(output)

        // Oven
        shaped(MISC, ModItems.OVEN, hasCreativityMechanism).pattern(
            "IWI",
            "ICI",
            "IMI",
        ).define(
            'I' to Items.IRON_INGOT,
            'C' to ModItems.CREATIVITY_MECHANISM,
            'W' to Items.WHITE_DYE,
            'M' to Items.MAGMA_BLOCK
        ).save(output)

        // Butter
        shaped(FOOD, ModItems.BUTTER, "has_boxed_milk" to ModItems.BOXED_MILK, count = 3).pattern(
            "   ",
            "BBB",
            "   ",
        ).define('B' to ModItems.BOXED_MILK).save(output)

        // Cake
        shapeless(
            FOOD,
            Items.CAKE,
            1,
            "has_vanilla_cake_slice" to ModItems.VANILLA_CAKE_SLICE,
            arrayOf(
                ModItems.VANILLA_CAKE_SLICE,
                ModItems.VANILLA_CAKE_SLICE,
                ModItems.VANILLA_CAKE_SLICE,
                ModItems.VANILLA_CAKE_SLICE
            ),
        ).save(output, res("cake_from_vanilla_cake_slice"))

        // Vanilla Cake Slice
        cutting(ModItems.VANILLA_CAKE_SLICE, "has_cake" to Items.CAKE, Items.CAKE, count = 4).save(output)

        // Caramel Chocolate Cake
        shapeless(
            FOOD,
            ModItems.CARAMEL_CHOCOLATE_CAKE,
            1,
            "has_caramel_chocolate_cake_slice" to ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE,
            arrayOf(
                ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE,
                ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE,
                ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE,
                ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE
            ),
        ).save(output, res("caramel_chocolate_cake_from_caramel_chocolate_cake_slice"))
        shaped(
            FOOD,
            ModItems.CARAMEL_CHOCOLATE_CAKE,
            "has_dark_chocolate_chips" to ModItems.DARK_CHOCOLATE_CHIPS,
        ).pattern(
            "DDD",
            "SES",
            "WWW",
        ).define(
            'D' to ModItems.DARK_CHOCOLATE_CHIPS,
            'S' to Items.SUGAR,
            'W' to Items.WHEAT,
        ).defineTags('E' to Tags.Items.EGGS).save(output)

        // Caramel Chocolate Cake Slice
        cutting(
            ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE,
            "has_caramel_chocolate_cake" to ModItems.CARAMEL_CHOCOLATE_CAKE,
            ModItems.CARAMEL_CHOCOLATE_CAKE,
            count = 4
        ).save(output)

        // Sweet Berry Cake
        shapeless(
            FOOD,
            ModItems.SWEET_BERRY_ICE_CREAM_CAKE,
            1,
            "has_sweet_berry_cake_slice" to ModItems.SWEET_BERRY_ICE_CREAM_CAKE_SLICE,
            arrayOf(
                ModItems.SWEET_BERRY_ICE_CREAM_CAKE_SLICE,
                ModItems.SWEET_BERRY_ICE_CREAM_CAKE_SLICE,
                ModItems.SWEET_BERRY_ICE_CREAM_CAKE_SLICE,
                ModItems.SWEET_BERRY_ICE_CREAM_CAKE_SLICE
            ),
        ).save(output, res("sweet_berry_cake_from_sweet_berry_cake_slice"))
        shaped(
            FOOD,
            ModItems.SWEET_BERRY_ICE_CREAM_CAKE,
            "has_sweet_berry" to Items.SWEET_BERRIES,
        ).pattern(
            "BBB",
            "SES",
            "III",
        ).define(
            'B' to ModItems.BOXED_MILK,
            'S' to Items.SWEET_BERRIES,
            'I' to ModItems.SCOOP_OF_ICE_CREAM,
        ).defineTags('E' to Tags.Items.EGGS).save(output)

        // Sweet Berry Cake Slice
        cutting(
            ModItems.SWEET_BERRY_ICE_CREAM_CAKE_SLICE,
            "has_sweet_berry_cake" to ModItems.SWEET_BERRY_ICE_CREAM_CAKE,
            ModItems.SWEET_BERRY_ICE_CREAM_CAKE,
            count = 4
        ).save(output)

        // Nether Wart Soul Cake
        shapeless(
            FOOD,
            ModItems.NETHER_WART_SOUL_CAKE,
            1,
            "has_nether_wart_soul_cake_slice" to ModItems.NETHER_WART_SOUL_CAKE_SLICE,
            arrayOf(
                ModItems.NETHER_WART_SOUL_CAKE_SLICE,
                ModItems.NETHER_WART_SOUL_CAKE_SLICE,
                ModItems.NETHER_WART_SOUL_CAKE_SLICE,
                ModItems.NETHER_WART_SOUL_CAKE_SLICE
            ),
        ).save(output, res("nether_wart_soul_cake_from_nether_wart_soul_cake_slice"))
        shaped(
            FOOD,
            ModItems.NETHER_WART_SOUL_CAKE,
            "has_nether_wart" to Items.NETHER_WART,
        ).pattern(
            "BNB",
            "SMS",
            "III",
        ).define(
            'B' to Items.BLAZE_POWDER,
            'N' to Items.NETHER_SPROUTS,
            'S' to Items.NETHER_WART,
            'M' to Items.MAGMA_CREAM,
            'I' to Items.SOUL_SOIL,
        ).save(output)

        // Nether Wart Soul Cake Slice
        cutting(
            ModItems.NETHER_WART_SOUL_CAKE_SLICE,
            "has_nether_wart_soul_cake" to ModItems.NETHER_WART_SOUL_CAKE,
            ModItems.NETHER_WART_SOUL_CAKE,
            count = 4
        ).save(output)

        // Chorus Flower Jelly Cake
        shapeless(
            FOOD,
            ModItems.CHORUS_FLOWER_JELLY_CAKE,
            1,
            "has_chorus_flower_jelly_cake_slice" to ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE,
            arrayOf(
                ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE,
                ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE,
                ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE,
                ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE
            ),
        ).save(output, res("chorus_flower_jelly_cake_from_chorus_flower_jelly_cake_slice"))
        shaped(
            FOOD,
            ModItems.CHORUS_FLOWER_JELLY_CAKE,
            "has_chorus_fruit" to Items.CHORUS_FRUIT,
        ).pattern(
            "CCC",
            "SBS",
            "FFF",
        ).define(
            'C' to Items.CHORUS_FLOWER,
            'S' to Items.SUGAR,
            'B' to Items.SLIME_BALL,
            'F' to Items.CHORUS_FRUIT,
        ).save(output)

        // Chorus Flower Jelly Cake Slice
        cutting(
            ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE,
            "has_chorus_flower_jelly_cake" to ModItems.CHORUS_FLOWER_JELLY_CAKE,
            ModItems.CHORUS_FLOWER_JELLY_CAKE,
            count = 4
        ).save(output)

        // Sausage
        shapeless(
            FOOD,
            ModItems.SAUSAGE,
            3,
            "has_cooked_porkchop" to Items.COOKED_PORKCHOP,
            arrayOf(Items.COOKED_PORKCHOP, ModItems.JALAPENO, Items.SUGAR),
        ).save(output)

        // Emmental Cheese Wheel
        shapeless(
            FOOD, ModItems.EMMENTAL_CHEESE_WHEEL, 2, "has_boxed_milk" to ModItems.BOXED_MILK,
            arrayOf(ModItems.BOXED_MILK, ModItems.BOXED_MILK, ModItems.SUPER_ANCIENT_MUSHROOM, Items.DRAGON_BREATH),
        ).save(output)

        // Emmental Cheese Wheel Wedge
        cutting(
            ModItems.EMMENTAL_CHEESE_WEDGE,
            "has_emmental_cheese_wheel" to ModItems.EMMENTAL_CHEESE_WHEEL,
            ModItems.EMMENTAL_CHEESE_WHEEL,
            count = 4
        ).save(output)

        // Emmental Cheese Wheel Slice
        cutting(
            ModItems.EMMENTAL_CHEESE_SLICE,
            "has_emmental_cheese_wedge" to ModItems.EMMENTAL_CHEESE_WEDGE,
            ModItems.EMMENTAL_CHEESE_WEDGE,
            count = 3
        ).save(output)

        // Cheddar Cheese Wheel
        shapeless(
            FOOD, ModItems.CHEDDAR_CHEESE_WHEEL, 2, "has_boxed_milk" to ModItems.BOXED_MILK,
            arrayOf(ModItems.BOXED_MILK, ModItems.BOXED_MILK, ModItems.SUPER_ANCIENT_MUSHROOM, Items.MAGMA_CREAM),
        ).save(output)

        // Cheddar Cheese Wheel Wedge
        cutting(
            ModItems.CHEDDAR_CHEESE_WEDGE,
            "has_cheddar_cheese_wheel" to ModItems.CHEDDAR_CHEESE_WHEEL,
            ModItems.CHEDDAR_CHEESE_WHEEL,
            count = 4
        ).save(output)

        // Cheddar Cheese Wheel Slice
        cutting(
            ModItems.CHEDDAR_CHEESE_SLICE,
            "has_cheddar_cheese_wedge" to ModItems.CHEDDAR_CHEESE_WEDGE,
            ModItems.CHEDDAR_CHEESE_WEDGE,
            count = 3
        ).save(output)

        // Mozzarella Cheese Wheel
        shapeless(
            FOOD, ModItems.MOZZARELLA_CHEESE_WHEEL, 2, "has_boxed_milk" to ModItems.BOXED_MILK,
            arrayOf(ModItems.BOXED_MILK, ModItems.BOXED_MILK, ModItems.SUPER_ANCIENT_MUSHROOM, Items.SLIME_BALL),
        ).save(output)

        // Mozzarella Cheese Wheel Wedge
        cutting(
            ModItems.MOZZARELLA_CHEESE_WEDGE,
            "has_mozzarella_cheese_wheel" to ModItems.MOZZARELLA_CHEESE_WHEEL,
            ModItems.MOZZARELLA_CHEESE_WHEEL,
            count = 4
        ).save(output)

        // Mozzarella Cheese Wheel Slice
        cutting(
            ModItems.MOZZARELLA_CHEESE_SLICE,
            "has_mozzarella_cheese_wedge" to ModItems.MOZZARELLA_CHEESE_WEDGE,
            ModItems.MOZZARELLA_CHEESE_WEDGE,
            count = 3
        ).save(output)

        // Marble Bowl
        shaped(MISC, ModItems.MARBLE_BOWL, "has_polished_marble" to ModItems.POLISHED_MARBLE, count = 4).pattern(
            "M M",
            " M ",
            "   ",
        ).define('M' to ModItems.POLISHED_MARBLE).save(output)

        // Cosmic Dragon Salad
        shapeless(
            FOOD, ModItems.COSMIC_DRAGON_SALAD, 1, "has_dragon_fruit_slice" to ModItems.DRAGON_FRUIT_SLICE,
            arrayOf(
                ModItems.MARBLE_BOWL,
                ModItems.STARFRUIT_SLICE,
                ModItems.STARFRUIT_SLICE,
                ModItems.DRAGON_FRUIT_SLICE,
                ModItems.DRAGON_FRUIT_SLICE,
                Items.HONEY_BOTTLE
            ),
        ).save(output)

        // Rosa Salad
        shapeless(
            FOOD, ModItems.ROSA_SALAD, 1, "has_apple_slice" to ModItems.APPLE_SLICE,
            arrayOf(
                ModItems.MARBLE_BOWL,
                ModItems.APPLE_SLICE,
                ModItems.PEACH_SLICE,
                ModItems.PEAR_SLICE,
                Items.HONEY_BOTTLE
            ),
        ).save(output)

        // Sea Pearl Salad
        shapeless(
            FOOD, ModItems.OCEANIC_PEARL_SALAD, 1, "has_kelp" to Items.KELP,
            arrayOf(
                ModItems.MARBLE_BOWL,
                Items.KELP,
                Items.KELP,
                ModItems.COOKED_PRAWN,
                Items.GLOW_BERRIES,
                ModItems.BOTTLED_OLIVE_OIL
            ),
        ).save(output)

        // Melon Salad
        shapeless(
            FOOD, ModItems.MELON_SALAD, 1, "has_melon_slice" to Items.MELON_SLICE,
            arrayOf(
                ModItems.MARBLE_BOWL,
                Items.MELON_SLICE,
                Items.MELON_SLICE,
                ModItems.HAMI_MELON_SLICE,
                ModItems.HAMI_MELON_SLICE,
                Items.HONEY_BOTTLE
            ),
        ).save(output)

        // Jewellery Box Salad
        shapeless(
            FOOD, ModItems.JEWELERY_BOX_SALAD, 1, "has_pomegranate_seeds" to ModItems.POMEGRANATE_SEEDS,
            arrayOf(
                ModItems.MARBLE_BOWL,
                ModItems.POMEGRANATE_SEEDS,
                ModItems.POMEGRANATE_SEEDS,
                ModItems.CRANBERRIES,
                ModItems.GOLD_KIWIFRUIT_SLICE,
                Items.HONEY_BOTTLE
            ),
        ).save(output)

        // Primary Colors Salad
        shapeless(
            FOOD, ModItems.PRIMARY_COLORS_SALAD, 1, "has_strawberry" to ModItems.STRAWBERRY,
            arrayOf(
                ModItems.MARBLE_BOWL,
                ModItems.STRAWBERRY,
                ModItems.STRAWBERRY,
                ModItems.BLUEBERRIES,
                ModItems.MANGO_SLICE,
                ModItems.KIWIFRUIT_SLICE,
            ),
        ).save(output)

        // Deformed Glass Bottle
        shapeless(
            MISC, ModItems.DEFORMABLE_GLASS_BOTTLE, 1, "has_glass_bottle" to Items.GLASS_BOTTLE, arrayOf(
                Items.GLASS_BOTTLE, Items.BONE_MEAL
            )
        ).save(output)

        // Boxed Lemon Tea
        shaped(FOOD, ModItems.BOXED_LEMON_TEA, "has_lemonade" to ModItems.LEMONADE, count = 1).pattern(
            " L ",
            " M ",
            " P ",
        ).defineTags('L' to ItemTags.LEAVES).define('M' to ModItems.LEMONADE, 'P' to Items.PAPER).save(output)

        // Boxed Milk
        shapeless(
            FOOD,
            ModItems.BOXED_MILK,
            4,
            "has_milk_bucket" to Items.MILK_BUCKET,
            arrayOf(Items.MILK_BUCKET, Items.PAPER, Items.PAPER, Items.PAPER, Items.PAPER)
        ).save(output)

        // Boxed Whipping Cream
        shaped(FOOD, ModItems.BOXED_WHIPPING_CREAM, "has_butter" to ModItems.BUTTER, count = 3).pattern(
            "MBM",
            "PPP",
            "   ",
        ).define('M' to ModItems.BOXED_MILK, 'B' to ModItems.BUTTER, 'P' to Items.PAPER).save(output)

        // Bottle Olive Oil
        shapeless(
            FOOD, ModItems.BOTTLED_OLIVE_OIL, 2, "has_olive_oil_bucket" to ModItems.OLIVE_OIL_BUCKET, arrayOf(
                ModItems.OLIVE_OIL_BUCKET, ModItems.DEFORMABLE_GLASS_BOTTLE, ModItems.DEFORMABLE_GLASS_BOTTLE
            )
        ).save(output)

        // Lavender Essential Oil
        shaped(MISC, ModItems.LAVENDER_ESSENTIAL_OIL, "has_lavender" to ModItems.LAVENDER).pattern(
            " L ",
            "NPN",
            "NDN",
        ).define(
            'L' to ModItems.LAVENDER,
            'N' to Items.NETHERITE_SCRAP,
            'P' to ModItems.PERLITE,
            'D' to Items.DRAGON_BREATH
        ).save(output)

        // Chorus Chrome
        shapeless(
            FOOD,
            ModItems.CHORUS_ALLOY,
            3,
            "has_chorus_fruit" to Items.CHORUS_FRUIT,
            arrayOf(Items.CHORUS_FRUIT, Items.CHORUS_FRUIT, Items.SUGAR, Items.HONEY_BOTTLE)
        ).save(output)

        // Ice Cream Cone
        shaped(FOOD, ModItems.ICE_CREAM_CONE, "has_wheat" to Items.WHEAT, count = 4).pattern(
            " W ",
            "W W",
            "WW ",
        ).define('W' to Items.WHEAT).save(output)

        // Vanilla Ice Cream Bucket
        shapeless(
            FOOD, ModItems.VANILLA_ICE_CREAM_BUCKET, 1, "has_milk_bucket" to Items.MILK_BUCKET, arrayOf(
                Items.SUGAR, Items.SUGAR, Items.SNOWBALL, Items.SNOWBALL, Items.MILK_BUCKET, Items.BUCKET
            )
        ).save(output)

        // Scoop of Ice Cream
        shapeless(
            FOOD,
            ModItems.SCOOP_OF_ICE_CREAM,
            4,
            "has_vanilla_ice_cream_bucket" to ModItems.VANILLA_ICE_CREAM_BUCKET,
            arrayOf(ModItems.VANILLA_ICE_CREAM_BUCKET)
        )

        // Scoop of Cherry Ice Cream
        shapeless(
            FOOD,
            ModItems.SCOOP_OF_CHERRY_ICE_CREAM,
            4,
            "has_vanilla_ice_cream_bucket" to ModItems.VANILLA_ICE_CREAM_BUCKET,
            arrayOf(
                ModItems.VANILLA_ICE_CREAM_BUCKET,
                ModItems.CHERRIES,
                ModItems.CHERRIES,
                ModItems.CHERRIES,
                ModItems.CHERRIES
            )
        ).save(output)

        // Scoop of Chocolate Ice Cream
        shapeless(
            FOOD,
            ModItems.SCOOP_OF_CHOCOLATE_ICE_CREAM,
            4,
            "has_vanilla_ice_cream_bucket" to ModItems.VANILLA_ICE_CREAM_BUCKET,
            arrayOf(
                ModItems.VANILLA_ICE_CREAM_BUCKET,
                ModItems.DARK_CHOCOLATE_CHIPS,
                ModItems.DARK_CHOCOLATE_CHIPS,
                ModItems.DARK_CHOCOLATE_CHIPS,
                ModItems.DARK_CHOCOLATE_CHIPS
            )
        ).save(output)

        // Powsicle Popsicle
        shaped(FOOD, ModItems.POWSICLE_POPSICLE, "has_blue_ice" to Items.BLUE_ICE).pattern(
            " SI",
            "BLS",
            "TB ",
        ).define(
            'S' to ModItems.STRAWBERRY,
            'I' to Items.ICE,
            'B' to ModItems.BLUEBERRIES,
            'L' to Items.BLUE_ICE,
            'T' to Items.STICK
        ).save(output)

        // Luau Popsicle
        shaped(FOOD, ModItems.LUAU_POPSICLE, "has_boxed_milk" to ModItems.BOXED_MILK).pattern(
            " PI",
            "BMP",
            "TB ",
        ).define(
            'P' to ModItems.PINEAPPLE_SLICE,
            'I' to Items.ICE,
            'B' to ModItems.MANGO_SLICE,
            'M' to ModItems.BOXED_MILK,
            'T' to Items.STICK
        ).save(output)

        // Watermelon Popsicle
        shaped(FOOD, ModItems.WATERMELON_POPSICLE, "has_melon_slice" to Items.MELON_SLICE).pattern(
            " MI",
            "MSM",
            "TM ",
        ).define(
            'M' to Items.MELON_SLICE, 'I' to Items.ICE, 'S' to ModItems.SCOOP_OF_ICE_CREAM, 'T' to Items.STICK
        ).save(output)

        // Pink Snowman Popsicle
        shaped(FOOD, ModItems.PINK_SNOWMAN_POPSICLE, "has_pineberry" to ModItems.PINEBERRY).pattern(
            " CI",
            "PSC",
            "TP ",
        ).define(
            'P' to ModItems.PINEBERRY,
            'I' to Items.ICE,
            'C' to ModItems.CHOCOLATE_CHIPS,
            'S' to ModItems.SCOOP_OF_ICE_CREAM,
            'T' to Items.STICK
        ).save(output)
    }

    private fun shapeless(
        category: RecipeCategory,
        result: ItemLike,
        count: Int = 1,
        unlockedBy: Pair<String, ItemLike>,
        ingredients: Array<ItemLike>
    ): ShapelessRecipeBuilder {
        return ShapelessRecipeBuilder.shapeless(category, result, count).apply {
            ingredients.forEach { this.requires(it) }
        }.unlockedBy(unlockedBy.first, has(unlockedBy.second))
    }

    private fun shaped(
        category: RecipeCategory, result: ItemLike, unlockedBy: Pair<String, ItemLike>, count: Int = 1
    ): ShapedRecipeBuilder {
        return ShapedRecipeBuilder.shaped(category, result, count).unlockedBy(unlockedBy.first, has(unlockedBy.second))
    }

    private fun cutting(
        result: ItemLike, unlockedBy: Pair<String, ItemLike>, ingredient: ItemLike, count: Int = 1
    ): RecipeBuilder {
        return CuttingBoardRecipeBuilder.cuttingRecipe(
            ingredient.ingredient, Ingredient.of(CommonTags.TOOLS_KNIFE), result, count
        ).unlockedBy(unlockedBy.first, has(unlockedBy.second))
    }

    private fun shapedPastries(result: ItemLike, itemLike: ItemLike, unlockedBy: String): ShapedRecipeBuilder {
        return ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result).pattern(
            "AA ",
            "ABC",
            " DE",
        ).define(
            'A' to Items.WHEAT,
            'B' to itemLike,
            'D' to Items.SUGAR,
        ).defineTags(
            'C' to Tags.Items.BUCKETS_MILK,
            'E' to Tags.Items.EGGS,
        ).unlockedBy(unlockedBy, has(itemLike))
    }

    private fun cookJuice(
        result: Holder<Item>, ingredient: Holder<Item>
    ): CookingPotRecipeBuilder {
        return CookingPotRecipeBuilder.cookingPotRecipe(result.value(), 1, 200, 0.35F, ModItems.DEFORMABLE_GLASS_BOTTLE)
            .setRecipeBookTab(
                CookingPotRecipeBookTab.DRINKS
            ).addIngredient(ingredient.value()).addIngredient(ingredient.value()).addIngredient(ingredient.value())
            .addIngredient(Items.SUGAR).addIngredient(Items.SUGAR)
            .unlockedByItems("has_${ingredient.unwrapKey().get().location().path}", ingredient.value())
    }

    private fun cutSlice(
        result: ItemLike, count: Int, itemLike: ItemLike, unlockedBy: String, result2: ItemLike? = null, count2: Int = 0
    ): RecipeBuilder {
        return CuttingBoardRecipeBuilder.cuttingRecipe(
            Ingredient.of(itemLike), Ingredient.of(CommonTags.TOOLS_KNIFE), result, count
        ).apply {
            if (result2 != null) addResult(result2, count2)
        }.unlockedBy(unlockedBy, has(itemLike))
    }

    private fun ShapedRecipeBuilder.pattern(pattern1: String, pattern2: String, pattern3: String): ShapedRecipeBuilder {
        return pattern(pattern1).pattern(pattern2).pattern(pattern3)
    }

    private fun ShapedRecipeBuilder.define(vararg pairs: Pair<Char, ItemLike>): ShapedRecipeBuilder {
        pairs.forEach {
            define(it.first, it.second)
        }
        return this
    }

    private fun ShapedRecipeBuilder.defineTags(vararg pairs: Pair<Char, TagKey<Item>>): ShapedRecipeBuilder {
        pairs.forEach {
            define(it.first, it.second)
        }
        return this
    }

}