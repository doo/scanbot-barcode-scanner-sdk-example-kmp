package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Mapping item scanning")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeItemMapper
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeMappedData
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode

fun rtuUiMappingItemScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        // Initialize the use case for single scanning.
        useCase = MultipleScanningMode().apply {

            // Set the item mapper.
            barcodeInfoMapping.barcodeItemMapper = BarcodeItemMapper { item, onResult, onError ->

                // This is a simple example, you can choose your own mapping logic here, e.g. based on the barcode format or value.
                if (item.text.isNotEmpty()) {
                    onResult.onResult(
                        BarcodeMappedData(
                            title = "Mapped: ${item.text}",
                            subtitle = item.format.name,
                            barcodeImage = ""
                        )
                    )
                } else {
                    onError.onError()
                }
            }

            // Configure other parameters as needed.
        }
    }
    return configuration;
}

fun startMappingItemScanning(
    onResultHandler: (barcodeResult: BarcodeScannerUiResult) -> Unit,
    onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiMappingItemScanningUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        })
}
// @EndTag("Mapping item scanning")