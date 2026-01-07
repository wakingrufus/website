package com.github.wakingrufus.website.articles

import com.github.wakingrufus.website.lib.article.article
import com.github.wakingrufus.website.lib.code.FUNCTION
import com.github.wakingrufus.website.lib.code.block
import com.github.wakingrufus.website.lib.code.call
import com.github.wakingrufus.website.lib.code.kotlin
import com.github.wakingrufus.website.lib.code.number
import com.github.wakingrufus.website.lib.code.on
import com.github.wakingrufus.website.lib.code.plain
import com.github.wakingrufus.website.lib.code.sampleCode
import com.github.wakingrufus.website.lib.code.string
import com.github.wakingrufus.website.lib.htmlPage
import com.github.wakingrufus.website.myFooter
import kotlinx.html.ARTICLE
import kotlinx.html.CODE
import kotlinx.html.a
import kotlinx.html.p

val calculateValueForNumberFunction: FUNCTION.() -> Unit = {
    val numberParam = parameter(name = "number", type = "Int")
    body {
        val sb = assignment(modifier = "val", name = "sb", format = CODE::plain) {
            call(name = "StringBuilder")
        }

        ifStatement {
            conditionExpression {
                inline(numberParam.call(callName = "%") {
                    infix()
                    argumentExp {
                        on({ number(3) }) {
                            call("==") {
                                infix()
                                argument { number(0) }
                            }
                        }
                    }
                })
            }
            trueExpression {
                block {
                    statement {
                        inlineExpression(sb.call(callName = "append") {
                            argument { string("FIZZ") }
                        })
                    }
                }
            }
        }
        ifStatement {
            conditionExpression {
                inline(numberParam.call(callName = "%") {
                    infix()
                    argumentExp {
                        on({ number(5) }) {
                            call("==") {
                                infix()
                                argument { number(0) }
                            }
                        }
                    }
                })
            }
            trueExpression {
                block {
                    statement {
                        inlineExpression(sb.call(callName = "append") {
                            argument { string("BUZZ") }
                        })
                    }
                }
            }
        }
        ifStatement {
            conditionExpression {
                inline(sb.call(callName = "isEmpty"))
            }
            trueExpression {
                block {
                    statement {
                        inlineExpression(sb.call(callName = "append") {
                            argument { +"number" }
                        })
                    }
                }
            }
        }
        returns {
            inline(sb.call(callName = "toString"))
        }
    }
}

val dependencyInjection: ARTICLE.() -> Unit = {
    p {
        +"""
Dependency injection is a pattern very commonly used in Object-Oriented Java. 
It is sometimes done using frameworks such as Dagger, Spring, or Guice, 
and sometimes it is as simple as making sure all dependencies of a class are provided through the constructor. 
One of the major benefits of dependency injection is that it gives you the ability to isolate a class for testing by injecting mocks. 
For example:
""".trimIndent()
    }
    sampleCode {
        kotlin {
            declareClass(name = "FizzBuzzRunner", propsOnSeparateLines = false) {
                property(modifier = "val", name = "linePrinter", type = "PrintStream") {
                    on({ +"System" }) {
                        property(name = "out")
                    }
                }
                function(name = "fizzBuzz", paramsOnSeparateLines = false) {
                    parameter(name = "limit", type = "Int")
                    body {
                        statement {
                            forLoop(iterator = "i", collection = "1..limit") {
                                statement {
                                    on({ +"linePrinter" }) {
                                        call("println") {
                                            argument { call("calculateValueForNumber") { argument { +"i" } } }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                function(name = "calculateValueForNumber", paramsOnSeparateLines = false) {
                    calculateValueForNumberFunction()
                }
            }
        }
    }
    p { +"FizzBuzzRunner will print to System.out in production, but we can inject a mock PrintStream for testing." }
    p {
        +"""
If you are like most Kotlin developers, you come from an Object-Oriented Java background.
You most likely start out by writing Object-Oriented Kotlin that looks a lot like Java.
As you became more comfortable, you might start taking advantage of Kotlin’s ability to be functional. 
This means writing first-class functions, not classes. 
Functional programming has led me to rethink my approaches to software design, for the better. 
However, I soon realized that as great as pure functions are, 
at some point you have to compose them into higher level program functions, or some kind of procedural flow. 
Functions will call other functions. When one function calls another, how can you test the caller function?
    """.trimIndent()
    }
    p { +"Our FizzBuzz code will now look like this:" }
    sampleCode {
        kotlin {
            declareFunction(name = "fizzBuzz") {
                parameter(name = "limit", type = "Int")
                body {
                    statement {
                        forLoop(iterator = "i", collection = "1..limit") {
                            call("print") {
                                argument { call("calculateValueForNumber") { argument { +"i" } } }
                            }
                        }
                    }
                }
            }
            declareFunction(name = "calculateValueForNumber") {
                calculateValueForNumberFunction()
            }
            declareFunction(name = "print") {
                parameter(name = "string", type = "String")
                body {
                    statement {
                        on({ +"System" }) {
                            property(name = "out")
                            call(name = "println") {
                                argument { +"string" }
                            }
                        }
                    }
                }
            }
        }
    }
    p {
        +"""
In this case, we have no way to mock out the actual printing function. 
One approach is to just let that function make it’s calls, and test it as a component-style test. 
But when it is calling something at the system boundary, like a database call, or, in this case, I/O, that will not work so well. 
So I took another approach. 
In addition to my function’s inputs, it will also take functional arguments for any other functions it needs to invoke. 
These parameters will have a default value of a static method reference to the function it will call, 
so that my production code looks the same, and these dependencies will not need to be passed all the way down from my main method. 
However, for testing, I can pass in a lambda for that parameter as a mock, and in this way, I can isolate my function. 
This approach is known as "Ad-hoc Polymorphism". 
The fizzBuzz function now looks like this:
    """.trimIndent()
    }
    sampleCode {
        kotlin {
            declareFunction(name = "fizzBuzz") {
                parameter(name = "limit", type = "Int")
                parameter(name = "printer", type = "(String) -> Unit") {
                    +"::print"
                }
                body {
                    statement {
                        forLoop(iterator = "i", collection = "1..limit") {
                            statement {
                                on({ +"printer" }) {
                                    call("invoke") {
                                        argument { call("calculateValueForNumber") { argument { +"i" } } }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    p { +"We can invoke it in production code just the same:" }
    sampleCode {
        call("fizzBuzz") {
            argument { number(4) }
        }
    }
    p { +"And it can be isolated for testing like this:" }
    sampleCode {
        kotlin {
            declareFunction(name = "test") {
                body {
                    val outputList = assignment(modifier = "val", name = "outputList") {
                        call(name = "ArrayList<String>")
                    }
                    call(name = "fizzBuzz") {
                        argument { number(6) }
                        argument {
                            block(inline = true) {
                                expression(outputList.call(callName = "add") {
                                    argument { +"it" }
                                })
                            }
                        }
                    }
                    statement {
                        on({
                            call(name = "assertThat") {
                                argument { +"outputList[2]" }
                            }
                        }) {
                            call(name = "isEqualTo") {
                                argument { string("FIZZ") }
                            }
                        }
                    }
                }
            }
        }
    }
    p {
        +"""
The beauty of this approach is that it can also be used in your object-oriented “edge” code 
to inject pure “core” functions into your objects which call them. 
For example:
"""
    }
    sampleCode {
        kotlin {
            declareClass(name = "FizzBuzzRunner") {
                val printer = property(modifier = "private val", name = "printer", type = "(String) -> Unit") {
                    +"::print"
                }
                val calculator = property(modifier = "private val", name = "calculator", type = "(Int) -> String") {
                    +"::calculateForNumber"
                }
                function(name = "fizzBuzz", paramsOnSeparateLines = false) {
                    parameter(name = "limit", type = "Int")
                    body {
                        statement {
                            forLoop(iterator = "i", collection = "1..limit") {
                                statement {
                                    inlineExpression(printer.call(callName = "invoke") {
                                        argument(value = calculator.call(callName = "invoke") {
                                            argument { +"i" }
                                        })
                                    })
                                }
                            }
                        }
                    }
                }
                function(name = "print", paramsOnSeparateLines = false) {
                    parameter(name = "string", type = "String")
                    body {
                        statement {
                            on({ +"System" }) {
                                property(name = "out")
                                call(name = "println") {
                                    argument { +"string" }
                                }
                            }
                        }
                    }
                }
                function(name = "calculateValueForNumber", paramsOnSeparateLines = false) {
                    calculateValueForNumberFunction()
                }
            }
        }
    }
    p {
        +"""
This is a simple pattern I have found to write more testable functional Kotlin, and I hope it is useful to others.
    """.trimIndent()
    }
    p {
        +"Discuss this post on "
        a(href = "https://mastodon.technology/@wakingrufus/103862936151360467") { +"Mastodon" }
        +" or "
        a(href = "https://twitter.com/wakingrufus/status/1241462314269843456") { +"Twitter" }
        +"."
    }
}

val adhocPolymorphism = htmlPage("adhoc-polymorphism.html") {
    article("Testing Functional Kotlin") {
        date("March 21, 2020")
        htmlSection(dependencyInjection)
        footer(myFooter)
    }
}
