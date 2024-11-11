package use_cases

import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.compose.multiplatform.configuration.MultipleScanningMode
import io.scanbot.sdk.compose.multiplatform.configuration.SheetMode

fun multipleScanningPreviewConfigSnippet() : BarcodeScannerConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    // Initialize the use case for multiple scanning.
    val scanningMode = MultipleScanningMode()

    // Set the sheet mode for the barcodes preview.
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET

    // Set the height for the collapsed sheet.
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE

    // Configure the submit button on the sheet.
    scanningMode.sheetContent.submitButton.text = "Submit"
    scanningMode.sheetContent.submitButton.foreground.color =
        ScanbotColor("#000000")

    // Configure other parameters, pertaining to multiple-scanning mode as needed.
    configuration.useCase = scanningMode

    // Configure other parameters as needed.
    return configuration;
}