package io.scanbot.barcode.sdk.example.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.scanbot.barcode.sdk.example.kmp.navigation.NavigationRoot
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration

const val SCANBOT_SDK_LICENSE_KEY = "" // TODO: Replace with your Scanbot SDK license key

@Composable
fun App() {
    MaterialTheme {
        LaunchedEffect(Unit) {
            val config = SdkConfiguration(
                licenseKey = SCANBOT_SDK_LICENSE_KEY,
                loggingEnabled = true, // Consider switching logging OFF in production builds for security and performance reasons!
            )

            ScanbotSDK.initialize(config).onSuccess { licenseInfo ->
                println("Scanbot SDK initialized successfully. License status: ${licenseInfo.status}")
            }.onFailure { error ->
                println("Error initializing Scanbot SDK: ${error.message}")
            }
        }

        NavigationRoot()
    }
}

