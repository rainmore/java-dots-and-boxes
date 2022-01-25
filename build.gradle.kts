plugins {
    java
    application
    idea
}

group = "au.com.rainmore"
version = "1.0.0"
description = "Coding Assignment — Dots and Boxes"

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set(listOf(project.group.toString(), "Application").joinToString("."))
}

dependencies {
    implementation("org.slf4j:slf4j-api:${project.properties["slf4j.version"]!!.toString()}")
    implementation("ch.qos.logback:logback-classic:${project.properties["logback.version"]!!.toString()}")
    implementation("ch.qos.logback:logback-core:${project.properties["logback.version"]!!.toString()}")

    testImplementation(platform("org.junit:junit-bom:${project.properties["junit.version"]!!.toString()}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}