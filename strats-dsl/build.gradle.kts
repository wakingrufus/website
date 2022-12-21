import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    jacoco
    id("com.jfrog.artifactory")
    `maven-publish`
}

dependencies {
    compile("org.slf4j:slf4j-api:1.7.25")
    compile("io.github.microutils:kotlin-logging:1.6.10")

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
    reports {
        xml.isEnabled = true
    }
}

tasks.findByPath("build")?.dependsOn("jacocoTestReport")

publishing {
    publications {
        create<MavenPublication>("mavenJava"){
            from(components["java"])
        }
    }
}

configure<org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention> {
    setContextUrl(System.getenv("int_jfrog_url"))
    publish {
        repository {
            setRepoKey("public")
            setUsername(System.getenv("int_jfrog_user"))
            setPassword(System.getenv("int_jfrog_apikey"))
            setMavenCompatible(true)
        }
        defaults {
            publications("mavenJava")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}