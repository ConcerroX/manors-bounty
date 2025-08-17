package com.djinfinite.manors_bounty.content.pineapple

import com.djinfinite.manors_bounty.content.food.FoodIngredientType
import com.djinfinite.manors_bounty.content.food.FoodItem
import com.djinfinite.manors_bounty.content.food.FoodType
import com.djinfinite.manors_bounty.registry.ModItems
import com.djinfinite.manors_bounty.registry.ModMobEffects
import net.minecraft.world.item.Items
import net.minecraft.world.item.UseAnim

object PineappleFoodItems {

    class Slice(properties: Properties) :
        FoodItem(properties, FoodIngredientType.PINEAPPLE, FoodType.A, arrayOf(ModMobEffects.SUMMER_HEATWAVE), null, 4)

    class Juice(properties: Properties) : FoodItem(
        properties,
        FoodIngredientType.PINEAPPLE,
        FoodType.B,
        arrayOf(ModMobEffects.SUMMER_HEATWAVE),
        ModItems.DEFORMABLE_GLASS_BOTTLE.get(),
        1,
        UseAnim.DRINK
    )

    class Pie(properties: Properties) :
        FoodItem(properties, FoodIngredientType.PINEAPPLE, FoodType.C, arrayOf(ModMobEffects.SUMMER_HEATWAVE))

    class Popsicle(properties: Properties) :
        FoodItem(properties, FoodIngredientType.PINEAPPLE, FoodType.C, arrayOf(ModMobEffects.SUMMER_HEATWAVE))

    class Cakes(properties: Properties) :
        FoodItem(properties, FoodIngredientType.PINEAPPLE, FoodType.C, arrayOf(ModMobEffects.SUMMER_HEATWAVE), Items.STICK)

}