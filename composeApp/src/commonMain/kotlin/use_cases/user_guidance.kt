package use_cases

import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration

fun userGuidanceConfigSnippet() : BarcodeScannerConfiguration{
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    // Hide/unhide the user guidance.
    configuration.userGuidance.visible = true

    // Configure the title.
    configuration.userGuidance.title.text = "Move the finder over a barcode"
    configuration.userGuidance.title.color = ScanbotColor("#FFFFFF")

    // Configure the background.
    configuration.userGuidance.background.fillColor = ScanbotColor("#0000007A")

    // Configure other parameters as needed.

    return configuration;
}