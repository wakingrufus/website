plugins {
    kotlin("jvm")
    jacoco
    application
}

application {
    mainClass.set("com.github.wakingrufus.website.MainKt")
}

dependencies {
    implementation(project(":website-dsl"))
    implementation(project(":recipe-dsl"))
    implementation(project(":rss-dsl"))
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("io.github.microutils:kotlin-logging:3.0.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jsoup:jsoup:1.15.3")
    implementation("com.beust:klaxon:5.5")
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("ch.qos.logback:logback-classic:1.5.22")
    implementation("com.rometools:rome:1.10.0")
    implementation("com.vladsch.flexmark:flexmark-all:0.50.16")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.12")
    testImplementation("org.assertj:assertj-core:3.11.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter()
            targets.all {
                testTask.configure {
                    finalizedBy("jacocoTestReport")
                }
            }
        }
    }
}

tasks.getByName<JavaExec>("run") {
    if (project.hasProperty("neocities.apiKey")) {
        args(project.properties.get("neocities.apiKey") ?: "")
    } else if (project.hasProperty("neocities.user")) {
        args(project.properties.get("neocities.user") ?: "", project.properties.get("neocities.password") ?: "")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}