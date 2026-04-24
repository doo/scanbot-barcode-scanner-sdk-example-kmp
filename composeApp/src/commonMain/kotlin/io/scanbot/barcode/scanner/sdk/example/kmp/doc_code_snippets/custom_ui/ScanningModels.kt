package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.custom_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.scanbot.sdk.compose.barcode.FinderViewConfiguration
import io.scanbot.sdk.compose.barcode.ui.BarcodeScannerCustomUI

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

@Composable
fun BasicBarcodeScanner() {
    // @Tag("Basic Barcode Scanner View")
    BarcodeScannerCustomUI(
        modifier = Modifier.fillMaxSize(),
        finderConfiguration = FinderViewConfiguration(enabled = true),
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        })
    // @EndTag("Basic Barcode Scanner View")
}

@Composable
fun MinFocusBarcodeScanner() {
    // @Tag("Min Focus Distance Lock")
    BarcodeScannerCustomUI(
        modifier = Modifier.fillMaxSize(),
        finderConfiguration = FinderViewConfiguration(enabled = true),
        minFocusDistanceLock = true,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        })
    // @EndTag("Min Focus Distance Lock")
}

@Composable
fun ZoomBarcodeScanner() {
    // @Tag("Camera Zoom Factor")
    BarcodeScannerCustomUI(
        modifier = Modifier.fillMaxSize(),
        finderConfiguration = FinderViewConfiguration(enabled = true),
        cameraZoomFactor = 1.0f,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        })
    // @EndTag("Camera Zoom Factor")
}