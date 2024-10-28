import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val xcf = XCFramework()
    val frameworkPath = project.file("$rootDir/scanbot_sdk/ScanbotBarcodeScannerSDK.xcframework").absolutePath
    val frameworkPathArm64 = "$frameworkPath/ios-arm64/"
    val frameworkPathSimulator = "$frameworkPath/ios-arm64_x86_64-simulator/"

    fun configureScanbotInterop(target: org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget, frameworkPath: String) {
        target.compilations.getByName("main") {
            val coreScanbot by cinterops.creating {
                defFile("$rootDir/scanbot_sdk/ScanbotBarcodeScannerSDK.def")
                compilerOpts("-framework", "ScanbotBarcodeScannerSDK", "-F$frameworkPath")
                extraOpts += listOf("-compiler-option", "-fmodules")
            }
        }
        target.binaries.all {
            linkerOpts("-framework", "ScanbotBarcodeScannerSDK", "-F$frameworkPath")
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
        iosX64()
    ).forEach { target ->
        val currentFrameworkPath = if (target.name.contains("arm64")) frameworkPathArm64 else frameworkPathSimulator
        configureScanbotInterop(target, currentFrameworkPath)

        target.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            xcf.add(this)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.navigation.compose)
            implementation(libs.scanbot.sdk.compose.multiplatform)
        }
    }
}

android {
    namespace = "io.scanbot.example.kmpbarcodescanner"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "io.scanbot.example.kmpbarcodescanner"
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
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
