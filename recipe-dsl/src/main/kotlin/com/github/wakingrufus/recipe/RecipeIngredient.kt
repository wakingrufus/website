package com.github.wakingrufus.recipe

@RecipeDsl
class RecipeIngredient(val quantity: Number,
                       val unit: MeasureUnit,
                       val ingredient: Ingredient)