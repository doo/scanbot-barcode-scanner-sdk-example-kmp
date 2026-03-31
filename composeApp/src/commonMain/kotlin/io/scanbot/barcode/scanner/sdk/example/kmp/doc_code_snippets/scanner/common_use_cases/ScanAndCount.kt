package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.barcode.BarcodeFormatCommonConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormats
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeItemMapper
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeMappedData
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleBarcodesScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Scan and count")
fun rtuUiScanAndCountUseCase(): BarcodeScannerScreenConfiguration {
    val config = BarcodeScannerScreenConfiguration().apply {

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

            // Implement mapping for the barcode item information.
            barcodeInfoMapping.barcodeItemMapper = BarcodeItemMapper { item, onResult, onError ->
                /**
                 * TODO: process scan result as needed to get your mapped data,
                 * e.g. query your server to get product image, title and subtitle.
                 */
                val title = "Some product ${item.text}"
                val subtitle = item.format.name

                // If image from URL is used, INTERNET permission is required.
                val image = "https://avatars.githubusercontent.com/u/1454920"

                // Call onError() in case of error during obtaining mapped data.
                if (item.text == "Error occurred!") {
                    onError.onError()
                } else {
                    onResult.onResult(
                        BarcodeMappedData(
                            title = title,
                            subtitle = subtitle,
                            barcodeImage = image
                        )
                    )
                }
            }

            // Configure other parameters, pertaining to multiple-scanning mode as needed.
        }

        // Set an array of accepted barcode types.
        scannerConfiguration.barcodeFormatConfigurations = listOf(
            BarcodeFormatCommonConfiguration(formats = BarcodeFormats.common)
        )

        // Configure other parameters as needed.
    }
    return config
}

fun startScanAndCount(
    onResultHandler: (barcodeResult: BarcodeScannerUiResult) -> Unit,
    onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiScanAndCountUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        }
    )
}
// @EndTag("Scan and count")