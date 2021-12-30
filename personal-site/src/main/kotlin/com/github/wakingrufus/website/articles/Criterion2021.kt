package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val criterion2021 = htmlPage("criterion-2021.html") {
    article("2021 Criterion Challenge Recap") {
        nav {
            item("#traumatic", "Most Traumatic")
            item("#marcello", "Best Movie Starring Marcello Mastrionni")
            item("#fun", "Most Fun")
            item("#laughcry", "You'll Laugh, You'll Cry")
            item("#relevant", "Most relevant to today")
            item("#surprising", "Most surprising")
            item("#disappointing", "Most disappointing")
            item("#ninth", "Bottom of the ninth ")
            item("#obscure", "Most obscure")
        }
        htmlSection {
            a(href = "https://letterboxd.com/wakingrufus/list/criterion-challenge-2021/") { +"Letterboxd" }
        }
        htmlSection {
            h2 { +"Most Traumatic" }
            a { id = "traumatic" }
            h3 { +"wakingrufus" }
            h4 { +"Come and See (1985)" }
            p {}
        }
        htmlSection {
            h2 { +"Best Movie Starring Marcello Mastrionni" }
            a { id = "marcello" }
            h3 { +"wakingrufus" }
            h4 { +"A Very Special Day (1977)" }
            p {
                +"""This movie centers on two people, oppressed in different ways who are set up to be political enemies:
                a gay leftist, and a wife and mother of a fascist family.
                Through the movie, they actually meet each other as human beings,
                and show us that human connection has the ability to break down the barriers put between us by oppressive regimes."""
            }
        }
        htmlSection {
            h2 { +"Most Fun" }
            a { id = "fun" }
            h3 { +"wakingrufus" }
            h4 { +"The Fabulous Baron Munchausen (1962)" }
            p {}
        }
        htmlSection {
            h2 { +"You'll Laugh, You'll Cry" }
            a { id = "laughcry" }
            h3 { +"wakingrufus" }
            h4 { +"Death By Hanging (1968)" }
            p {}
        }
        htmlSection {
            h2 { +"Most Traumatic" }
            a { id = "relevant" }
            h3 { +"wakingrufus" }
            h4 { +"Come and See (1985)" }
            p {}
        }
        htmlSection {
            h2 { +"Most Traumatic" }
            a { id = "surprising" }
            h3 { +"wakingrufus" }
            h4 { +"Come and See (1985)" }
            p {}
        }
        htmlSection {
            h2 { +"Most Traumatic" }
            a { id = "disappointing" }
            h3 { +"wakingrufus" }
            h4 { +"Come and See (1985)" }
            p {}
        }
        htmlSection {
            h2 { +"Most Traumatic" }
            a { id = "ninth" }
            h3 { +"wakingrufus" }
            h4 { +"Come and See (1985)" }
            p {}
        }
        htmlSection {
            h2 { +"Most Traumatic" }
            a { id = "obscure" }
            h3 { +"wakingrufus" }
            h4 { +"Come and See (1985)" }
            p {}
        }
        footer(myFooter)
    }
}