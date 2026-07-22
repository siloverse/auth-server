plugins {
    id("io.github.siloverse.spring-boot-application") version "1.0.2"
}

group = "io.github.siloverse"
version = "0.1.0"

application {
    mainClass.set("io.github.siloverse.ApplicationKt")
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.jackson.module.kotlin)
    testImplementation(libs.testcontainers.postgresql)
    implementation(project(":messages"))
    implementation(project(":ui"))
    implementation(project(":web"))
}
