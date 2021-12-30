plugins {
    idea
}

allprojects {
    version = if (System.getenv("res_website_release_isGitTag") == "true")
        System.getenv("res_website_release_gitTagName") else "0.5.2-SNAPSHOT"
    group = "com.github.wakingrufus"
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "6.9.1"
    distributionType = Wrapper.DistributionType.ALL
}

subprojects {
    repositories {
        mavenCentral()
    }
}