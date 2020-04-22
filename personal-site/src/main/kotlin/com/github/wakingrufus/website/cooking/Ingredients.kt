package com.github.wakingrufus.website.cooking

import com.github.wakingrufus.recipe.Ingredient

val chickpeas = Ingredient("chickpeas")
val canolaOil = Ingredient("canola oil")
val yellowOnion = Ingredient("yellow onion")
fun yogurt(style: String, flavor: String) = Ingredient("yogurt")("$flavor $style")
val tomatoPaste = Ingredient("tomato paste")
val crushedTomatoes = Ingredient("crushed tomatoes")
val butter = Ingredient("butter")
val shrimp = Ingredient("shrimp")
val oliveOil = Ingredient("olive oil")
val dicedTomatoes = Ingredient("diced tomatoes")
val linguine = Ingredient("linguine")
val parsley = Ingredient("parsley")
val garlic = Ingredient("garlic")
val bayScallops = Ingredient("bay scallops")
val pork = Ingredient("Pork")
val orangeJuice = Ingredient("Orange Juice")
val mexicanCoke = Ingredient("Mexican Coke")

object Spices {
    val curryPowder = Ingredient("curry powder")
    val garamMasala = Ingredient("garam masala")
    val ginger = Ingredient("ginger")
    val cumin = Ingredient("cumin")
    val salt = Ingredient("salt")
    val blackPepper = Ingredient("Black Pepper")
    val oregano = Ingredient("Oregano")
    val redPepperFlakes = Ingredient("red pepper flakes")
}