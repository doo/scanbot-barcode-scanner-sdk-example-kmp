package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.custom_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.scanbot.sdk.compose.barcode.BarcodeCameraConfiguration
import io.scanbot.sdk.compose.barcode.BarcodeOverlayTextFormat
import io.scanbot.sdk.compose.barcode.OverlayColors
import io.scanbot.sdk.compose.barcode.SelectionOverlay
import io.scanbot.sdk.compose.barcode.ui.BarcodeScannerView

import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

@Composable
fun BasicSelectionBarcodeScanner() {
    // @Tag("Basic Selection Overlay")
    val configuration = BarcodeCameraConfiguration(
        overlayConfiguration = SelectionOverlay(
            overlayEnabled = true,
            colors = OverlayColors(
                polygonColor = ScanbotColor("#00FF00CC"),
                textColor = ScanbotColor("#FFFFFF")
            )
        )
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        }
    )
    // @EndTag("Basic Selection Overlay")
}

@Composable
fun SelectionOverlayBarcodeScanner() {
    // @Tag("Selection Overlay with Text Format")
    val configuration = BarcodeCameraConfiguration(
        overlayConfiguration = SelectionOverlay(
            overlayEnabled = true,
            textFormat = BarcodeOverlayTextFormat.CODE_AND_TYPE,
            colors = OverlayColors(
                polygonColor = ScanbotColor("#0093ffCC"),
                strokeColor = ScanbotColor("#0027ffCC"),
                textColor = ScanbotColor("#ffffff"),
                textContainerColor = ScanbotColor("#ff0000CC")
            )
        )
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        }
    )
    // @EndTag("Selection Overlay with Text Format")
}

@Composable
fun SelectionOverlayTapBarcodeScanner() {
    // @Tag("Selection Overlay with Tap Handling")
    val configuration = BarcodeCameraConfiguration(
        overlayConfiguration = SelectionOverlay(
            overlayEnabled = true,
            colors = OverlayColors(
                polygonColor = ScanbotColor("#ff0005CC"),
                textColor = ScanbotColor("#FFFFFF")
            )
        )
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        },
        onBarcodeTap = { barcode, highlighted ->
            // Handle selected barcode
        }
    )
    // @EndTag("Selection Overlay with Tap Handling")
}