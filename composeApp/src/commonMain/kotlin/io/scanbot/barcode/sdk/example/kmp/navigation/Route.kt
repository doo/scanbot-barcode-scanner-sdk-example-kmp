package io.scanbot.barcode.sdk.example.kmp.navigation

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object BarcodeUseCases : Route

    @Serializable
    data class BarcodePreview(val barcodeJson: String) : Route
    
    @Serializable
    data object BarcodeCustomUI : Route
}
