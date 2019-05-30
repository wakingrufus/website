package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.HtmlPage
import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.sideNav
import kotlinx.html.*


val failAgile: HtmlPage.() -> Unit = {
    article("Why Does Agile Fail?") {
        nav { this.sideNav() }
        content {
            intro(this)
            badAgile(this)
            autonomy(this)
            relationships(this)
            hierarchy(this)
            pace(this)
            alienation(this)
            additionalResources()(this)
        }
    }
}

val intro: DIV.() -> Unit = {
    p {
        +"The "
        a(href = "https://agilemanifesto.org/") { +"Agile Manifesto" }
        +" "
        +"""was published in 2001, and has inspired a number of new software development methodologies.
            Now, nearly all software development shops claim to be agile to some degree.
            However, in almost every organization which attempts to adopt it,
            the implementation falls short of the promise offered by the approach.
            A whole industry of consultants and speakers have emerged trying to pin down how companies can implement agile correctly,
            yet still, it almost never works.
            Even the authors of the manifesto seem consistently frustrated and confused by the refusal of companies to internalize the principles of agile software development.
            This may be due to a mass misunderstanding of what the Agile Manifesto really means.
            Although it explicitly critiques the specific methods of waterfall-style development,
            the criticisms are implicitly critiquing something deeper.
""".trimIndent()
    }
}
val badAgile: DIV.() -> Unit = {
    h3 { +"Dark Agile and Flaccid Agile" }
    p {
        +"""The discussion about the failures of Agile has been far-reaching,
            but some of the Manifesto signatories have termed phrases such as "Dark Agile" or "Flaccid Agile"
            to describe attempted implementations of Agile which fail to live up to the ideals of the manifesto.
            However, most analyses of Agile failure ultimately trace the failure back to a lack of buy-in.
            It is universally recognized that agile only works if you have buy-in across the entire organization, all the way to the top.
            This level of buy-in is almost never achieved, and this is usually what is blamed for the failings of Agile.
            Most people assume, people don't buy into Agile, because they just don't truly understand it, and fail to internalize it.
            But the reason may actually be that management """
        em { +"does" }
        +""" understand what it is, and rejects it outright.
            At the same time, developers mis-understand it, and therefore don't grasp why management refuses to buy in.
            So to understand what is really going on, we need to examine what the manifesto is addressing.
        """.trimIndent()
    }
}

val autonomy: DIV.() -> Unit = {
    p {
        +"""One of the main tenets of the manifesto is "Individuals and interactions over processes and tools".
            This statement is actually addressing autonomy in the workplace.
            If you place trust in your people, and give them the autonomy to execute on what they judge is best,
            you don't need strict processes and limiting tools to direct the work.
    """.trimIndent()
    }
    ul {
        li { +"customer collaboration over contract negotiation" }
        li {
            +"business people and developers must work together daily throught the project"

        }
        li { +"trust" }
        li { +"Work at constant pace" }
    }
}

val relationships: DIV.() -> Unit = {
    p {
        +"""
        the most efficient and effective method of conveying information to and within a development team is face to face conversation
    """.trimIndent()
    }
}

val hierarchy: DIV.() -> Unit = {
    p {
        +"""
            "the best architectures, requirements, and designs emerge from self organizing teams"
        """.trimIndent()
    }
}

val pace: DIV.() -> Unit = {
    p {
        blockQuote {
            +"""Agile processes promote sustainable development.
                The sponsors, developers, and users should be able to maintain a constant pace indefinitely.""".trimIndent()
        }
        +"""The agile manifesto advocates for a steady, sustainable work pace.
            It sells this idea on the principle that if you overwork developers, their output will be of a worse quality, and turnover will be high.
        Of course, this principle also offers protection to developers from miserable working conditions such as crunch time.
        However, agile also promises:
    """.trimIndent()
        blockQuote {
            +"Deliver working software frequently, from a couple of weeks to a couple of months, with a preference to the shorter timescale."
        }
        +"""For profit corporations are always trying to optimise for growth.
            |The wording of the above principle is close enough to 'go faster' that managers are happy to twist its meaning into that,
            |and completely disregard all of the other language regarding sustainable pace.
            |Sustianable pace and unbounded growth are incompatible concepts. """.trimMargin()

    }
}

val alienation: DIV.() -> Unit = {
    p {
        +"""
        This all adds up to a concept known as "Alienation".
        The theoretical basis of alienation within the capitalist mode of production is that the worker invariably loses the ability to determine life and destiny when deprived of the right to think (conceive) of themselves as the director of their own actions; to determine the character of said actions; to define relationships with other people; and to own those items of value from goods and services, produced by their own labour. Although the worker is an autonomous, self-realized human being, as an economic entity this worker is directed to goals and diverted to activities that are dictated by the bourgeoisie—who own the means of production—in order to extract from the worker the maximum amount of surplus value in the course of business competition among industrialists.
    """.trimIndent()
    }
}

fun additionalResources(): DIV.() -> Unit = {
    h2 { +"Additional Resources" }

    p {
        +"Discuss this post on "
        a(href = "https://mastodon.technology/web/statuses/101241850991374660") { +"Mastodon" }
        +"."
    }
}
