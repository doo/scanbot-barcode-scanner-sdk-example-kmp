package io.scanbot.barcode.sdk.example.kmp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.scanbot.sdk.kmp.ScanbotSDK

@Composable
fun LicenseGuard(
    content: @Composable (checkLicense: (() -> Unit) -> Unit) -> Unit
) {
    var licenseError by rememberSaveable { mutableStateOf<String?>(null) }

    val checkLicense: (() -> Unit) -> Unit = { action ->
        ScanbotSDK.getLicenseInfo().fold(onSuccess = { info ->
            if (info.isValid) action()
            else licenseError = info.licenseStatusMessage
        }, onFailure = { licenseError = "Error getting license info. ${it.message}" })
    }

    content(checkLicense)

    licenseError?.let {
        ErrorDialog("License Error", "Your Scanbot SDK license is not valid. $it") {
            licenseError = null
        }
    }
}