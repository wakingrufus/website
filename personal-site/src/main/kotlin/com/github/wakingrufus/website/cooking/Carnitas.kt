package com.github.wakingrufus.website.cooking

import com.github.wakingrufus.recipe.*
import com.github.wakingrufus.website.cooking.Spices.blackPepper
import com.github.wakingrufus.website.cooking.Spices.cumin
import com.github.wakingrufus.website.cooking.Spices.oregano
import com.github.wakingrufus.website.cooking.Spices.salt
import java.time.temporal.ChronoUnit.HOURS
import java.time.temporal.ChronoUnit.MINUTES

val carnitas = recipe("Carnitas") {
    servings = 4
    ingredients(
            4 lbs pork("Butt or Shoulder"),
            2.5 tsp salt,
            1 tsp blackPepper,
            1 medium yellowOnion,
            8 ea garlic("cloves, minced"),
            1 tbs oregano,
            1 tbs cumin,
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
        duration(10, HOURS)
    }
    step("Remove pork from slow cooker")
    step("Save juice from slow cooker")
    step("Set aside all pork that you will be saving for leftovers")
    step("To prepare pork for eating, heat up a skillet very hot")
    step("Spread pork on skillet")
    step("Cook until it starts to crisp") {
        duration(1, MINUTES)
    }
    step("Spoon over about 1 ladle of the saved juices and continue to cook until reduced") {
        duration(3, MINUTES)
    }
}
