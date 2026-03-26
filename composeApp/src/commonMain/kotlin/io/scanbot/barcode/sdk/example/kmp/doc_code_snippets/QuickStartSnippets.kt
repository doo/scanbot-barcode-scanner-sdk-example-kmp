package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Add imports for Scanbot SDK KMP")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
// @EndTag("Add imports for Scanbot SDK KMP")

import io.scanbot.sdk.kmp.barcode.BarcodeFormatQrCodeConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatUpcEanConfiguration
import io.scanbot.sdk.kmp.image.ImageRef

class QuickStartSnippetKMP {

    fun initializeSDK() {
        // @Tag("Initialize Scanbot SDK")
        val LICENSE_KEY = "YOUR_SCANBOT_SDK_LICENSE_KEY"

        val configuration = SdkConfiguration(
            licenseKey = LICENSE_KEY
            // Optional: enable logging for debugging
            // loggingEnabled = true
        )

        val result = ScanbotSDK.initialize(configuration)

        result.onSuccess { licenseInfo ->
            // SDK initialized successfully
            println("License is valid: ${licenseInfo.isValid}")
        }.onFailure { error ->
            // Handle initialization error
            println("SDK initialization failed: ${error.message}")
        }
        // @EndTag("Initialize Scanbot SDK")
    }

    fun launchBarcodeScanner() {
        // @Tag("Launch barcode scanner RTU UI")
        // Create the scanner configuration
        val config = BarcodeScannerScreenConfiguration().apply {
            // TODO: configure as needed
        }

        // Launch the scanner
        ScanbotSDK.barcode.startScanner(
            configuration = config,
            onResult = { result ->
                result.onSuccess { scanResult ->
                    // Handle successful scan
                    val barcodes = scanResult.items
                    if (barcodes.isNotEmpty()) {
                        val firstBarcode = barcodes.first()
                        println("Scanned: ${firstBarcode.barcode.text} (${firstBarcode.barcode.format})")
                    }
                }.onFailure { error ->
                    // Handle scanning error
                    println("Scan error: ${error.message}")
                }
            }, onCancel = {
                // Handle user cancellation
                println("Scanning cancelled by user")
            })
        // @EndTag("Launch barcode scanner RTU UI")
    }

    fun scanBarcodeFromImage(imageRef: ImageRef) {
        // @Tag("Scan barcode from image")
        // Create barcode scanner configuration
        val scannerConfig = BarcodeScannerConfiguration(
            barcodeFormatConfigurations = listOf(
                BarcodeFormatQrCodeConfiguration(), BarcodeFormatUpcEanConfiguration()
            )
            // Add more configuration as needed
        )

        // Scan from image
        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef, // Your ImageRef instance
            configuration = scannerConfig
        )

        result.onSuccess { scanResult ->
            val barcodes = scanResult.barcodes
            println("Found ${barcodes.size} barcodes")
            barcodes.forEach { barcode ->
                println("${barcode.format}: ${barcode.text}")
            }
        }.onFailure { error ->
            println("Error scanning image: ${error.message}")
        }
        // @EndTag("Scan barcode from image")
    }
}