package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Scanning tiny barcodes")
fun scanTinyBarcodes() {
    // Create the default configuration object.
    val config = BarcodeScannerScreenConfiguration();

    // Enable locking the focus at the minimum possible distance.
    config.cameraConfiguration.minFocusDistanceLock = true;

    // Configure other parameters as needed.

    ScanbotSDK.barcode.startScanner(
        configuration = config,
        onResult = { result ->
            result.onSuccess { TODO("Handle scanned result") }
            result.onFailure { TODO("Handle error") }
        }
    )
}
// @EndTag("Scanning tiny barcodes")