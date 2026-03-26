package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Localization")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration

fun rtuUiLocalizationConfig(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        // Configure localization parameters.
        localization.barcodeInfoMappingErrorStateCancelButton = "Custom Cancel title"
        localization.cameraPermissionCloseButton = "Custom Close title"

        // Configure other strings as needed.
    }
    return configuration
}

fun startLocalizationScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiLocalizationConfig(), onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        })
}
// @EndTag("Localization")