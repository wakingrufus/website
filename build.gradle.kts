plugins {
    idea
}

allprojects {
    version = "0.4.1"
    group = "com.github.wakingrufus"
}

idea {
    module {
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel("1.8")
    }
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "6.9"
    distributionType = Wrapper.DistributionType.ALL
}

subprojects {
    repositories {
        mavenCentral()
    }
}