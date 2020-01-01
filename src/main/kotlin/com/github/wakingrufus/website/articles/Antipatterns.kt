package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.lib.sideNavBar
import com.github.wakingrufus.website.myFooter
import kotlinx.html.*

val antipatterns = htmlPage("antipatterns.html") {
    article("Software Development Anti-Patterns") {
        nav {
            sideNavBar {
                li { a(href = "#intro") { +"Introduction" } }
                li { a(href = "#refactoring") { +"Refactoring as a Separate Ticket" } }
            }
        }
        htmlSection {
            a { id = "intro" }
            p {
                +"There are a lot of ways to develop software, and there is no one best way. "
                +"For this reason, there is much literature about how to develop software. "
                +"But with so many subjectively good ways to develop software, it can be easier to just focus on how "
                em { +"not" }
                +" to do it. "
                +"I have identified a number of Software Engineering anti-patterns. "
                +"Note that these are not code design anti-patterns, they are anti-patterns in "
                em { +"how" }
                +" we develop software."
            }
        }
        htmlSection(refactoring)
        footer(myFooter)
    }
}

val refactoring: DIV.() -> Unit = {
    h2 { +"Refactoring as a Separate Ticket" }
    a { id = "refactoring" }
    p {
        +"""
        Refactoring is an important part of software development.
        It is one of the primary ways to repay technical debt, and make sure your design evolves with the product.
        In fact, I have a motto: "Always Be Refactoring", and I define legacy code as code that you are scared to refactor.
        Despite this, many teams adopt the following practice (usually at the behest of management):
        When you are working on a ticket, and you see code which should be refactored,
        put a ticket for the refactor in the backlog, and finish your ticket without doing the refactor.
        There are a few reasons why this is a bad idea:"
        """.trimIndent()
    }
    ol {
        li {
            +"The best time to refactor code is when you are already working on it. "
            +"You have already grokked this code, so you understand how to best refactor it. "
            +" By the time you come back to the ticket, you will have lost this context."
        }
        li {
            +"Oftentimes, a refactor makes sense because the functionality of the code has changed, and you want to change the design to match. "
            +"If you do not change the design when the functionality changed, you have created technical debt, where previously there was none. "
        }
        li {
            +"Paying back technical debt is always cheaper to do sooner rather than later"
        }
        p { +"And the big one:" }
        li {
            +"You will never get to work that technical debt ticket. "
            +"Once in the backlog, the ticket will be subject to the prioritization whims of the business, product owner, project manager, or even development manager. "
            +"People in these roles will never value the work as high as it should be, and often will just deprioritize it indefinitely. "
        }

    }
    p {
        +"This whole practice is a grift to appease the developer by saying \"Sure we will do it, but later\" but without actually doing it."
        +"Be careful not to get overzealous with refactors. "
        +"A good rule of thumb is to only refactor code you are interacting with already. "
        +"But refactoring should be as much a part of your normal development workflow as writing tests."
    }
    p {
        +"Discuss this post on "
        a(href = "https://mastodon.technology/@wakingrufus/100265615075039570") { +"Mastodon" }
        +" or "
        a(href = "https://twitter.com/wakingrufus/status/1011236173774245888") { +"Twitter" }
        +"."
    }
}