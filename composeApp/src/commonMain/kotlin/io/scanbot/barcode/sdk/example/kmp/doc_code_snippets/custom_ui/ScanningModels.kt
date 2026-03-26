package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets.custom_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.scanbot.sdk.compose.barcode.BarcodeCameraConfiguration
import io.scanbot.sdk.compose.barcode.FinderViewConfiguration
import io.scanbot.sdk.compose.barcode.ui.BarcodeScannerView

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

@Composable
fun BasicBarcodeScanner() {
    // @Tag("Basic Barcode Scanner View")
    val configuration = BarcodeCameraConfiguration(
        finderConfiguration = FinderViewConfiguration(
            enabled = true
        )
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        }
    )
    // @EndTag("Basic Barcode Scanner View")
}

@Composable
fun MinFocusBarcodeScanner() {
    // @Tag("Min Focus Distance Lock")
    val configuration = BarcodeCameraConfiguration(
        finderConfiguration = FinderViewConfiguration(
            enabled = true
        ),
        minFocusDistanceLock = true
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        }
    )
    // @EndTag("Min Focus Distance Lock")
}

@Composable
fun ZoomBarcodeScanner() {
    // @Tag("Camera Zoom Factor")
    val configuration = BarcodeCameraConfiguration(
        finderConfiguration = FinderViewConfiguration(
            enabled = true
        ),
        cameraZoomFactor = 1.0f
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        }
    )
    // @EndTag("Camera Zoom Factor")
}