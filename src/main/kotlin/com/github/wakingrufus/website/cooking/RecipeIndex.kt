package com.github.wakingrufus.website.cooking

import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import com.github.wakingrufus.website.sideNav
import kotlinx.html.*

val allRecipes = listOf(seafoodFraDiavolo, chickPeaCurry)

fun recipeIndex(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Recipes")
        sideNav()
        content {
            ul {
                allRecipes.forEach { recipe ->
                    li { a(href = recipe.name.replace(" ", "") + ".html") { +recipe.name } }
                }
            }
        }
    }
}

