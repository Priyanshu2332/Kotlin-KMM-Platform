plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.0"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {

            implementation("io.ktor:ktor-client-core:2.3.2")
            implementation("io.ktor:ktor-client-cio:2.3.2")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7" )
            implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7" )
            implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")


// Kotlin Serialization dependencies
            implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

// Ktor JSON serialization feature
            implementation( "io.ktor:ktor-client-json:2.1.0")
            implementation ("io.ktor:ktor-client-serialization:2.1.0")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.kmmgithubcommits"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation(libs.androidx.media3.common.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.android)
}
