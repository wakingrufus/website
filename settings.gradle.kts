pluginManagement{
    plugins {
        id("org.jetbrains.kotlin.jvm") version "1.7.20"
        id("com.jfrog.artifactory") version "4.24.12"
    }
}

rootProject.name = "website"

include(":website-dsl")
include(":recipe-dsl")
include(":rss-dsl")
include(":personal-site")
include(":generationq")

dependencyResolutionManagement {
    versionCatalogs {
        create("kotlinlib"){
            library("html","org.jetbrains.kotlinx","kotlinx-html-jvm").version("0.8.1")
        }
    }
}