import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    iosArm64 {
        val path = "$rootDir/scanbotsdk/ScanbotBarcodeScannerSDK.xcframework/ios-arm64"
        compilations.getByName("main") {
            val ScanbotBarcodeScannerSDK by cinterops.creating {
                defFile("src/nativeInterop/cinterop/ScanbotBarcodeScannerSDK.def")
                compilerOpts("-F$path", "-framework", "ScanbotBarcodeScannerSDK", "-rpath", path)
                extraOpts += listOf("-compiler-option", "-fmodules")
            }
        }
        binaries.all {
            linkerOpts("-framework", "ScanbotBarcodeScannerSDK", "-F$path")
        }
    }

    listOf(
        iosX64(),
        iosSimulatorArm64()
    ).forEach {
        val path =
            "$rootDir/scanbotsdk/ScanbotBarcodeScannerSDK.xcframework/ios-arm64_x86_64-simulator"
        it.compilations.getByName("main") {
            val ScanbotBarcodeScannerSDK by cinterops.creating {
                defFile("src/nativeInterop/cinterop/ScanbotBarcodeScannerSDK.def")
                compilerOpts("-F$path", "-framework", "ScanbotBarcodeScannerSDK", "-rpath", path)
                extraOpts += listOf("-compiler-option", "-fmodules")
            }
        }
        it.binaries.all {
            linkerOpts("-framework", "ScanbotBarcodeScannerSDK", "-F$path")
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.scanbot.barcode.scanner.sdk)
            implementation(libs.rtu.ui.v2.barcode)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
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

