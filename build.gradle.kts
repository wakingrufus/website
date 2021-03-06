plugins {
    idea
}

allprojects {
    version = "0.4.0"
    group = "com.github.wakingrufus"
}

idea {
    module {
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel("1.8")
    }
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "6.8.3"
    distributionType = Wrapper.DistributionType.ALL
}

subprojects {
    repositories {
        mavenCentral()
    }
}