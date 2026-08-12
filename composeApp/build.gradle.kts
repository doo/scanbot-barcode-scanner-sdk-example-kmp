@file:OptIn(ExperimentalSpmForKmpFeature::class)

import io.github.frankois944.spmForKmp.swiftPackageConfig
import io.github.frankois944.spmForKmp.utils.ExperimentalSpmForKmpFeature
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.swift.packagemanager)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }

        iosTarget.swiftPackageConfig {
            minIos = "13.0"
            dependency {
                remotePackageVersion(
                    url = uri("https://github.com/doo/scanbot-barcode-scanner-sdk-ios-spm.git"),
                    version = libs.versions.scanbot.sdk.ios.barcode.get(),
                    products = {
                        add("ScanbotBarcodeScannerSDK", exportToKotlin = true)
                    },
                )
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.components.uitoolingpreview)
            implementation(libs.compose.material.icons.extended)
            implementation(libs.filekit.core)
            implementation(libs.filekit.dialogs.compose)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.jetbrains.compose.navigation)
            implementation(libs.scanbot.sdk)
            implementation(libs.scanbot.compose.ui)
            implementation(libs.image.picker)
            implementation(libs.moko.permissions)
            implementation(libs.moko.permissions.compose)
            implementation(libs.moko.permissions.camera)
        }
    }
}

android {
    namespace = "io.scanbot.barcode.scanner.sdk.example.kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.scanbot.barcode.scanner.sdk.example.kmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.ui.tooling)
}