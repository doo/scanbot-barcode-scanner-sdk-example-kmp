package use_cases

import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.compose.multiplatform.configuration.ExpectedBarcode
import io.scanbot.sdk.compose.multiplatform.configuration.FindAndPickScanningMode
import io.scanbot.sdk.compose.multiplatform.configuration.SheetMode

fun findAndPickModeUseCaseSnippet() : BarcodeScannerConfiguration{
    // Create the default configuration object.
    var configuration = BarcodeScannerConfiguration();

    // Initialize the use case for find and pick scanning.
    var scanningMode = FindAndPickScanningMode();

    // Set the sheet mode for the barcodes preview.
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET;

    // Enable AR Overlay
    scanningMode.arOverlay.visible = true;

    // Enable/Disable the automatic selection.
    scanningMode.arOverlay.automaticSelectionEnabled = false;

    // Set the height for the collapsed sheet.
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE;

    // Enable manual count change.
    scanningMode.sheetContent.manualCountChangeEnabled = true;

    // Set the delay before same barcode counting repeat.
    scanningMode.countingRepeatDelay = 1000;

    // Configure the submit button.
    scanningMode.sheetContent.submitButton.text = "Submit";
    scanningMode.sheetContent.submitButton.foreground.color =
        ScanbotColor("#000000");

    // Configure other parameters, pertaining to findAndPick-scanning mode as needed.
    // Set the expected barcodes.
    scanningMode.expectedBarcodes = listOf(
        ExpectedBarcode(
            barcodeValue = "123456",
            title = "numeric barcode",
            image = "https://avatars.githubusercontent.com/u/1454920",
            count =  4),
        ExpectedBarcode(
            barcodeValue = "SCANBOT",
            title = "value barcode",
            image = "https://avatars.githubusercontent.com/u/1454920",
            count =  3),
    ) // WARNING: All web links will not work without internet permission. Enable it by uncommenting in AndroidManifest.xml.

    configuration.useCase = scanningMode;

    // Configure other parameters as needed.
    return configuration;
}