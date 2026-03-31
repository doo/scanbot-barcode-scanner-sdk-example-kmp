package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerResult
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

fun scanBarcodeFromImage(imageRef: ImageRef) {
// @Tag("Detecting barcodes")
    val configuration = BarcodeScannerConfiguration()
    // Configure other parameters as needed.

    val result = ScanbotSDK.barcode.scanFromImage(
        image = imageRef,
        configuration = configuration
    )

    result.fold(onFailure = { error ->
        // handle the error
    }, onSuccess = { result ->
        // handle the detected barcode(s)
    })
    // handle the detected barcode(s) from result
// @EndTag("Detecting barcodes")
}

fun scanBarcodeFromImageWithResult(imageRef: ImageRef): Result<BarcodeScannerResult> {
// @Tag("Detecting barcodes with result")
    val configuration = BarcodeScannerConfiguration()
    // Configure other parameters as needed.

    return ScanbotSDK.barcode.scanFromImage(
        image = imageRef,
        configuration = configuration
    )
// @EndTag("Detecting barcodes with result")
}
