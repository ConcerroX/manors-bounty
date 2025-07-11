package com.djinfinite.manors_bounty.content.fryer

import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike

class FryerRecipeBuilder(
    private val ingredient: Ingredient, private val result: ItemStack, private val isLongTime: Boolean
) : RecipeBuilder {

    constructor(ingredient: Ingredient, result: ItemLike, isLongTime: Boolean) : this(
        ingredient, ItemStack(result), isLongTime
    )

    private var unlockedBy: Pair<String, Criterion<*>>? = null
    private var group: String? = null

    override fun getResult(): Item = result.item

    override fun unlockedBy(name: String, criterion: Criterion<*>): RecipeBuilder {
        unlockedBy = name to criterion
        return this
    }

    override fun group(groupName: String?): RecipeBuilder {
        group = groupName
        return this
    }

    override fun save(recipeOutput: RecipeOutput, id: ResourceLocation) {
        val advancement = recipeOutput.advancement().apply {
            addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            unlockedBy?.let { (name, criterion) -> addCriterion(name, criterion) }
            rewards(AdvancementRewards.Builder.recipe(id))
            requirements(AdvancementRequirements.Strategy.OR)
        }
        val recipe = FryerRecipe(ingredient, result, isLongTime)
        recipeOutput.accept(id, recipe, advancement.build(id.withPrefix("recipes/")))
    }

}