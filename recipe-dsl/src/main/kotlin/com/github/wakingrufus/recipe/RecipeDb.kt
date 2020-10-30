package com.github.wakingrufus.recipe

@RecipeDsl
open class RecipeDb {
    val recipes = mutableListOf<Recipe>()
    fun recipe(name: String, block: RECIPE.() -> Unit): Recipe {
        return RECIPE(name).apply(block)().also {
            recipes.add(it)
        }
    }
}
