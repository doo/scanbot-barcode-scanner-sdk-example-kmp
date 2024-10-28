package use_cases

import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration

fun configurationWithLocalizationSnippet() {
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    // Configure localization parameters.
    configuration.localization.barcodeInfoMappingErrorStateCancelButton =
        "Custom Cancel title"
    configuration.localization.cameraPermissionCloseButton = "Custom Close title"

    // Configure other strings as needed.

    // Configure other parameters as needed.
}