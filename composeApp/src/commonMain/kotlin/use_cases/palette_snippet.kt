package use_cases

import io.scanbot.sdk.compose.multiplatform.common.Palette
import io.scanbot.sdk.compose.multiplatform.common.ScanbotColor
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration

fun paletteConfigSnippet() : BarcodeScannerConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerConfiguration()

    val palette = Palette()

    // Simply alter one color and keep the other default.
    palette.sbColorPrimary = ScanbotColor("#c86e19")

    // ... or set an entirely new palette.
    palette.sbColorPrimary = ScanbotColor("#C8193C")
    palette.sbColorPrimaryDisabled = ScanbotColor("#F5F5F5")
    palette.sbColorNegative = ScanbotColor("#FF3737")
    palette.sbColorPositive = ScanbotColor("#4EFFB4")
    palette.sbColorWarning = ScanbotColor("#FFCE5C")
    palette.sbColorSecondary = ScanbotColor("#FFEDEE")
    palette.sbColorSecondaryDisabled = ScanbotColor("#F5F5F5")
    palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")
    palette.sbColorOnSecondary = ScanbotColor("#C8193C")
    palette.sbColorSurface = ScanbotColor("#FFFFFF")
    palette.sbColorOutline = ScanbotColor("#EFEFEF")
    palette.sbColorOnSurfaceVariant = ScanbotColor("#707070")
    palette.sbColorOnSurface = ScanbotColor("#000000")
    palette.sbColorSurfaceLow = ScanbotColor("#00000026")
    palette.sbColorSurfaceHigh = ScanbotColor("#0000007A")
    palette.sbColorModalOverlay = ScanbotColor("#000000A3")

    configuration.palette = palette

    return configuration;
}