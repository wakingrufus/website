package com.github.wakingrufus.website.slideshows

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssStringPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.slides.*
import kotlinx.html.DIV
import kotlinx.html.a
import kotlinx.html.li


fun whyDoesAgileFail(): Website.() -> Unit = {
    slideshow(
            name = Paths.FAIL_AGILE_SLIDESHOW_BASE_NAME,
            rootCss = CssStringPage(Paths.SLIDESHOW_CSS_PATH, MyStyles().slideShowStyles())) {
        titleSlide(title = "Why Does Agile Fail?", block = failAgileTitleSlide())
        slide(title = "Manifesto", block = functionalKotlinFunctionalProgramming())

        slide(title = "Alienation", block = functionalKotlinImmutableDataStory())
        slide(title = "Ownership", block = functionalKotlinImmutableData())
        slide(title = "Product owners", block = functionalKotlinDeterministicStory())
        slide(anarchy())
        slide(pace())

    }
}

fun failAgileTitleSlide(): DIV.() -> Unit = {
    slideshowTitleFooter {
        slideshowTitleFooterLine { +"John Burns" }
        slideshowTitleFooterLine { +"wakingrufus@gmail.com" }
        slideshowTitleFooterLine {
            a(href = "http://wakingrufus.neocities.org") { +"http://wakingrufus.neocities.org" }
        }
    }
}


fun manifesto(): DIV.() -> Unit = {
    slideContent {
        slideList {
            li { +"individuals and interactions of processes and tools" }
            li { +"customer collaboration over contract negotiation" }
            li { +"the best architectures, requirements, and designs emerge from self organizing teams" }
            li { +"the most efficient and effective method of conveying information to and within a development team is face to face conversation" }
            li {
                +"business people and developers must work together daily throught the project"
            }
            li { +"trust" }
            li { +"Work at constant pace" }
        }

    }
}

fun additionalDiscussion(): SLIDE.() -> Unit = {
    title = "Additional Discussion"
}

fun alienation(): DIV.() -> Unit = {
    slideContent {
        slideList {
            a(href = "https://twitter.com/allenholub/status/1125107686201151488") { +"Allan Holub on Managers" }
            li {
                +"""Agile is *radical*. Self organizing means self organizing. There are reasons for that. "Management" slows agility (and sometimes renders it impossible). Why do so many defenders of "management" not seem to get that. Are you agile or not?
                |All of this talk about how we're redefining "manager" (and I suppose "management") to mean something else implies that the notion of "manager" is so important to the running of a company that we can't get rid of it. Why is that title so sacrosanct that we need to preserve it?
                |If you define a "good manager" as somebody who doesn't act like a manager, what does that say?
                |I have no problem with leaders. It's managers (people who manage other people) that I have a hard time with. I can choose to follow a leader. I cannot choose not to be managed, especially if my job depends on it. The best teams are autonomous.
            """.trimMargin()
            }
        }
    }
}

fun anarchy(): SLIDE.() -> Unit = {
    title = "Hierarchy vs Anarchy"
    content {
        slideContent {
            slideList {
                li { +"individuals and interactions of processes and tools" }
            }
        }
    }
}

fun pace(): SLIDE.() -> Unit = {
    title = "Pace"
    content {
        slideContent {
            slideList {
                li { +"Business inevitly asks for more" }
                li { +"Agile interpreted to mean 'go faster'" }
            }
        }
    }
}

fun coops() {

}

fun unions() {

}