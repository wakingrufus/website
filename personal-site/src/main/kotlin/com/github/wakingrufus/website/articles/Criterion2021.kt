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
            item("#relevant", "Most Relevant Today")
            item("#surprising", "Most Surprising")
            item("#disappointing", "Most Disappointing")
            item("#ninth", "Bottom of the Ninth Award")
            item("#obscure", "Most Obscure")
        }
        htmlSection {
            a(href = "https://letterboxd.com/wakingrufus/list/criterion-challenge-2021/") { +"Letterboxd" }
            +" list of all movies watched for the challenge by "
            a(href = "https://bigshoulders.city/@wakingrufus") { +"@wakingrufus" }
            +" and "
            a(href = "https://twitter.com/tprig3") { +"@tprig3" }
            +"."
        }
        htmlSection {
            h2 { +"Most Traumatic" }
            a { id = "traumatic" }
            h3 { +"Come and See (1985)" }
            p {
                strong {
                    +"wakingrufus: "
                }
                +"""Even war movies with a "war is bad" message tend to glorify some aspects of war and focus on the leaders and soldiers fighting the war. 
                |This movie is an unflinching look at what happens to ordinary poor people who are in the wrong place at the wrong time during a war. 
                |These stories are important to tell because in history books, they are reduced to statistics.""".trimMargin()

            }
            p {
                strong { +"tprig3: " }
                +"""Probably the only true anti-war movie ever made. Should be required viewing for high school students."""
            }
        }
        htmlSection {
            h2 { +"Best Movie Starring Marcello Mastrionni" }
            a { id = "marcello" }
            p { +"We ended up watching 4 movies starring him, so it felt fitting to pick the best." }
            h3 { +"A Very Special Day (1977)" }
            p {
                strong { +"tprig3: " }
                +"""Starts with a basic rom-com premise but goes k. A different direction and becomes about so much more"""
            }
            p {
                strong { +"wakingrufus: " }
                +"""This movie centers on two people, oppressed in different ways who are set up to be political enemies:
                a gay leftist, and a wife and mother of a fascist family.
                Through the movie, they actually meet each other as human beings,
                and show us that human connection has the ability to break down the barriers put between us by oppressive regimes."""
            }
        }
        htmlSection {
            h2 { +"Most Fun" }
            a { id = "fun" }
            h3 { +"The Fabulous Baron Munchausen (1962)" }
            p {
                strong { +"wakingrufus: " }
                +"""This film's distinctive style combines paintings, animation, and live action in a way that is mind-blowing for the pre-digital era. 
                |Best not enjoyed sober. Consume your substance of choice and enjoy.""".trimMargin()
            }
            p {
                strong { +"tprig3: " }
                +"Completely unique and perfect for a movie about fairy tales."
            }
        }
        htmlSection {
            h2 { +"You'll Laugh, You'll Cry" }
            a { id = "laughcry" }
            h3 { +"Death By Hanging (1968)" }
            p {
                strong { +"wakingrufus: " }
                +"""Equal parts farce and heavy moral lesson about the barbaric practice of capital punishment 
                    |with some story telling tricks that can leave you feeling lost, 
                    |blurring the lines between reality and thought in order to make its point. 
                    |I have never seen a movie that covers so many bases, and it is all based on a true story!""".trimMargin()
            }
            h3 { +"El Verdugo (The Executioner) (1963)" }
            p {
                strong { +"tprig3: " }
                +"A movie so funny you start to forget it's about a reluctant executioner until you're brutally reminded of it."
            }
        }
        htmlSection {
            h2 { +"Most Relevant Today" }
            a { id = "relevant" }
            h3 { +"Canoa: A Shameful Memory (1968)" }
            p {
                strong { +"wakingrufus: " }
                +"""Living in the US in 2021 feels a lot like living in the village of Canoa in 1968. 
                |Paranoia, rage, mob mentality, and scapegoating leading to a society that has completely lost its mind.""".trimMargin()
            }
            h3 { +"Death By Hanging (1968)" }
            p {
                strong { +"tprig3: " }
                +"""It tackles xenophobia, nationalism, wealth inequality, class, duty, bureaucracy, so much more. 
                |Hard to think of a movie that makes you think about so many issues important to today.""".trimMargin()
            }
        }
        htmlSection {
            h2 { +"Most Surprising" }
            a { id = "surprising" }
            p { +"This award goes to the movie that was better than we expected, underrated, or just a great movie that we would have never watched on our own. " }
            h3 { +"The Red Shoes (1948)" }
            p {
                strong { +"wakingrufus: " }
                +"""A musical about musical theater is about the farthest from "up my alley" as can be,
                | but this movie with its gorgeous Technicolor and mesmerizing music and dance sequences won me over.""".trimMargin()
            }
            h3 { +"ハウス (House) (1977)" }
            p {
                strong { +"tprig3: " }
                +"""A cult 70s Japanese horror movie is something I would usually try to avoid at all costs. 
                |This was a great watch. Completely won me over.""".trimMargin()
            }
        }
        htmlSection {
            h2 { +"Most Disappointing" }
            a { id = "disappointing" }
            h3 { +"8½ (1963)" }
            p {
                strong { +"tprig3: " }
                +"Really don't understand the hype around this movie."
            }
            p {
                strong { +"wakingrufus: " }
                +"""This is supposed to be one of the greatest films ever. I can see why a director would think this, 
                |but to me, its just too much self-indulgence and self-pity about a director who is just a misogynistic jerk.""".trimMargin()
            }
        }
        htmlSection {
            h2 { +"Bottom of the Ninth Award" }
            p { +"This award goes to a movie that was better made significantly better by the ending." }
            a { id = "ninth" }
            h3 { +"The Great Dictator (1940)" }
            p {
                strong { +"wakingrufus: " }
                +"""The movie is good, not great, for most of its length. It is all leading up to the end, 
                    when Charlie Chaplin's character impersonates Hitler to give an impassioned speech. 
                    The speech is a beautiful humanist appeal for all human-kind to do away with oppressors and unite to free us all.
                    It hits home just as hard today as when it was written.
                    The fact that Chaplin had to try so hard to get this movie made 
                    at a time Hollywood would rather self-censor itself to not say anything bad about Hitler 
                    so that they could keep distributing movies in Germany really underscores the bold condemnation 
                    of nationalism and imperialism in the speech."""
            }
            h3 { +"La Haine (1995)" }
            p {
                strong { +"tprig3: " }
                +""""It's about a society on its way down. And as it falls,it keeps telling itself: "So far so good... So far so good... So far so good." 
                |It's not how you fall that matters. It's how you land."""".trimMargin()
            }
        }
        htmlSection {
            h2 { +"Most Obscure" }
            a { id = "obscure" }
            h3 { +"Crumbs (2015)" }
            p {
                strong { +"wakingrufus: " }
                +"""With only 1.5k views on letterboxd, 
                |this Ethiopian post-apocalyptic sci-fi shows us a world after an alien race of pop culture fandoms 
                |have forced the entire earth economy to revolve around the secondhand vintage collectables market.""".trimMargin()
            }
            p {
                strong { +"tprig3: " }
                +"""Never heard of it before but it carries an important lesson for a lot of people my age. 
                |Do not pray to Michael Jordan he can not save you.""".trimMargin()
            }
        }
        footer(myFooter)
    }
}