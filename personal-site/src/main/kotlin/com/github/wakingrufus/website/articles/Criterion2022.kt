package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val criterion2022 = htmlPage("criterion-2022.html") {
    article("2022 Criterion Challenge Recap") {
        nav {
            item("#fun", "Most Fun")
            item("#laughcry", "You'll Laugh, You'll Cry")
            item("#relevant", "Most Relevant Today")
            item("#surprising", "Most Surprising")
            item("#disappointing", "Most Disappointing")
            item("#ninth", "Bottom of the Ninth Award")
            item("#obscure", "Most Obscure")
            item("#true", "Best Based on a True Story")
            item("#dudes", "Dudes Rock")
        }
        htmlSection {
            a(href = "https://letterboxd.com/wakingrufus/list/criterion-challenge-2022/") { +"Letterboxd" }
            +" list of all movies watched for the challenge by "
            a(href = "https://bigshoulders.city/@wakingrufus") { +"@wakingrufus" }
            +" and "
            a(href = "https://twitter.com/tprig3") { +"@tprig3" }
            +"."
        }

        htmlSection {
            a { id = "fun" }
            h2 { +"Most Fun" }
            h3 { +"Tompopo (1985)" }
            p {
                strong { +"wakingrufus: " }
                +""""A Fistful of Dollars" brought "Yojimbo" from feudal Japan to the old west, and "Tompopo" brings it back to Japan, 
                    |but this time, a ramen shop. 
                    |The story is also intercut with a series of vignettes that explore how food intersects with all aspects of life. 
                    |Make sure you have access to ramen before seeing this, because you will NEED some after watching""".trimMargin()
            }
            h3 {+"お早よう (Good Morning) (1959)"}
            strong { +"tprig3" }
        }
        htmlSection {
            a { id = "laughcry" }
            h2 { +"You'll Laugh, You'll Cry" }
            h3 { +"Yi Yi" }
            p {
                strong { +"wakingrufus: " }
                +"""Family dramas are not usually my cup of tea, but this is everything I want out of one and much more. 
                    |Its scope is broad. 
                    |It encompasses so many different familial experiences, but all the while, is deeply intimate. 
                    |It hits every note, which makes it perfect for this category.""".trimMargin()
            }
        }
        htmlSection {
            a { id = "relevant" }
            h2 { +"Most Relevant Today" }
            h3 { +"The Parallax View" }
            p {
                strong { +"wakingrufus: " }
                details {
                    summary { +"Spoilers" }
                    +"""This movie has the government military intelligence sub-contracting to a corporation which operates on American soil. 
                    |The protagonist descends into deeper and deeper paranoia in order to try to uncover and stop this deep conspiracy, 
                    |but in the end, everyone is in on it, and there is nothing he can do. 
                    |All of his efforts are erased as he is murdered, then framed up as a scapegoat for an assassination. 
                    |I am sure that this movie was made as a reaction to the JFK assassination, 
                    |but everything about it feels right at home in the present day.""".trimMargin()
                }
            }
            h3 {+"Sorry We Missed You"}
            strong { +"tprig3" }
        }
        htmlSection {
            a { id = "surprising" }
            h2 { +"Most Surprising" }
            p { +"This award goes to the movie that was better than we expected, underrated, or just a great movie that we would have never watched on our own. " }
            h3 { +"The Passion of Joan of Arc" }
            p {
                strong { +"wakingrufus: " }
                +""""The Passion of Joan of Arc" brings unjust political and religious society down to the personal level. 
                    |I did not expect such a vividly personal depiction of oppression out of such an old movie, 
                    |but that is exactly what this film delivers. """.trimMargin()
            }
            h3 { +"Zazie dans le Métro (1960)" }
            p {
                strong { +"tprig3" }
            }
        }
        htmlSection {
            a { id = "disappointing" }
            h2 { +"Most Disappointing" }
            h3 { +"One-Eyed Jacks (1961)" }
            p {
                strong { +"wakingrufus: " }
                +"""The premise has so much promise, and while it is a fairly entertaining movie, 
                    |it failed to explore some of the potentially more nuanced aspects of the story, 
                    |and went on for far too long for just being what it was, which is a fairly straightforward old west story.""".trimMargin()
            }
            h3 {+"Beau Travail"}
            p {
                strong { +"tprig3" }
            }
        }
        htmlSection {
            a { id = "ninth" }
            h2 { +"Bottom of the Ninth Award" }
            p { +"This award goes to a movie that was better made significantly better by the ending." }
            h3 { +"The Soft Skin" }
            p {
                strong { +"wakingrufus: " }
                +"""Most of the movie is just about a guy being a terrible husband and father. 
                    |I don't want to spoil the ending, but even as I saw *the* scene unfold, 
                    |I thought it would be too good to be true to actually happen. 
                    |Then it did and I was """.trimMargin()
                i { + "blown away."}
            }
            h3 { +"La Haine (1995)" }
            p {
                strong { +"tprig3: " }
                +""""It's about a society on its way down. And as it falls,it keeps telling itself: "So far so good... So far so good... So far so good." 
                |It's not how you fall that matters. It's how you land."""".trimMargin()
            }
        }
        htmlSection {
            a { id = "obscure" }
            h2 { +"Most Obscure" }
            h3 { +"Joy Street" }
            p {
                strong { +"wakingrufus: " }
                +"""An extremely expressive portrayal of depression through beautiful animation and music. 
                    |At only ~2,000 views on letterboxd, it ties for the most obscure film we watched this year. 
                    |It is only 24 minutes long, more people should see it.""".trimMargin()
            }
            h3 { +"Deep Crimson" }
            p {
                strong { +"tprig3" }
            }
        }
        htmlSection {
            a { id = "true" }
            h2 { +"Best Based on a True Story" }
            p { +"We watched a lot of movies based on real events this year, so we picked our favorite." }
            h3 { +"Mishima" }
            p {
                strong { +"wakingrufus: " }
                +"""I had not heard of Yukio Mishima prior to watching this movie, 
                    |but I got the sense that he was a larger-than-life figure known for his prolific literary and stage writing. 
                    |However, later in life, he formed a private militia and led a failed coup. 
                    |Naturally, this opens the question of how does someone get from A to B. 
                    |In "Mishima", reenactments of his works and his actual life are sequenced in a way that attempts to explain his motivations, 
                    |through his own internal vernacular. 
                    |We can never truly understand how someone else's mind works, but this film makes us feel like we can.""".trimMargin()

            }
        }
        htmlSection {
            a { id = "dudes" }
            h2 { +"Dudes Rock" }
            p { +"We watched quite a few movies this year that could be labeled as \"dudes rock\" portrayals, so we thought it would be a fun category to have." }
            h3 { +"I Vitelloni" }
            p {
                strong { +"wakingrufus: " }
                +"""While the protagonist (not the narrator) of "I Vitelloni" is a constant scumbag throughout the movie, 
                    |I'd like to bring your attention to his friend group, which is just guys being dudes, all movie long.""".trimMargin()
            }
            h3 { +"Easy Rider" }
            p {
                strong { +"tprig3" }
            }
        }
        footer(myFooter)
    }
}