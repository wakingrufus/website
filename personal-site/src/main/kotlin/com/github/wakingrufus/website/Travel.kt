package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.PLACE
import com.github.wakingrufus.website.lib.area
import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import kotlinx.html.*


fun travel(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Travel")
        content {
            ul {
                li { a(href = "#colorado") { +"Colorado" } }
            }
            area("Colorado") {
                subArea("Denver", denver())
                subArea("Central City", centralCity())
                subArea("Frisco", frisco())
            }
        }
        footer { myFooter() }
    }
}

fun centralCity(): PLACE.() -> Unit = {
    description {
        +"""
                |Central City is an old historic mining town that is now completely made up of casinos
                |and a couple dispensaries. But, a few minutes outside of town, you can find an old ghost town called
                |"Nevadaville".
                |This is a true ghost town, and worth a visit up the steep gravel road you must take to get there.
                |Note that this is not a touristy place, and the properties are privately owned.
                |
            """.trimMargin()
    }
}

fun denver(): PLACE.() -> Unit = {
    subArea("Tennyson Street") {
        website = "https://shoptennyson.com/"
        description {
            +"A nice walkable street with lots of shops and restaurants."
        }
        place(name = "De Steeg Brewery",
                website = "http://desteegbrewing.com/") {
            map = "https://www.google.com/maps/place/De+Steeg+Brewing+-+Entrance+in+%22The+Alley%22/@39.7761296,-105.0435161,19.5z/data=!4m5!3m4!1s0x876b8784a9dc1b73:0x81fec315ac8e77ad!8m2!3d39.776061!4d-105.043509"
            description {
                +"""
                A brewery with a great Tripel. No food, but you can bring your own.
                There is also a popcorn machine.
                Enter from the alley behind Tennyson Street.
                """.trimIndent()
            }
        }
    }

    place(name = "Billy's Inn",
            website = "http://www.billysinn.com/") {
        map = "https://www.google.com/maps/place/Billy's+Inn/@39.7769774,-105.037172,17z/data=!3m1!4b1!4m5!3m4!1s0x876b8780d0aeff1d:0x8a9ea0f2809b9052!8m2!3d39.7769733!4d-105.034978"
        description {
            +"""
                Tequila bar and brunch spot. Get the Sugar-cured bacon at brunch.
              """.trimIndent()
        }
    }
}

fun frisco(): PLACE.() -> Unit = {
    description {
        +"""
        A ski town off of I-70 in the Rockies.
        There is a nice lake suitable for boating in the summer.
        """.trimIndent()
    }
    place(name = "The Lost Cajun", website = "https://thelostcajun.com/locations/frisco-colorado") {
        description {
            +"""
                Great Cajun restaurant. It is now a chain, but this is the original. Try the Cat-touffeé!
            """.trimIndent()
        }
    }
}