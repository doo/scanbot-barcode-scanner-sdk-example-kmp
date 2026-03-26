package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Multiple scanning")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleBarcodesScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiMutliScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        // Initialize the use case for multiple scanning.
        useCase = MultipleScanningMode().apply {

            // Set the counting mode.
            mode = MultipleBarcodesScanningMode.COUNTING

            // Set the sheet mode for the barcodes preview.
            sheet.mode = SheetMode.COLLAPSED_SHEET

            // Set the height for the collapsed sheet.
            sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE

            // Enable manual count change.
            sheetContent.manualCountChangeEnabled = true

            // Set the delay before same barcode counting repeat.
            countingRepeatDelay = 1000

            // Configure the submit button.
            sheetContent.submitButton.text = "Submit"
            sheetContent.submitButton.foreground.color = ScanbotColor("#000000")

            // Configure other parameters as needed.
        }
    }
    return configuration
}

fun startMultiScanning(
    onResultHandler: (barcodeResult: BarcodeScannerUiResult) -> Unit,
    onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiMutliScanningUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        })
}
// @EndTag("Multiple scanning")