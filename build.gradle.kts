plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "day17.MainKt"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
    compilerOptions {
        freeCompilerArgs.add("-Xwhen-guards")
    }
}
