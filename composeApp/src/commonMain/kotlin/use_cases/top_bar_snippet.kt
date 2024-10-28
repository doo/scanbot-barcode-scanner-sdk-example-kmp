package use_cases

import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.common.StatusBarMode
import io.scanbot.sdk.compose.multiplatform.common.TopBarMode
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration

fun topBarConfigSnippet() : BarcodeScannerConfiguration{
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    // Configure the top bar.

    // Set the top bar mode.
    configuration.topBar.mode = TopBarMode.GRADIENT

    // Set the background color which will be used as a gradient.
    configuration.topBar.backgroundColor = ScanbotColor("#C8193C")

    // Configure the status bar look. If visible - select DARK or LIGHT according to your app's theme color.
    configuration.topBar.statusBarMode = StatusBarMode.HIDDEN

    // Configure the Cancel button.
    configuration.topBar.cancelButton.text = "Cancel"
    configuration.topBar.cancelButton.foreground.color = ScanbotColor("#FFFFFF")

    // Configure other parameters as needed.
    return  configuration;
}