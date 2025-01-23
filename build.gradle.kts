plugins {
    application // Applying the application plugin
    // id("com.adarshr.test-logger") version "4.0.0" // Correct plugin declaration in Kotlin DSL
}

repositories {
    mavenCentral() // Use Maven Central for resolving dependencies
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.7.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Optional: specify Java version for toolchain
    }
}

application {
    mainClass.set("aoc.App") // Define the main class for the application
}

// testlogger {
//     showPassed = false
//     logLevel = LogLevel.LIFECYCLE
// }

// Use JUnit Platform for unit tests
tasks.named<Test>("test") {
    useJUnitPlatform()
}