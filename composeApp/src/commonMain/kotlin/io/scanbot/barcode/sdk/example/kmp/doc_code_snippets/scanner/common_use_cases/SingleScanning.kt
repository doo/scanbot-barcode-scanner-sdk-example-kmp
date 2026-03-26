package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Single scanning mode")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SingleScanningMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiSingleScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        scannerConfiguration.returnBarcodeImage = true

        // Single-scanning mode
        useCase = SingleScanningMode().apply {

            // Enable confirmation sheet
            confirmationSheetEnabled = true
            sheetColor = ScanbotColor("#FFFFFF")

            // Show barcode image preview
            barcodeImageVisible = true

            // Title config
            barcodeTitle.visible = true
            barcodeTitle.color = ScanbotColor("#000000")

            // Subtitle config
            barcodeSubtitle.visible = true
            barcodeSubtitle.color = ScanbotColor("#000000")

            // Cancel button config
            cancelButton.text = "Close"
            cancelButton.foreground.color = ScanbotColor("#C8193C")
            cancelButton.background.fillColor = ScanbotColor("#00000000")

            // Submit button config
            submitButton.text = "Submit"
            submitButton.foreground.color = ScanbotColor("#FFFFFF")
            submitButton.background.fillColor = ScanbotColor("#C8193C")
        }
    }
    return configuration
}

fun startSingleScanning(
    onResultHandler: (barcodeResult: BarcodeScannerUiResult) -> Unit,
    onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiSingleScanningUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        })
}
// @EndTag("Single scanning mode")