package use_cases

import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration

enum class UseCaseSnippet {
    ActionBar,
    AROverlay,
    FindAndPick,
    ItemMapping,
    MultiScanningPreview,
    Palette,
    SingleScanning,
    TopBar,
    UserGuidance
}

fun getBarcodeScannerConfigurationFrom(useCaseSnippet: UseCaseSnippet): BarcodeScannerConfiguration = when (useCaseSnippet) {
    UseCaseSnippet.AROverlay -> arOverlayUseCaseSnippet()
    UseCaseSnippet.SingleScanning -> singleScanningUseCaseSnippet()
    UseCaseSnippet.MultiScanningPreview -> multipleScanningPreviewConfigSnippet()
    UseCaseSnippet.FindAndPick -> findAndPickModeUseCaseSnippet()
    UseCaseSnippet.Palette -> paletteConfigSnippet()
    UseCaseSnippet.TopBar -> topBarConfigSnippet()
    UseCaseSnippet.UserGuidance -> userGuidanceConfigSnippet()
    UseCaseSnippet.ActionBar -> actionBarConfigSnippet()
    UseCaseSnippet.ItemMapping -> itemMappingSnippet()
}