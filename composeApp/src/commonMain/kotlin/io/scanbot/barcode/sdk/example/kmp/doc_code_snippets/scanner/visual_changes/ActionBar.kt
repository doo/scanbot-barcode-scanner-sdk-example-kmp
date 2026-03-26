package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Action Bar")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiActionBarConfiguration(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val config = BarcodeScannerScreenConfiguration().apply {

        // Hide/unhide the flash button.
        actionBar.flashButton.visible = true

        // Configure the inactive state of the flash button.
        actionBar.flashButton.backgroundColor = ScanbotColor("#0000007A")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")

        // Configure the active state of the flash button.
        actionBar.flashButton.activeBackgroundColor = ScanbotColor("#FFCE5C")
        actionBar.flashButton.activeForegroundColor = ScanbotColor("#000000")

        // Hide/unhide the zoom button.
        actionBar.zoomButton.visible = true

        // Configure the inactive state of the zoom button.
        actionBar.zoomButton.backgroundColor = ScanbotColor("#0000007A")
        actionBar.zoomButton.foregroundColor = ScanbotColor("#FFFFFF")
        // Zoom button has no active state - it only switches between zoom levels (for configuring those please refer to camera configuring).

        // Hide/unhide the flip camera button.
        actionBar.flipCameraButton.visible = true

        // Configure the inactive state of the flip camera button.
        actionBar.flipCameraButton.backgroundColor = ScanbotColor("#0000007A")
        actionBar.flipCameraButton.foregroundColor = ScanbotColor("#FFFFFF")
        // Flip camera button has no active state - it only switches between front and back camera.

        // Configure other parameters as needed.
    }
    return config
}

fun startActionBarScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiActionBarConfiguration(), onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        })
}
// @EndTag("Action Bar")