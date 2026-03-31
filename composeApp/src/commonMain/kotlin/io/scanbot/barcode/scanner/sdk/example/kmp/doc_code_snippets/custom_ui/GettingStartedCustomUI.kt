package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.custom_ui

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.scanbot.sdk.compose.barcode.BarcodeCameraConfiguration
import io.scanbot.sdk.compose.barcode.BarcodeItemOverlay
import io.scanbot.sdk.compose.barcode.BarcodeOverlayTextFormat
import io.scanbot.sdk.compose.barcode.FinderViewConfiguration
import io.scanbot.sdk.compose.barcode.OverlayColors
import io.scanbot.sdk.compose.barcode.SelectionOverlay
import io.scanbot.sdk.compose.barcode.ui.BarcodeScannerView
import io.scanbot.sdk.kmp.barcode.AustraliaPostCustomerFormat
import io.scanbot.sdk.kmp.barcode.BarcodeDocumentFormat
import io.scanbot.sdk.kmp.barcode.BarcodeFormatAustraliaPostConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatCode11Configuration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatCode2Of5Configuration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatCommonConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatConfigurationBase
import io.scanbot.sdk.kmp.barcode.BarcodeFormatMsiPlesseyConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormats
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerEngineMode
import io.scanbot.sdk.kmp.barcode.Gs1Handling
import io.scanbot.sdk.kmp.barcode.MsiPlesseyChecksumAlgorithm
import io.scanbot.sdk.kmp.geometry.AspectRatio
import io.scanbot.sdk.kmp.ui.camera.CameraLiveScannerResolution
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.CameraModule
import io.scanbot.sdk.kmp.ui_v2.common.configuration.EdgeInsets

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

@Composable
fun BarcodeScannerScreen() {
    // @Tag("Basic Barcode Scanner View")
    val configuration = BarcodeCameraConfiguration(
        detectionEnabled = true,
        flashEnabled = false,
        scannerConfiguration = BarcodeScannerConfiguration().apply {
            extractedDocumentFormats = listOf(
                BarcodeDocumentFormat.AAMVA,
                BarcodeDocumentFormat.BOARDING_PASS,
                BarcodeDocumentFormat.MEDICAL_CERTIFICATE
            )
        }
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle detected barcodes
            barcodes.forEach { barcodeItem ->
                val barcodeText = barcodeItem.text
                val barcodeFormat = barcodeItem.format
                val barcodeRawData = barcodeItem.rawBytes
            }
        },
        onBarcodeTap = { barcode, highlighted ->
            // Handle barcode tap
        },
        onError = { error ->
            // Handle errors
        }
    )
    // @EndTag("Basic Barcode Scanner View")
}


@Composable
fun SimpleBarcodeScannerConfiguration() {
    // @Tag("Simple configuring Barcode Scanner")
    val scannerConfiguration = BarcodeScannerConfiguration()

    scannerConfiguration.onlyAcceptDocuments = false
    scannerConfiguration.extractedDocumentFormats = listOf(
        BarcodeDocumentFormat.AAMVA,
        BarcodeDocumentFormat.BOARDING_PASS,
        BarcodeDocumentFormat.DE_MEDICAL_PLAN,
        BarcodeDocumentFormat.MEDICAL_CERTIFICATE,
        BarcodeDocumentFormat.ID_CARD_PDF_417,
        BarcodeDocumentFormat.SEPA,
        BarcodeDocumentFormat.SWISS_QR,
        BarcodeDocumentFormat.VCARD,
        BarcodeDocumentFormat.GS1,
        BarcodeDocumentFormat.HIBC
    )

    val baseConfig = BarcodeFormatCommonConfiguration.default().copy(
        formats = BarcodeFormats.common
    )
    scannerConfiguration.barcodeFormatConfigurations = listOf(baseConfig)

    val configuration = BarcodeCameraConfiguration(
        scannerConfiguration = scannerConfiguration
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle barcodes
        }
    )
    // @EndTag("Simple configuring Barcode Scanner")
}

@Composable
fun AdvancedBarcodeScannerConfiguration() {
    // @Tag("Advanced configuring Barcode Scanner")
    val scannerConfiguration = BarcodeScannerConfiguration()

    var configs = mutableListOf<BarcodeFormatConfigurationBase>()

    val baseConfig = BarcodeFormatCommonConfiguration.default().copy(
        regexFilter = "",
        minimum1DQuietZoneSize = 10,
        stripCheckDigits = false,
        minimumTextLength = 0,
        maximumTextLength = 0,
        gs1Handling = Gs1Handling.PARSE,
        strictMode = true,
        formats = BarcodeFormats.common,
        addAdditionalQuietZone = false
    )
    configs.add(baseConfig)

    // Add individual configurations for specific barcode formats
    val australiaPostConfig = BarcodeFormatAustraliaPostConfiguration(
        regexFilter = "",
        australiaPostCustomerFormat = AustraliaPostCustomerFormat.ALPHA_NUMERIC
    )
    configs.add(australiaPostConfig)

    val msiPlesseyConfig = BarcodeFormatMsiPlesseyConfiguration(
        regexFilter = "",
        minimum1DQuietZoneSize = 10,
        stripCheckDigits = false,
        minimumTextLength = 0,
        maximumTextLength = 0,
        checksumAlgorithms = listOf(MsiPlesseyChecksumAlgorithm.MOD_10)
    )
    configs.add(msiPlesseyConfig)

    val code11Config = BarcodeFormatCode11Configuration(
        regexFilter = "",
        minimum1DQuietZoneSize = 10,
        stripCheckDigits = false,
        minimumTextLength = 0,
        maximumTextLength = 0,
        checksum = true
    )
    configs.add(code11Config)

    val code2Of5Config = BarcodeFormatCode2Of5Configuration(
        regexFilter = "",
        minimum1DQuietZoneSize = 10,
        stripCheckDigits = false,
        minimumTextLength = 0,
        maximumTextLength = 0,
        iata2of5 = true,
        code25 = false,
        industrial2of5 = false,
        useIATA2OF5Checksum = true
    )
    configs.add(code2Of5Config)

    // Set the configurations to the barcode scanner
    scannerConfiguration.barcodeFormatConfigurations = configs
    scannerConfiguration.extractedDocumentFormats = listOf(
        BarcodeDocumentFormat.AAMVA,
        BarcodeDocumentFormat.BOARDING_PASS,
        BarcodeDocumentFormat.DE_MEDICAL_PLAN,
        BarcodeDocumentFormat.MEDICAL_CERTIFICATE,
        BarcodeDocumentFormat.ID_CARD_PDF_417,
        BarcodeDocumentFormat.SEPA,
        BarcodeDocumentFormat.SWISS_QR,
        BarcodeDocumentFormat.VCARD,
        BarcodeDocumentFormat.GS1,
        BarcodeDocumentFormat.HIBC
    )
    scannerConfiguration.onlyAcceptDocuments = false
    scannerConfiguration.engineMode = BarcodeScannerEngineMode.NEXT_GEN
    scannerConfiguration.returnBarcodeImage = true

    val configuration = BarcodeCameraConfiguration(
        scannerConfiguration = scannerConfiguration
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle barcodes
        }
    )
    // @EndTag("Advanced configuring Barcode Scanner")
}


@Composable
fun CommonConfigurationExample() {
    // @Tag("Configuring BarcodeFormatCommonConfiguration")
    val baseConfig = BarcodeFormatCommonConfiguration.default().copy(
        formats = BarcodeFormats.common,
    )

    val scannerConfiguration = BarcodeScannerConfiguration()
    scannerConfiguration.barcodeFormatConfigurations = listOf(baseConfig)

    val configuration = BarcodeCameraConfiguration(
        scannerConfiguration = scannerConfiguration
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle barcodes
        }
    )
    // @EndTag("Configuring BarcodeFormatCommonConfiguration")
}

@Composable
fun CameraConfigurationExample() {
    // @Tag("Configuring Camera")
    val configuration = BarcodeCameraConfiguration(
        detectionEnabled = true,
        flashEnabled = false,
        hardwareButtonsEnabled = true,
        cameraZoomFactor = 1.0f,
        cameraZoomRange = FloatRange(from = 1.0, to = 12.0),
        cameraModule = CameraModule.BACK,
        minFocusDistanceLock = false,
        cameraLiveScannerResolution = CameraLiveScannerResolution.FULL_HD
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle barcodes
        }
    )
    // @EndTag("Configuring Camera")
}

@Composable
fun FinderViewConfigurationExample() {
    // @Tag("Configuring Finder View")
    val finderConfiguration = FinderViewConfiguration(
        enabled = true,
        lineWidth = 2,
        lineColor = ScanbotColor("#FFFFFF"),
        backgroundColor = ScanbotColor("#000000A9"),
        aspectRatio = AspectRatio(4.0, 1.0), // Wide aspect ratio for barcodes
        insets = EdgeInsets(10.0, 10.0, 10.0, 10.0)
    )

    val configuration = BarcodeCameraConfiguration(
        finderConfiguration = finderConfiguration
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle barcodes
        }
    )
    // @EndTag("Configuring Finder View")
}

@Composable
fun SelectionOverlayConfigurationExample() {
    // @Tag("Configuring Selection Overlay")
    val overlayConfiguration = SelectionOverlay(
        overlayEnabled = true,
        loadingText = "Scanning...",
        textFormat = BarcodeOverlayTextFormat.CODE_AND_TYPE,
        colors = OverlayColors(
            polygonColor = ScanbotColor("#00CFA633"),
            strokeColor = ScanbotColor("#00CFA6CC"),
            highlightedPolygonColor = ScanbotColor("#C81A3C33"),
            highlightedStrokeColor = ScanbotColor("#C81A3CCC"),
            textColor = ScanbotColor("#000000"),
            textContainerColor = ScanbotColor("#00CFA6CC"),
            highlightedTextColor = ScanbotColor("#FFFFFF"),
            highlightedTextContainerColor = ScanbotColor("#C81A3CCC")
        )
    )

    val configuration = BarcodeCameraConfiguration(
        overlayConfiguration = overlayConfiguration
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle barcodes
        },
        onBarcodeTap = { barcode, highlighted ->
            // Handle tap on highlighted barcode
        }
    )
    // @EndTag("Configuring Selection Overlay")
}

@Composable
fun CustomBarcodeOverlayExample() {
    // @Tag("Custom Barcode Overlay")
    val overlayConfiguration = SelectionOverlay(
        overlayEnabled = true,
        textFormat = BarcodeOverlayTextFormat.CODE,
        itemOverlayViewBinder = { barcodes ->
            barcodes.map { barcode ->
                BarcodeItemOverlay(
                    barcodeItem = barcode,
                    text = "Custom: ${barcode.text}",
                    textFormat = BarcodeOverlayTextFormat.CODE_AND_TYPE,
                    colors = OverlayColors(
                        polygonColor = ScanbotColor("#FF0000CC"),
                        strokeColor = ScanbotColor("#FF0000CC")
                    )
                )
            }
        }
    )

    val configuration = BarcodeCameraConfiguration(
        overlayConfiguration = overlayConfiguration
    )

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            // Handle barcodes
        }
    )
    // @EndTag("Custom Barcode Overlay")
}


@Composable
fun HandleBarcodeScanResults() {
    // @Tag("Handling the Result")
    val configuration = BarcodeCameraConfiguration()

    BarcodeScannerView(
        modifier = Modifier.fillMaxSize(),
        configuration = configuration,
        onBarcodesDetected = { barcodes ->
            barcodes.forEach { barcodeItem ->
                // Handle the detected barcode(s) from result
                val barcodeText = barcodeItem.text
                val barcodeFormat = barcodeItem.format

                // The barcodeItem contains the scanned barcode data as ByteArray
                val barcodeRawData = barcodeItem.rawBytes

                // This is the image of the barcode that was scanned
                val barcodeImage = barcodeItem.sourceImage
            }
        }
    )
    // @EndTag("Handling the Result")
}