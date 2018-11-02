package com.github.wakingrufus.website

import com.github.wakingrufus.website.lib.content
import com.github.wakingrufus.website.lib.pageTitle
import kotlinx.html.*


fun staticweb(): HTML.() -> Unit = {
    head {
        link(href = Paths.CSS_PATH, rel = "stylesheet")
    }
    body {
        pageTitle("Static Web with Kotlin DSLs")
        sideNav()
        content {
            p {
                +"""
                    The term "static web" refers to a style of web development that might seem out of place in today's
                    modern scene of Javascript frameworks. But it is precisely the proliferation of javascript-based
                    web applications that inspires a reaction to get back to some of the ideals of the web of yesteryear.
                    In this article, we will review the benefits of the static web and look at how Kotlin can bring us
                    some of the benefits of dynamic web programming to the static web world.
                """.trimIndent()
            }
            h2 { +"Documents, not Apps" }
            p {
                +"""
                    The Web was designed to provide documents, in HTML.
                    The addition of Javascript and AJAX to the standard set of web technologies allowed these documents to be dynamic.
                    This opened up the door to a new way to deliver cross-platform application with rich UIs.
                    The JS ecosystem developed around this idea of "Web Applications" in order to make it easier to develop these webapp,
                    and bring along the perks of a true development environment. Pretty soon, this JS ecosystem
                    became so ubiquitous, that nearly all websites on the internet are web applications.
                """.trimIndent()
            }
            p {
                +"""
                    But, this has had some downsides. The amount of data transferred when loading a website has exploded.
                    Sites are built JS libraries which are built on other libraries which are built on others...
                    Many memes have been written about the `node_modules` directory.
                    All of those files are compiled into the application that is delivered as part of a page load.
                    On top of this, sites have stuffed a myriad of trackers and advertisements into their sites in order to monetize you via surveillance capitalism.
                    in addition to the privacy issues with this, it also makes these sites near unusable on slow connections,
                    such as mobile connections or connections in rural areas or developing countries.
                """.trimIndent()
            }
            p {
                +"""
                    Some sites should be webapps, such as eCommerce sites, or web interfaces to applications (think gmail, etc).
                    But most websites are just serving up static content, such as news websites and blogs.
                    Lately, many people have desired a return to the simple websites.
                    With todays technologies, static websites can be extremely fast and reliable.
                    There is a new static webhost called
                """.trimIndent()
            }
            a(href = "http://www.neocites.org") { +"Neocities" }
            p {
                +"""
                    , a name which an homage to the defunct Geocities.
                """.trimIndent()
            }
            p {
                +"""
                    I decided to build myself a personal website to publish my own content to, in order to avoid places like Medium, Twitter, or various Slideshow services.
                    Anyone who has built a site by hand in HTML knows that this is very tedious.
                    There are some frameworks for generating static sites, such as Jekyll and Hugo, which are great for most people.
                    But I am a programmer, and i'd like the control i get from programming, with the benefits of using a framework as well.
                    This is where Kotlin comes in.
                """.trimIndent()
            }
            p {
                +"""
                    This is where Kotlin comes in. Kotlin is a very good language for DSLs. THe Kotlin team has created HTML and CSS DSLs already.
                    Instead of building a DSL from scratch, I decided to extend these DSLs. I will walk you thought how I did this.
                    Along the way, we will see how DSLs are built, using the HTML DSL as a model example,
                    then how to write our own DSLs by extending the HTML DSL.
                """.trimIndent()
            }
        }
    }
}