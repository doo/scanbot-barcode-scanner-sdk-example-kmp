package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

import io.scanbot.sdk.kmp.geometry.AspectRatio
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderStyle

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Viewfinder")
fun rtuUiViewFinderConfiguration() {
    val config = BarcodeScannerScreenConfiguration().apply {
        viewFinder.visible = true // Show the view finder
        viewFinder.aspectRatio = AspectRatio(16.0, 9.0) // Set the aspect ratio of the view finder
        viewFinder.style = FinderStyle.finderCorneredStyle().apply {
            strokeColor = ScanbotColor("#00FF00") // Set the color of the view finder corners
            strokeWidth = 10.0 // Set the width of the view finder corners in dp
        }
    }
}
// @EndTag("Viewfinder")