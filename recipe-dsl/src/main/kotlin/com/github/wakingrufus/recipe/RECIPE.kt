package com.github.wakingrufus.recipe

@RecipeDsl
class RECIPE(val name: String) {
    var servings: Int? = null
    private val ingredients = mutableListOf<RecipeIngredient>()
    private val instructions = mutableListOf<STEP>()

    fun ingredients(vararg ingredients: RecipeIngredient) {
        this.ingredients.addAll(ingredients)
    }
    fun step(description: String, step: STEP.() -> Unit = {}) {
        instructions += STEP(description).apply(step)
    }

    operator fun invoke(): Recipe {
        return Recipe(name, servings, ingredients.toList(), instructions.toList())
    }
}

class Recipe(val name: String,
             val servings: Int? = null,
             val ingredients: List<RecipeIngredient>,
             val instructions: List<STEP>)