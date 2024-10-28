package use_cases

import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeItem
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeMappedData
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.MultipleScanningMode
import io.scanbot.sdk.compose.multiplatform.configuration.internal.BarcodeItemMapper

fun itemMappingSnippet() : BarcodeScannerConfiguration {
    // Create the default configuration object.
    var configuration = BarcodeScannerConfiguration();

    // Initialize the use case for single scanning.
    var scanningMode = MultipleScanningMode();

    val mappingFunction: (BarcodeItem) -> BarcodeMappedData = ::mapBarcodeItemToMappedData
    scanningMode.barcodeInfoMapping.barcodeItemMapper = BarcodeItemMapper(mappingFunction, null)

    configuration.useCase = scanningMode;

    // Configure other parameters as needed.
    return configuration;
}

fun mapBarcodeItemToMappedData(barcodeItem: BarcodeItem): BarcodeMappedData {
    return BarcodeMappedData("test", "test123", "")
}