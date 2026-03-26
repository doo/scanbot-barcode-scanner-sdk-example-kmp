package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Sheet mode")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiMultipleScanningPreviewConfig(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val config = BarcodeScannerScreenConfiguration().apply {

        // Initialize the use case for multiple scanning.
        useCase = MultipleScanningMode().apply {

            // Set the sheet mode for the barcodes preview.
            sheet.mode = SheetMode.COLLAPSED_SHEET

            // Set the height for the collapsed sheet.
            sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE

            // Configure the submit button on the sheet.
            sheetContent.submitButton.text = "Submit"
            sheetContent.submitButton.foreground.color = ScanbotColor("#000000")
        }
        // Configure localization parameters.
        localization.barcodeInfoMappingErrorStateCancelButton = "Custom Cancel title"
        localization.cameraPermissionCloseButton = "Custom Close title"
        // Configure other strings as needed.

        // Configure other parameters
    }
    return config
}

fun startMultipleScanningPreview() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiMultipleScanningPreviewConfig(), onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        })
}
// @EndTag("Sheet mode")
