package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.a
import kotlinx.html.blockQuote
import kotlinx.html.h3
import kotlinx.html.p

val coHost = htmlPage("cohost.html") {
    article("Concerns about Cohost") {
        htmlSection {
            h3 { +"November, 2022" }
            p {
                +"""With the recent buyout of Twitter by Elon Musk, many people have been discovering Mastodon/Fediverse.
        |I have been on the Fediverse since 2017, and I am a big fan of it, so I am glad that so many others are starting to discover its merits.
        |A lot of people have put in a lot of hard work to make this possible.
        |
        |There are other places people are looking for something to fill the void of Twitter as well, one of them being Cohost.
        |At first, I didn't like the idea of Cohost, but because I am on fediverse, so I am biased toward that.
        |But through the efforts of """.trimMargin()
                a(href = "https://weirder.earth/@noracodes") { +"Nora Tindall" }
                +", I now realize there are some very legitimate concerns with Cohost. I wanted to collect those here for easy reference."
            }
            h3 { +"It is centralized" }
            p {
                +"The people building Cohost seem like good people, with their hearts in the right place. "
                +"However, being centralized leaves it open to the same fate as Twitter. "
                +"If ownership changes hands, or the owners have a change of heart, it can immediately lose everything about it that makes it better than other options."
            }
            h3 { +"Immature" }
            p {
                +"It is "
                a(href = "https://weirder.earth/@noracodes/109254278510144219") { +"lacking" }
                +" many of the features fediverse has had for years, and it is unclear how a small team such as theirs can make up this gap."
            }
            h3 { +"Claims of non-profit" }
            p {
                +"""
                They claim to be "non-profit", but are just an LLC. According to Colin/vogon:
            """.trimIndent()
            }
            blockQuote {
                +"by \"not-for-profit\" we mean that we intend to, practically, operate as a company that does not retain profits net of operating expenses, similar to e.g. a credit union (our corporate bank is a credit union which is also incorporated as a Washington LLC.)  as per our latest financial report (https://cohost.org/staff/post/121495-september-2022-finan), we currently do not turn a profit, nor are we even close yet, so we have no firm plans for post-profit operation, but in broad strokes we plan to either reduce our revenue or donate the excess to upstream contributors or charities.  we are also open to formally reincorporating as a nonprofit once we have the legal and tax compliance resources."
            }
            h3 { +"Governance" }
            p {
                +"Their governance is "
                a(href = "https://twitter.com/joepie91/status/1542210890627518464") { +"not thought out at all" }
            }
            h3 { +"Claims of Independence" }
            p {
                +"""They claim they are not beholden to VC, but their source code is the """
                a(href = "https://twitter.com/vogon/status/1585722087033147392") { +"collateral" }
                +" for their business loan."
            }
            h3 { +"Not Open Source" }
            p {
                +"They are not open source, so as for what their software does, we can only take them at their word. "
                +"They also "
                a(href = "https://twitter.com/vogon/status/1585722087033147392") { +"can't" }
                +" open-source it, because the source is the collateral for their loan, as mentioned above."
            }
            h3 { +"Higher costs" }
            p {
                +"Cohost has higher costs than fediverse implementations because they can't use any of the free tooling available to open source projects, such as infrastructure and bug trackers. "
                +"This is important because without a reduction in costs, "
                a(href = "https://cohost.org/noracodes/post/169186-october-update") { +"Cohost isn't sustainable" }
                +" at any scale."
            }
        }
        footer(myFooter)
    }
}