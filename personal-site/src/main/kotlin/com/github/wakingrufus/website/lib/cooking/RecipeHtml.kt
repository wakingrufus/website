package com.github.wakingrufus.website.lib.cooking

import com.github.wakingrufus.recipe.ModifiedIngredient
import com.github.wakingrufus.recipe.Recipe
import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.lib.css
import kotlinx.css.*
import kotlinx.html.*

fun Recipe.html(): ARTICLE.() -> Unit = {
    div {
        style = css {
            backgroundColor = MyStyles.SUB_BACKGROUND_COLOR
            borderStyle = BorderStyle.dashed
            borderColor = MyStyles.FONT_COLOR
            paddingLeft = 1.em
            paddingRight = 1.em
        }
        h2 { +name }
        h3 { +"Ingredients" }
        ul {
            ingredients.forEach {
                val ingredient = it.ingredient
                li { +"${it.quantity} ${it.unit} ${it.ingredient.name}"
                +when(ingredient){
                    is ModifiedIngredient -> " (${ingredient.modification})"
                    else -> ""
                }}
            }
        }
        h3 { +"Instructions" }
        ol {
            instructions.forEach {
                li { +it.description }
            }
        }
        p {
            +"Serves $servings"
        }
    }
}