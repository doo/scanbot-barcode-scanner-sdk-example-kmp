package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Top Bar")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode

fun rtuUiTopBarConfiguration(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        // Set the top bar mode.
        topBar.mode = TopBarMode.GRADIENT

        // Set the background color which will be used as a gradient.
        topBar.backgroundColor = ScanbotColor("#C8193C")

        // Configure the status bar look. If visible - select DARK or LIGHT according to your app's theme color.
        topBar.statusBarMode = StatusBarMode.HIDDEN

        // Configure the Cancel button.
        topBar.cancelButton.text = "Cancel"
        topBar.cancelButton.foreground.color = ScanbotColor("#FFFFFF")

        // Configure other parameters as needed.
    }

    return configuration
}

fun startTopBarScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiTopBarConfiguration(), onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        })
}
// @EndTag("Top Bar")