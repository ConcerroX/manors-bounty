package com.djinfinite.manors_bounty.data

import com.djinfinite.manors_bounty.registry.ModItems
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.Tags
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab
import vectorwing.farmersdelight.common.tag.CommonTags
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder
import java.util.concurrent.CompletableFuture


class ModRecipeProvider(output: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    RecipeProvider(output, lookupProvider) {

    override fun buildRecipes(output: RecipeOutput) {
        shapedPaleteria(ModItems.PINEAPPLE_PALETERIA, ModItems.PINEAPPLE_SLICE, "has_pineapple_slice").save(output)
        shapedPastries(ModItems.PINEAPPLE_PASTRIES, ModItems.PINEAPPLE_SLICE, "has_pineapple_slice").save(output)
        cutSlice(ModItems.PINEAPPLE_SLICE, 4, ModItems.PINEAPPLE, "has_pineapple", ModItems.PINEAPPLE_CROP, 1).save(
            output
        )
        cookJuice(ModItems.PINEAPPLE_JUICE, ModItems.PINEAPPLE_SLICE).save(output)
    }

    private fun shapedPaleteria(result: ItemLike, itemLike: ItemLike, unlockedBy: String): ShapedRecipeBuilder {
        return ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result).pattern(
            " AB",
            "ACA",
            "DA ",
        ).define(
            'A' to itemLike,
            'B' to Items.ICE,
            'C' to Items.SNOWBALL,
            'D' to Items.STICK,
        ).unlockedBy(unlockedBy, has(itemLike))
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
        return CookingPotRecipeBuilder.cookingPotRecipe(result.value(), 1, 200, 0.35F, ModItems.DEFORMED_GLASS_BOTTLE)
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