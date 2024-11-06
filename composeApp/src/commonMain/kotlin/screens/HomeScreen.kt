package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextButton
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.detectors.detectBarcodesFromImageBitmap
import io.scanbot.sdk.compose.multiplatform.picker.rememberGalleryManager
import io.scanbot.sdk.compose.multiplatform.sdk.ScanbotSDK
import use_cases.actionBarConfigSnippet
import use_cases.arOverlayUseCaseSnippet
import use_cases.findAndPickModeUseCaseSnippet
import use_cases.itemMappingSnippet
import use_cases.multipleScanningUseCaseSnippet
import use_cases.paletteConfigSnippet
import use_cases.singleScanningUseCaseSnippet
import use_cases.topBarConfigSnippet
import use_cases.userGuidanceConfigSnippet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToScanner: (BarcodeScannerConfiguration) -> Unit,
    backgroundColor: Color = Color.White
) {
    MaterialTheme(colorScheme = exampleColorScheme()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    title = {
                        Text("Scanbot Barcode Scanner SDK")
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
            ) {
                ConfigurationButtons { configuration ->
                    onNavigateToScanner(configuration)
                }

                DetectBarcodesFromImage()
                DisplayScanbotLicenseStatus()
            }
        }
    }
}

private fun exampleColorScheme(): ColorScheme {
    val scanbotColor = Color(0xFFC8193C)
    return lightColorScheme(
        primary = scanbotColor,
        secondary = scanbotColor,
        primaryContainer = Color(Color.White.toArgb()),
    )
}

@Composable
private fun ConfigurationButtons(
    onConfigurationSelected: (BarcodeScannerConfiguration) -> Unit,
) {
    val operations = remember {
        listOf(
            "Single Scanning" to { onConfigurationSelected(singleScanningUseCaseSnippet()) },
            "Multiple Scanning" to { onConfigurationSelected(multipleScanningUseCaseSnippet()) },
            "Find and Pick Mode" to { onConfigurationSelected(findAndPickModeUseCaseSnippet()) },
            "AR Overlay" to { onConfigurationSelected(arOverlayUseCaseSnippet()) },
            "Palette Config" to { onConfigurationSelected(paletteConfigSnippet()) },
            "Top Bar Config" to { onConfigurationSelected(topBarConfigSnippet()) },
            "User Guidance Config" to { onConfigurationSelected(userGuidanceConfigSnippet()) },
            "Action Bar Config" to { onConfigurationSelected(actionBarConfigSnippet()) },
            "Item Mapping Config" to { onConfigurationSelected(itemMappingSnippet()) }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        operations.forEach { (label, action) ->
            DemoButton(
                text = label,
                onClick = action
            )
        }
    }
}

@Composable
private fun DetectBarcodesFromImage() {
    var launchGallery by remember { mutableStateOf(false) }
    var imageBitmapForDetection by remember { mutableStateOf<ImageBitmap?>(null) }
    var barcodeResult by remember { mutableStateOf<String?>(null) }

    imageBitmapForDetection?.let { bitmap ->
        detectBarcodesFromImageBitmap(bitmap)?.let { result ->
            barcodeResult = result.joinToString(", ") { it.type?.name ?: "Unknown" }
        }
        imageBitmapForDetection = null
    }

    val galleryManager = rememberGalleryManager { sharedImage ->
        imageBitmapForDetection = sharedImage?.imageBitmap
    }

    LaunchedEffect(launchGallery) {
        if (launchGallery) {
            galleryManager.launch()
            launchGallery = false
        }
    }

    Column {
        DemoButton(
            text = "Pick & Detect from Image"
        ) { launchGallery = true }
        barcodeResult?.let {
            Text(text = "Barcode Result: $it")
        }
    }
}

@Composable
private fun DisplayScanbotLicenseStatus() {
    var licenseStatus by remember { mutableStateOf<String?>(null) }
    var isStatusRequested by remember { mutableStateOf(false) }

    if (isStatusRequested) {
        licenseStatus = ScanbotSDK.getLicenseStatus().status?.toString()
        isStatusRequested = false
    }

    Column {
        licenseStatus?.let {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = "License Status: $it"
            )
        }
        DemoButton("Get License Status") {
            isStatusRequested = true
        }
    }
}

@Composable
private fun DemoButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text
        )
    }
}
