allprojects {
    group = "com.github.wakingrufus"
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "8.13"
    distributionType = Wrapper.DistributionType.BIN
}

subprojects {
    repositories {
        mavenCentral()
    }
}