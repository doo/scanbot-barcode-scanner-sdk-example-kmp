package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Palette")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiPaletteConfig(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val config = BarcodeScannerScreenConfiguration().apply {

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
    }
    return config
}

fun startPaletteScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiPaletteConfig(), onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        })
}
// @EndTag("Palette")