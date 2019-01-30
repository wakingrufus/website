package com.github.wakingrufus.website.lib.cooking

@RecipeDsl
class RecipeIngredient(val quantity: Number,
                       val unit: MeasureUnit,
                       val ingredient: Ingredient)