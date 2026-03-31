package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Find And Pick")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.ExpectedBarcode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.FindAndPickScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiFindAndPickScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        // Initialize the use case for find and pick scanning.
        useCase = FindAndPickScanningMode().apply {

            // Set the sheet mode for the barcodes preview.
            sheet.mode = SheetMode.COLLAPSED_SHEET

            // Enable AR Overlay
            arOverlay.visible = true

            // Enable/Disable the automatic selection.
            arOverlay.automaticSelectionEnabled = false

            // Set the height for the collapsed sheet.
            sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE

            // Enable manual count change.
            sheetContent.manualCountChangeEnabled = true

            // Set the delay before same barcode counting repeat.
            countingRepeatDelay = 1000

            // Configure the submit button.
            sheetContent.submitButton.text = "Submit"
            sheetContent.submitButton.foreground.color = ScanbotColor("#000000");

            // Configure other parameters, pertaining to findAndPick-scanning mode as needed.
            // Set the expected barcodes.
            expectedBarcodes = listOf(
                ExpectedBarcode(
                    barcodeValue = "123456",
                    title = "numeric barcode",
                    image = "https://avatars.githubusercontent.com/u/1454920",
                    count = 4
                ),
                ExpectedBarcode(
                    barcodeValue = "SCANBOT",
                    title = "value barcode",
                    image = "https://avatars.githubusercontent.com/u/1454920",
                    count = 3
                ),
            )
        }

        // Configure other parameters as needed.
    }

    return configuration;
}

fun startFindAndPickScanning(
    onResultHandler: (barcodeResult: BarcodeScannerUiResult) -> Unit,
    onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiFindAndPickScanningUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        })
}
// @EndTag("Find And Pick")