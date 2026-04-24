package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.custom_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.scanbot.sdk.compose.barcode.ArOverlayConfiguration
import io.scanbot.sdk.compose.barcode.ArOverlayPolygonConfiguration
import io.scanbot.sdk.compose.barcode.ArOverlayTextConfiguration
import io.scanbot.sdk.compose.barcode.BarcodeOverlayTextFormat
import io.scanbot.sdk.compose.barcode.ui.BarcodeScannerCustomUI
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

@Composable
fun BasicArOverlayBarcodeScanner() {
    // @Tag("Basic Selection Overlay")
    val configuration = ArOverlayConfiguration(
        overlayEnabled = true, polygonConfiguration = ArOverlayPolygonConfiguration.Style(
            polygonColor = ScanbotColor("#00FF00CC"),
        ), textConfiguration = ArOverlayTextConfiguration.Style(
            textColor = ScanbotColor("#FFFFFF")
        )
    )

    BarcodeScannerCustomUI(
        modifier = Modifier.fillMaxSize(),
        arOverlayConfiguration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        })
    // @EndTag("Basic Selection Overlay")
}

@Composable
fun ArOverlayBarcodeScanner() {
    // @Tag("Selection Overlay with Text Format")
    val configuration = ArOverlayConfiguration(
        overlayEnabled = true, polygonConfiguration = ArOverlayPolygonConfiguration.Style(
            polygonColor = ScanbotColor("#0093ffCC"),
            strokeColor = ScanbotColor("#0027ffCC"),
        ), textConfiguration = ArOverlayTextConfiguration.Style(
            textColor = ScanbotColor("#ffffff"),
            textContainerColor = ScanbotColor("#ff0000CC"),
            textFormat = BarcodeOverlayTextFormat.CODE_AND_TYPE,
        )
    )

    BarcodeScannerCustomUI(
        modifier = Modifier.fillMaxSize(),
        arOverlayConfiguration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        })
    // @EndTag("Selection Overlay with Text Format")
}

@Composable
fun ArOverlayTapBarcodeScanner() {
    // @Tag("Selection Overlay with Tap Handling")
    val configuration = ArOverlayConfiguration(
        overlayEnabled = true, polygonConfiguration = ArOverlayPolygonConfiguration.Style(
            polygonColor = ScanbotColor("#ff0005CC"),
        ), textConfiguration = ArOverlayTextConfiguration.Style(
            textColor = ScanbotColor("#FFFFFF")
        )
    )

    BarcodeScannerCustomUI(
        modifier = Modifier.fillMaxSize(),
        arOverlayConfiguration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
        },
        onBarcodeTap = { barcode, highlighted ->
            // Handle selected barcode
        })
    // @EndTag("Selection Overlay with Tap Handling")
}