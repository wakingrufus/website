allprojects {
    group = "com.github.wakingrufus"
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "8.1.1"
    distributionType = Wrapper.DistributionType.ALL
}

subprojects {
    repositories {
        mavenCentral()
    }
}