package com.github.wakingrufus.website.cooking

import com.github.wakingrufus.website.lib.cooking.*
import java.time.temporal.ChronoUnit.MINUTES

val chickPeaCurry = recipe("Butter Chickpea Curry") {
    servings = 4
    ingredients(
        12 oz chickpeas,
        2 tbs canolaOil,
        1 tbs butter,
        1 medium yellowOnion,
        1 tsp garlic("minced"),
        2 tsp curryPowder,
        2 tsp garamMasala,
        1 tsp ginger("ground"),
        1 tsp cumin,
        1 tsp salt,
        1 cup yogurt("greek", "plain"),
        8 oz tomatoPaste,
        15 oz crushedTomatoes
    )
    step("Warm oil and butter in skillet over medium heat.")
    step("Stir in onion and garlic and cook until the onions are soft and translucent.")
    step("Stir in curry powder, garam masala, ginger, cumin, and salt")
    step("cook for 1-2 minutes, stirring")
    step("stir in tomatoes, yogurt, and chickpeas")
    step("Simmer 5 min") {
        duration(5, MINUTES)
    }
}