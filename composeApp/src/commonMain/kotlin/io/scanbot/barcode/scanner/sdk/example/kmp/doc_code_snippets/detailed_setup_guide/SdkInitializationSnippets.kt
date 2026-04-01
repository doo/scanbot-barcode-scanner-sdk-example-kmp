package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.detailed_setup_guide

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("SDK initialization imports")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration
// @EndTag("SDK initialization imports")


fun basicInitialization() {
    // @Tag("Basic initialization")
    val configuration = SdkConfiguration(
        licenseKey = "YOUR_SCANBOT_SDK_LICENSE_KEY"
    )

    val result = ScanbotSDK.initialize(configuration)

    result.onSuccess { licenseInfo ->
        // SDK initialized successfully
        println("License is valid: ${licenseInfo.isValid}")
    }.onFailure { error ->
        // Handle initialization error
        println("SDK initialization failed: ${error.message}")
    }
    // @EndTag("Basic initialization")
}

fun settingLicenseKye() {
    // @Tag("Setting license key")
    val LICENSE_KEY = "YOUR_SCANBOT_SDK_LICENSE_KEY"

    val configuration = SdkConfiguration(
        licenseKey = LICENSE_KEY
    )

    ScanbotSDK.initialize(configuration)
    // @EndTag("Setting license key")
}

fun checkingLicenseStatus() {
    // @Tag("Checking license status")
    val result = ScanbotSDK.getLicenseInfo()

    result.onSuccess { licenseInfo ->
        if (licenseInfo.isValid) {
            // License is valid, proceed with SDK usage
        } else {
            // License is invalid or expired, disable SDK features
        }
    }
    // @EndTag("Checking license status")
}

const val LICENSE_KEY = "YOUR_SCANBOT_SDK_LICENSE_KEY"

fun enableLogging() {
    // @Tag("Enable logging")
    val configuration = SdkConfiguration(
        licenseKey = LICENSE_KEY,
        loggingEnabled = true,
        enableNativeLogging = true  // Android only
    )
    // @EndTag("Enable logging")
}


fun XNNPACKAcceleration() {
    // @Tag("XNNPACK acceleration")
    val configuration = SdkConfiguration(
        licenseKey = LICENSE_KEY,
        allowXnnpackAcceleration = false
    )
    // @EndTag("XNNPACK acceleration")
}

fun disableGpuAcceleration() {
    // @Tag("Disable GPU acceleration")
    val configuration = SdkConfiguration(
        licenseKey = LICENSE_KEY,
        allowGpuAcceleration = false
    )
    // @EndTag("Disable GPU acceleration")
}

fun performanceHint() {
    // @Tag("Performance hint")
    val configuration = SdkConfiguration(
        licenseKey = LICENSE_KEY,
        performanceHintApi = true
    )
    // @EndTag("Performance hint")
}

fun disableOptimizations() {
    // @Tag("Disable all optimizations")
    val configuration = SdkConfiguration(
        licenseKey = LICENSE_KEY,

        // Disable all Android optimizations
        performanceHintApi = false,
        allowGpuAcceleration = false,
        allowXnnpackAcceleration = false
    )
    // @EndTag("Disable all optimizations")
}
