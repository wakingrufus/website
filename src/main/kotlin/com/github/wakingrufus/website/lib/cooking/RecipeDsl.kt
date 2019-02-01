package com.github.wakingrufus.website.lib.cooking

@DslMarker
annotation class RecipeDsl

fun recipe(name: String, block: RECIPE.() -> Unit): RECIPE {
    return RECIPE(name).apply(block)
}

infix fun Number.cup(ingredient: Ingredient): RecipeIngredient {
    return RecipeIngredient(this, MeasureUnit.CUP, ingredient)
}

infix fun Number.oz(ingredient: Ingredient): RecipeIngredient {
    return RecipeIngredient(this, MeasureUnit.OZ, ingredient)
}

infix fun Number.tbs(ingredient: Ingredient): RecipeIngredient {
    return RecipeIngredient(this, MeasureUnit.TBS, ingredient)
}

infix fun Number.tsp(ingredient: Ingredient): RecipeIngredient {
    return RecipeIngredient(this, MeasureUnit.TSP, ingredient)
}

infix fun Number.ea(ingredient: Ingredient): RecipeIngredient {
    return RecipeIngredient(this, MeasureUnit.EA, ingredient)
}

infix fun Number.medium(ingredient: Ingredient): RecipeIngredient {
    return RecipeIngredient(this, MeasureUnit.EA, ingredient)
}

infix fun Number.lbs(ingredient: Ingredient): RecipeIngredient{
    return RecipeIngredient(this, MeasureUnit.LBS, ingredient)
}