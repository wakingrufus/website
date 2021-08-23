plugins {
    idea
}

allprojects {
    version = if (System.getenv("res_website_release_isGitTag") == "true")
        System.getenv("res_website_release_gitTagName") else "0.5.0-SNAPSHOT"
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