package use_cases

import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.compose.multiplatform.configuration.MultipleBarcodesScanningMode
import io.scanbot.sdk.compose.multiplatform.configuration.MultipleScanningMode
import io.scanbot.sdk.compose.multiplatform.configuration.SheetMode

fun arOverlayUseCaseSnippet() : BarcodeScannerConfiguration{
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    // Initialize the use case for multiple scanning.
    val scanningMode = MultipleScanningMode()

    scanningMode.mode = MultipleBarcodesScanningMode.UNIQUE
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.SMALL
    // Configure AR Overlay.
    scanningMode.arOverlay.visible = true
    scanningMode.arOverlay.automaticSelectionEnabled = false

    // Configure other parameters, pertaining to use case as needed.

    // Set an array of accepted barcode types.
    // configuration.recognizerConfiguration.barcodeFormats = [
    //   BarcodeFormat.AZTEC,
    //   BarcodeFormat.PDF_417,
    //   BarcodeFormat.QR_CODE,
    //   BarcodeFormat.MICRO_QR_CODE,
    //   BarcodeFormat.MICRO_PDF_417,
    //   BarcodeFormat.ROYAL_MAIL,
    ///  .....
    // ];

    configuration.useCase = scanningMode;

    // Configure other parameters as needed.
    return configuration;
}