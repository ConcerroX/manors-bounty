package com.djinfinite.manors_bounty.content.durian

import com.djinfinite.manors_bounty.content.food.FoodIngredientType
import com.djinfinite.manors_bounty.content.food.FoodItem
import com.djinfinite.manors_bounty.content.food.FoodType
import com.djinfinite.manors_bounty.registry.ModMobEffects
import net.minecraft.core.Holder
import net.minecraft.world.effect.MobEffect

object DurianFoodItems {

    val MOB_EFFECTS = arrayOf<Holder<MobEffect>>(ModMobEffects.SUMMER_HEATWAVE, ModMobEffects.HACKED_THORNS)

    class Half(properties: Properties) :
        FoodItem(properties, FoodIngredientType.DURIAN, FoodType.A, MOB_EFFECTS, null, 2)

    class Flesh(properties: Properties) :
        FoodItem(properties, FoodIngredientType.DURIAN, FoodType.A, MOB_EFFECTS, null, 6)

    class CookedFlesh(properties: Properties) : FoodItem(properties, FoodIngredientType.DURIAN, FoodType.B, MOB_EFFECTS)

    class Crisp(properties: Properties) : FoodItem(properties, FoodIngredientType.DURIAN, FoodType.B, MOB_EFFECTS)

    class Cake(properties: Properties) : FoodItem(properties, FoodIngredientType.DURIAN, FoodType.C, MOB_EFFECTS)

    class CakeSlice(properties: Properties) :
        FoodItem(properties, FoodIngredientType.DURIAN, FoodType.C, MOB_EFFECTS, null, 4)

}