package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("User guidance")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiUserGuidanceConfig(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        // Hide/unhide the user guidance.
        userGuidance.visible = true

        // Configure the title.
        userGuidance.title.text = "Move the finder over a barcode"
        userGuidance.title.color = ScanbotColor("#FFFFFF")

        // Configure the background.
        userGuidance.background.fillColor = ScanbotColor("#0000007A")

        // Configure other parameters as needed.
    }

    return configuration
}

fun startUserGuidanceScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiUserGuidanceConfig(), onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        })
}
// @EndTag("User guidance")