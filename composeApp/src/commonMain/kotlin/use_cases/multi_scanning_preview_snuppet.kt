package use_cases

import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.compose.multiplatform.configuration.MultipleBarcodesScanningMode
import io.scanbot.sdk.compose.multiplatform.configuration.MultipleScanningMode
import io.scanbot.sdk.compose.multiplatform.configuration.SheetMode

fun multipleScanningUseCaseSnippet() : BarcodeScannerConfiguration{
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    // Initialize the use case for multiple scanning.
    val scanningMode = MultipleScanningMode()

    // Set the counting mode.
    scanningMode.mode = MultipleBarcodesScanningMode.COUNTING

    // Set the sheet mode for the barcodes preview.
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET

    // Set the height for the collapsed sheet.
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE

    // Enable manual count change.
    scanningMode.sheetContent.manualCountChangeEnabled = true

    // Set the delay before same barcode counting repeat.
    scanningMode.countingRepeatDelay = 1000

    // Configure the submit button.
    scanningMode.sheetContent.submitButton.text = "Submit"
    scanningMode.sheetContent.submitButton.foreground.color =
        ScanbotColor("#000000")

    // Configure other parameters, pertaining to multiple-scanning mode as needed.
    configuration.useCase = scanningMode

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

    // Configure other parameters as needed.
    return configuration;
}