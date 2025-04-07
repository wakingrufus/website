pluginManagement{
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
        id("org.jetbrains.kotlin.jvm") version "2.1.20"
        id("com.jfrog.artifactory") version "4.24.12"
    }
}

rootProject.name = "website"

include(":website-dsl")
include(":recipe-dsl")
include(":rss-dsl")
include(":personal-site")

dependencyResolutionManagement {
    versionCatalogs {
        create("kotlinlib"){
            library("html","org.jetbrains.kotlinx","kotlinx-html-jvm").version("0.12.0")
        }
    }
}