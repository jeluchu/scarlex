plugins {
    id("maven-publish")
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.dokka") version "0.10.1"
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
    maven("https://kotlin.bintray.com/ktor")
    maven("https://kotlin.bintray.com/kotlinx")
}

group = "com.jeluchu.scarlex"
version = "1.0.0"

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.gson)
    implementation(libs.bundles.jsoup)
    implementation(libs.bundles.logger)
    testImplementation(libs.jupiter.junit.api)
    testRuntimeOnly(libs.jupiter.junit.engine)
    implementation(libs.bundles.kotlin.coroutines)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }

    test {
        useJUnitPlatform()
    }

    dokka {
        outputFormat = "html"
        outputDirectory = "$rootDir/docs"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.jeluchu"
            artifactId = "scarlex"
            version = "1.0.0"

            from(components["kotlin"])
        }
    }
}