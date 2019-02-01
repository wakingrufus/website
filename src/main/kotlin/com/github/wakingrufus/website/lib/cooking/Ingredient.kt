package com.github.wakingrufus.website.lib.cooking

open class Ingredient(val name: String) {
   operator fun invoke(modification: String): ModifiedIngredient {
        return ModifiedIngredient(this, modification)
    }
}

class ModifiedIngredient(ingredient: Ingredient,val modification: String) : Ingredient(ingredient.name)