allprojects {
    group = "com.github.wakingrufus"
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "8.6"
    distributionType = Wrapper.DistributionType.ALL
}

subprojects {
    repositories {
        mavenCentral()
    }
}