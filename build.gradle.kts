plugins {
    idea
}

allprojects {
    version = "0.3.0"
    group = "com.github.wakingrufus"
}

idea {
    module {
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel("1.8")
    }
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "5.6.4"
    distributionType = Wrapper.DistributionType.ALL
}
