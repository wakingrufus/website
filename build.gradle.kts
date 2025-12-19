allprojects {
    group = "com.github.wakingrufus"
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "9.2.1"
    distributionType = Wrapper.DistributionType.BIN
}

subprojects {
    repositories {
        mavenCentral()
    }
}