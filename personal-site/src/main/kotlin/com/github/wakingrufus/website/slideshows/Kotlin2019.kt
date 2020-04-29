package com.github.wakingrufus.website.slideshows

import com.github.wakingrufus.website.MyStyles
import com.github.wakingrufus.website.Paths
import com.github.wakingrufus.website.lib.CssStringPage
import com.github.wakingrufus.website.lib.Website
import com.github.wakingrufus.website.lib.code.*
import com.github.wakingrufus.website.lib.slides.slide
import com.github.wakingrufus.website.lib.slides.slideCode
import com.github.wakingrufus.website.lib.slides.slideList
import com.github.wakingrufus.website.lib.slides.slideshowTitleFooterLine
import kotlinx.html.CODE
import kotlinx.html.a
import kotlinx.html.li

/**
 * Abstract:
 * A brief review of the new features and developments this year in Kotlin.
 * This will cover:
 * - Gradle Multiplatform projects
 * - Improvements for inline classes
 * - Operations for unsigned number arrays
 * - Experimental functions in the standard library
 * - Accessing the reified type using reflection on JVM
 * - Functions for bit manipulation
 * - Duration and Time Measurement API
 * - Null-check optimizations planned for Kotlin 1.4
 * - Coroutine Flows
 */

fun kotlin2019Slideshow(): Website.() -> Unit = {
    slideshow(
            name = Paths.KOTLIN_2019_SLIDESHOW_BASE_NAME,
            rootCss = CssStringPage(Paths.SLIDESHOW_CSS_PATH, MyStyles().slideShowStyles())) {
        titleSlide(title = "Kotlin in 2019", block = kotlin2019TitleSlide)
        slide(title = "Multiplatform Overhaul", subTitle = "Kotlin 1.3.20", block = multiplatform)
        slide(title = "Improved Inline Classes", subTitle = "Kotlin 1.3.20", block = improvedInlineClasses)
        slide(title = "Operations for unsigned number arrays", subTitle = "Kotlin 1.3.30", block = operationsForUnsignedNumberArrays)
        slide(title = "typeOf", subTitle = "Kotlin 1.3.40", block = typeOf)
        slide(title = "Gradle NPM Support", subTitle = "Kotlin 1.3.40", block = gradleNpmSupport)
        slide(title = "Bit Manipulation", subTitle = "Kotlin 1.3.50", block = bitManipulation)
        slide(title = "Duration and Time Measurement API", subTitle = "Kotlin 1.3.50", block = durationApi)
        slide(title = "New Null Checks", subTitle = "Kotlin 1.3.60 (1.4 preview)", block = newNullChecks)
        slide(title = "break and continue in when", subTitle = "Kotlin 1.3.60 (1.4 preview)", block = updatesToWhen)
        slide(title = "Flows", subTitle = "kotlinx.coroutines 1.2.1", block = flows)
    }
}

val kotlin2019TitleSlide = slide {
    titleFooter {
        slideshowTitleFooterLine { +"John Burns" }
        slideshowTitleFooterLine { +"wakingrufus@gmail.com" }
        slideshowTitleFooterLine {
            a(href = "http://wakingrufus.neocities.org") { +"http://wakingrufus.neocities.org" }
        }
    }
}

val multiplatform = slide {
    slideContent {
        slideCode {
            call("plugins") {
                lambda {
                    expression {
                        on({
                            call("kotlin") {
                                argument { string("multiplatform") }
                            }
                        }) {
                            call("version") {
                                infix()
                                argument { string("1.3.20") }
                            }
                        }
                    }
                }
            }
            +"\n"
            call("kotlin") {
                lambda {
                    call("jvm")
                    call("sourceSets") {
                        lambda {
                            assignment(modifier = "val", name = "commonMain", operator = "by") {
                                call("getting") {
                                    lambda {
                                        call("dependencies") {
                                            lambda {
                                                call("implementation") {
                                                    argument {
                                                        call("kotlin") {
                                                            argument { string("stdlib-common") }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

val improvedInlineClasses = slide {
    slideContent {
        slideList {
            li { +"Define an inner class inside an inline class" }
            li { +"Using inline functions inside inline classes" }
            li { +"Passing references to inline classes as arguments to inline functions" }
        }
    }
}

val operationsForUnsignedNumberArrays = slide {
    slideContent {
        slideList {
            li { +"filter, map, all, max, min, etc" }
            li { +"No more object overhead" }
        }
    }
}

val typeOf = slide {
    slideContent {
        slideList { li { +"Accessing the reified type using reflection on JVM" } }
        slideCode {
            declareFunction(name = "accessReifiedTypeArg") {
                annotation("UseExperimental") {
                    argument {
                        +"ExperimentalStdlibApi::class"
                    }
                }
                this.isInfix = true
                this.genericType = "reified T"
                body {
                    val kType = assignment(modifier = "val", name = "kType") {
                        call(name = "typeOf") {
                            genericType = "T"
                        }
                    }
                    call(name = "println") {
                        argument(value = kType.call("toString"))
                    }
                }
            }
        }
    }
}

val gradleNpmSupport = slide {
    slideContent {
        slideCode {
            call(name = "dependencies") {
                lambda {
                    call(name = "implementation") {
                        argument {
                            call(name = "npm") {
                                argument { string("react") }
                                argument { string("16.8.3") }
                            }
                        }
                    }
                }
            }
        }
    }
}

val bitManipulation = slide {
    slideContent {
        slideCode {
            declareFunction(name = "main") {
                annotation(name = "UseExperimental") {
                    argument { +"ExperimentalStdlibApi::class" }
                }
                body {
                    assignment(modifier = "val", name = "number") {
                        on({ string("1010000") }) {
                            call(name = "toInt") {
                                argument(name = "radix") {
                                    number(2)
                                }
                            }
                        }
                    }
                    call(name = "println") {
                        argument { on({ +"number" }) { call("countOneBits") } }
                    }
                    call(name = "println") {
                        argument { on({ +"number" }) { call("countTrailingZeroBits") } }
                    }
                    call(name = "println") {
                        argument {
                            on({ +"number" }) {
                                call("takeHighestOneBit")
                                call("toString") { argument { number(2) } }
                            }
                        }
                    }
                    call(name = "println") {
                        argument {
                            on({ +"number" }) {
                                call("rotateRight") { argument { number(3) } }
                                call("toString") { argument { number(2) } }
                            }
                        }
                    }
                    call(name = "println") {
                        argument {
                            on({ +"number" }) {
                                call("rotateLeft") { argument { number(3) } }
                                call("toString") { argument { number(2) } }
                            }
                        }
                    }
                }
            }
        }
    }
}

val durationApi = slide {
    slideContent {
        slideCode {
            keyword("import")
            +" kotlin.time.*"
            +"\n\n"
            declareFunction(name = "main") {
                annotation(name = "UseExperimental") {
                    argument { +"ExperimentalTime::class" }
                }
                body {
                    assignment(modifier = "val", name = "(value, duration)", format = CODE::plain) {
                        call("measureTimedValue") {
                            lambda {
                                statement {
                                    on({ +"Thread" }) {
                                        call("sleep") {
                                            argument { number(100) }
                                        }
                                    }
                                }
                                statement {
                                    expression {
                                        inline { number(42) }
                                    }
                                }
                            }
                        }
                    }
                    statement { comment("42") }
                    call("println") { argument { +"number" } }

                    statement { comment("e.g. 103 ms") }
                    call("println") { argument { +"duration" } }
                }
            }
        }
    }
}

val newNullChecks = slide {
    slideContent {
        slideList {
            li { +"no more: KotlinNullPointerException, IllegalStateException, IllegalArgumentException, TypeCastException" }
            li {
                +"Now: java.lang.NullPointerException with message: JavaCode.getNull() must not be null"
            }
        }
    }
}

val updatesToWhen = slide {
    slideContent {
        slideCode {
            kotlin {
                statement {
                    comment("process(listOf(1,2,3,0,4,-1,10)) == 1234")
                }
            }
            declareFunction(name = "process") {
                parameter(name = "list", type = "List<Int>")
                body {
                    forLoop("i") {
                        collectionExpression { inline { +"list" } }
                        logicBlock {
                            whenExpression {
                                subject { +"i" }
                                pair({ number(0) }, { keyword("continue") })
                                pair({ number(-1) }, { keyword("break") })
                                pair({ keyword("else") }, {
                                    call("print") {
                                        argument { +"i" }
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

val flows = slide {
    splitSlide({
        slideList {
            li { +"High level API on top of coroutines and channels" }
            li { +"Similar to Rx Streams" }
        }
    }) {
        slideCode {
            declareFunction(name = "foo", returnType = "Flow<Int>") {
                expression2 {
                    call(name = "flow") {
                        lambda {
                            forLoop(iterator = "i") {
                                collectionExpression { range(1, 3) }
                                logicBlock {
                                    call(name = "delay") {
                                        argument { number(100) }
                                    }
                                    call(name = "emit") {
                                        argument { +"i" }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            declareFunction(name = "main") {
                expression2 {
                    call(name = "runBlocking<Unit>") {
                        lambda {
                            expression {
                                on({ call(name = "foo") }) {
                                    call(name = "collect") {
                                        lambda {
                                            inline = true
                                            lambdaParameters = "value"
                                            call(name = "println") {
                                                argument { +"value" }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
