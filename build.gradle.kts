plugins {
    idea
}

allprojects {
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