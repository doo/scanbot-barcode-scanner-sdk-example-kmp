package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.InvalidLicenseError
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Barcode Scanner")
@Composable
fun BarcodeScannerExample() {
    // Initialize the SDK:
    LaunchedEffect(Unit) {
        ScanbotSDK.initialize(
            SdkConfiguration(
                licenseKey = "" // add your license key here
            )
        )
    }

    Button(onClick = {
        // Create the configuration:
        val config = BarcodeScannerScreenConfiguration().apply {
            // TODO: configure as needed
        }

        // Launch the barcode scanner:
        ScanbotSDK.barcode.startScanner(
            configuration = config,
            onResult = { result ->
                result.onSuccess { scanResult ->
                    // Barcode Scanner result callback:
                    // Get the first scanned barcode from the result object...
                    val barcodeItem = scanResult.items.firstOrNull()
                    // ... and process the result as needed, for example, print to console:
                    println("Scanned: ${barcodeItem?.barcode?.text} (${barcodeItem?.barcode?.format})")
                }.onFailure { error ->
                    // Optional failure handling to understand why scanner result is not provided
                    when (error) {
                        is InvalidLicenseError -> {
                            // Indicates that the Scanbot SDK license is invalid
                        }

                        else -> {
                            // Handle other errors
                        }
                    }
                }
            })
    }) {
        Text("Start Barcode Scanner")
    }
}
// @EndTag("Barcode Scanner")

// @Tag("Handling the result")
fun handlingResult() {
    // Create the configuration:
    val config = BarcodeScannerScreenConfiguration().apply {
        // TODO: configure as needed
    }

    ScanbotSDK.barcode.startScanner(
        configuration = config,
        onResult = { result ->
            result.onSuccess { scanResult ->
                scanResult.items.forEach { barcodeUiItem ->
                    // Handle the detected barcode(s) from result
                    val barcodeText = barcodeUiItem.barcode.text
                    val barcodeFormat = barcodeUiItem.barcode.format
                    val textWithExtension =
                        barcodeUiItem.barcode.text + barcodeUiItem.barcode.upcEanExtension

                    // The barcodeItem contains the scanned barcode data as ByteArray
                    val barcodeRawData = barcodeUiItem.barcode.rawBytes

                    // This is the image of the barcode that was scanned (if returnBarcodeImage was enabled)
                    val barcodeImage = barcodeUiItem.barcode.sourceImage

                    // Access extracted document if available (e.g., Boarding Pass)
                    barcodeUiItem.barcode.extractedDocument?.let { document ->
                        document.fields.forEach { field ->
                            println("${field.type}: ${field.value?.text}")
                        }
                    }
                }
            }
        }
    )
}
// @EndTag("Handling the result")