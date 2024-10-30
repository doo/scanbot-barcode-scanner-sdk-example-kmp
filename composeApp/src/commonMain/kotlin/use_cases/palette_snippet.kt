package use_cases

import io.scanbot.sdk.compose.multiplatform.common.Palette
import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration

fun paletteConfigSnippet(): BarcodeScannerConfiguration {
    // Create the default configuration object.
    return BarcodeScannerConfiguration().apply {
        palette = Palette().apply {
            // Simply alter one color and keep the other default.
            sbColorPrimary = ScanbotColor("#c86e19")

            // ... or set an entirely new palette.
            sbColorPrimary = ScanbotColor("#C8193C")
            sbColorPrimaryDisabled = ScanbotColor("#F5F5F5")
            sbColorNegative = ScanbotColor("#FF3737")
            sbColorPositive = ScanbotColor("#4EFFB4")
            sbColorWarning = ScanbotColor("#FFCE5C")
            sbColorSecondary = ScanbotColor("#FFEDEE")
            sbColorSecondaryDisabled = ScanbotColor("#F5F5F5")
            sbColorOnPrimary = ScanbotColor("#FFFFFF")
            sbColorOnSecondary = ScanbotColor("#C8193C")
            sbColorSurface = ScanbotColor("#FFFFFF")
            sbColorOutline = ScanbotColor("#EFEFEF")
            sbColorOnSurfaceVariant = ScanbotColor("#707070")
            sbColorOnSurface = ScanbotColor("#000000")
            sbColorSurfaceLow = ScanbotColor("#00000026")
            sbColorSurfaceHigh = ScanbotColor("#0000007A")
            sbColorModalOverlay = ScanbotColor("#000000A3")
        }
    }
}