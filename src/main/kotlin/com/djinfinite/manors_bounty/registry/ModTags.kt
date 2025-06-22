package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.res
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.biome.Biome

object ModTags {

    object Items {

        val BIG_CARPET = create("big_carpet")
        val ALL_FRUITS = create("all_fruits")
        val COOKABLE_FRUITS = create("cookable_fruits")
        val SALAD = create("salad")
        val FRUIT_DRINKS = create("fruit_drinks")

        val ICE_CREAM = create("ice_cream")
        val ICE_CREAM_AND_CONE = create("ice_cream_and_cone")
        val ICE_CREAM_TYPE = create("ice_cream_type")

        val FRYER_COOKABLE_ITEM = create("fryer_cookable_item")
        val FRYER_FRYABLE_ITEM = create("fryer_fryable_item")
        val FRYER_USABLE_ITEM = create("fryer_useable_item") // TODO: Fix the typo

        val APPLE_FOODS = create("apple_foods")
        val AVOCADO_FOODS = create("avocado_foods")
        val BLUEBERRY_FOODS = create("blueberry_foods")
        val CHERRIES_FOODS = create("cherries_foods")
        val CHERRY_BLOSSOMS_FOODS = create("cherry_blossoms_foods")
        val CHORUS_FRUIT_FOODS = create("chorus_fruit_foods")
        val CRANBERRY_FOODS = create("cranberry_foods")
        val DRAGON_FRUIT_FOODS = create("dragon_fruit_foods")
        val DURIAN_FOODS = create("durian_foods")
        val GLOW_BERRIES_FOODS = create("glow_berries_foods")
        val HAMIMELON_FOODS = create("hamimelon_foods")
        val KIWIFRUIT_FOODS = create("kiwifruit_foods")
        val LEMON_FOODS = create("lemon_foods")
        val MANGO_FOODS = create("mango_foods")
        val OLIVE_FRUIT_FOODS = create("olive_fruit_foods")
        val ORANGE_FOODS = create("orange_foods")
        val PEACH_FOODS = create("peach_foods")
        val PINEAPPLE_FOODS = create("pineapple_foods")
        val STARFRUIT_FOODS = create("starfruit_foods")
        val STRAWBERRY_FOODS = create("strawberry_foods")
        val SWEET_BERRIES_FOODS = create("sweet_berries_foods")
        val WATERMELON_FOODS = create("watermelon_foods")
        val MULTIPLE_FRUIT_FOODS = create("multiple_fruit_foods")

        val ALPINE_TREE_LOGS = create("alpine_tree_logs")
        val AVOCADO_TREE_LOGS = create("avocado_tree_logs")
        val CHERRIES_TREE_LOGS = create("cherries_tree_logs")
        val DURIAN_TREE_LOGS = create("durian_tree_logs")
        val KIWIFRUIT_TREE_LOGS = create("kiwifruit_tree_logs")
        val MANGO_TREE_LOGS = create("mango_tree_logs")
        val OLIVE_TREE_LOGS = create("olive_tree_logs")
        val ROSACEAE_TREE_LOGS = create("rosaceae_tree_logs")
        val RUTACEAE_TREE_LOGS = create("rutaceae_tree_logs")
        val SCOTS_PINE_LOGS = create("scots_pine_logs")
        val STARFRUIT_TREE_LOGS = create("starfruit_tree_logs")

        private fun create(tag: String): TagKey<Item> = TagKey.create(Registries.ITEM, res(tag))

    }

    object Biomes {

        val OPTIONAL_VIOLET_BLUE_PLAINS = create("optional_violet_blue_plains")

        private fun create(tag: String): TagKey<Biome> = TagKey.create(Registries.BIOME, res(tag))

    }

}