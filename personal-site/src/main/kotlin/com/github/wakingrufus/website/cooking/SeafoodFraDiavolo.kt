package com.github.wakingrufus.website.cooking

import com.github.wakingrufus.recipe.*
import com.github.wakingrufus.website.cooking.Spices.redPepperFlakes
import com.github.wakingrufus.website.cooking.Spices.salt

val seafoodFraDiavolo = recipe("Seafood Fra Diavolo") {
    servings = 8
    ingredients(
            1 lbs shrimp,
            4 tbs oliveOil,
            6 ea garlic("cloves, minced"),
            28 oz dicedTomatoes,
            1.5 tsp salt,
            1 tsp redPepperFlakes,
            16 oz linguine,
            1 tbs parsley("chopped"),
            8 oz bayScallops
    )
    step("In a large saucepan, heat 2 tablespoons of the olive oil with the garlic over medium heat.")
    step("When the garlic starts to sizzle, pour in the tomatoes. Season with salt and red pepper. Bring to a boil. Lower the heat, and simmer for 30 minutes, stirring occasionally.")
    step("Meanwhile, bring a large pot of lightly salted water to a boil. Cook pasta for 8 to 10 minutes, or until al dente; drain.")
    step("In a large skillet, heat the remaining 2 tablespoons of olive oil over high heat. Add the shrimp and scallops. Cook for about 2 minutes, stirring frequently, or until the shrimp turn pink. Add shrimp and scallops to the tomato mixture, and stir in the parsley. Cook for 3 to 4 minutes, or until the sauce just begins to bubble. Serve sauce over pasta.")
}