import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("io.github.microutils:kotlin-logging:1.6.10")
    api("com.rometools:rome:1.10.0")
    api(kotlinlib.html)

    testImplementation("org.slf4j:slf4j-log4j12:1.7.25")
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
}

tasks.findByPath("build")?.dependsOn("jacocoTestReport")

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}