package com.github.wakingrufus.website.cooking

import com.github.wakingrufus.recipe.*
import com.github.wakingrufus.website.cooking.Spices.ginger
import java.time.temporal.ChronoUnit

object RecipeIndex : RecipeDb() {
    val bibimbop = recipe("Bibimbop") {
        servings = 4
        ingredients(
                1 lbs Ingredient("thinly sliced marbled beef or pork"),
                4 ea garlic("minced"),
                1 tbs ginger,
                2 ea greenOnion
        )
        step("Warm oil and butter in skillet over medium heat.")
        step("Stir in onion and garlic and cook until the onions are soft and translucent.")
        step("Stir in curry powder, garam masala, ginger, cumin, and salt")
        step("cook for 1-2 minutes, stirring")
        step("stir in tomatoes, yogurt, and chickpeas")
        step("Simmer 5 min") {
            duration(5, ChronoUnit.MINUTES)
        }
    }
    val carnitas = com.github.wakingrufus.recipe.recipe("Carnitas") {
        servings = 4
        ingredients(
                4 lbs pork("Butt or Shoulder"),
                2.5 tsp Spices.salt,
                1 tsp Spices.blackPepper,
                1 medium yellowOnion,
                8 ea garlic("cloves, minced"),
                1 tbs Spices.oregano,
                1 tbs Spices.cumin,
                1 tbs oliveOil,
                .75 cup orangeJuice,
                .75 cup mexicanCoke
        )
        step("Pat dry pork")
        step("Combine salt, pepper, oregano, cumin, and olive oil and rub all over pork")
        step("Place pork in slow cooker, fat side up")
        step("Top pork with garlic and onions")
        step("Pour over orange juice and coke")
        step("Cook on low 10hrs or med 6 hrs") {
            duration(10, ChronoUnit.HOURS)
        }
        step("Remove pork from slow cooker")
        step("Save juice from slow cooker")
        step("Set aside all pork that you will be saving for leftovers")
        step("To prepare pork for eating, heat up a skillet very hot")
        step("Spread pork on skillet")
        step("Cook until it starts to crisp") {
            duration(1, ChronoUnit.MINUTES)
        }
        step("Spoon over about 1 ladle of the saved juices and continue to cook until reduced") {
            duration(3, ChronoUnit.MINUTES)
        }
    }
    val chickPeaCurry = com.github.wakingrufus.recipe.recipe("Butter Chickpea Curry") {
        servings = 4
        ingredients(
                12 oz chickpeas,
                2 tbs canolaOil,
                1 tbs butter,
                1 medium yellowOnion,
                1 tsp garlic("minced"),
                2 tsp Spices.curryPowder,
                2 tsp Spices.garamMasala,
                1 tsp ginger("ground"),
                1 tsp Spices.cumin,
                1 tsp Spices.salt,
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
            duration(5, ChronoUnit.MINUTES)
        }
    }
    val seafoodFraDiavolo = com.github.wakingrufus.recipe.recipe("Seafood Fra Diavolo") {
        servings = 8
        ingredients(
                1 lbs shrimp,
                4 tbs oliveOil,
                6 ea garlic("cloves, minced"),
                28 oz dicedTomatoes,
                1.5 tsp Spices.salt,
                1 tsp Spices.redPepperFlakes,
                16 oz linguine,
                1 tbs parsley("chopped"),
                8 oz bayScallops
        )
        step("In a large saucepan, heat 2 tablespoons of the olive oil with the garlic over medium heat.")
        step("When the garlic starts to sizzle, pour in the tomatoes. Season with salt and red pepper. Bring to a boil. Lower the heat, and simmer for 30 minutes, stirring occasionally.")
        step("Meanwhile, bring a large pot of lightly salted water to a boil. Cook pasta for 8 to 10 minutes, or until al dente; drain.")
        step("In a large skillet, heat the remaining 2 tablespoons of olive oil over high heat. Add the shrimp and scallops. Cook for about 2 minutes, stirring frequently, or until the shrimp turn pink. Add shrimp and scallops to the tomato mixture, and stir in the parsley. Cook for 3 to 4 minutes, or until the sauce just begins to bubble. Serve sauce over pasta.")
    }
}
