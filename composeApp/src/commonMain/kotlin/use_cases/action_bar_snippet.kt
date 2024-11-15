package use_cases

import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration

fun actionBarConfigSnippet() : BarcodeScannerConfiguration{
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    // Configure the action bar.
    // Hide/unhide the flash button.
    configuration.actionBar.flashButton.visible = true

    // Configure the inactive state of the flash button.
    configuration.actionBar.flashButton.backgroundColor =
        ScanbotColor("#0000007A")
    configuration.actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")

    // Configure the active state of the flash button.
    configuration.actionBar.flashButton.activeBackgroundColor =
        ScanbotColor("#FFCE5C")
    configuration.actionBar.flashButton.activeForegroundColor =
        ScanbotColor("#000000")

    // Hide/unhide the zoom button.
    configuration.actionBar.zoomButton.visible = true

    // Configure the inactive state of the zoom button.
    configuration.actionBar.zoomButton.backgroundColor =
        ScanbotColor("#0000007A")
    configuration.actionBar.zoomButton.foregroundColor = ScanbotColor("#FFFFFF")

    // Zoom button has no active state - it only switches between zoom levels (for configuring those please refer to camera configuring).

    // Hide/unhide the flip camera button.
    configuration.actionBar.flipCameraButton.visible = true

    // Configure the inactive state of the flip camera button.
    configuration.actionBar.flipCameraButton.backgroundColor =
        ScanbotColor("#0000007A")
    configuration.actionBar.flipCameraButton.foregroundColor =
        ScanbotColor("#FFFFFF")

    // Flip camera button has no active state - it only switches between front and back camera.

    // Configure other parameters as needed.
    return configuration;
}