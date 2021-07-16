import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.60"
    jacoco
    application
}

application {
    mainClassName = "com.github.wakingrufus.website.MainKt"
}

dependencies {
    implementation(project(":website-dsl"))
    implementation(project(":recipe-dsl"))
    implementation(project(":rss-dsl"))
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("io.github.microutils:kotlin-logging:1.6.10")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jsoup:jsoup:1.10.3")
    implementation("com.beust:klaxon:5.5")
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("org.slf4j:slf4j-log4j12:1.7.25")
    implementation("com.rometools:rome:1.10.0")
    implementation("com.vladsch.flexmark:flexmark-all:0.50.16")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.12")
    testImplementation("org.assertj:assertj-core:3.11.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

val testTask = tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(testTask)
    reports {
        xml.isEnabled = true
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.languageVersion = "1.4"
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
}

tasks.findByPath("build")?.dependsOn("jacocoTestReport")

tasks.getByName<JavaExec>("run") {
    if (project.hasProperty("neocities.apiKey")) {
        args(project.properties.get("neocities.apiKey"))
    } else if (project.hasProperty("neocities.user")) {
        args(project.properties.get("neocities.user"), project.properties.get("neocities.password"))
    }
}