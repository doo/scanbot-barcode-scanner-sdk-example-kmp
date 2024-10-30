package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
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

@Composable
fun HomeScreen(
    onNavigateToScanner: (BarcodeScannerConfiguration) -> Unit,
    backgroundColor: Color = Color.White
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ConfigurationButtons { configuration ->
            onNavigateToScanner(configuration)
        }

        DetectBarcodesFromImage()
        DisplayScanbotLicenseStatus()
    }
}

@Composable
private fun ConfigurationButtons(
    onConfigurationSelected: (BarcodeScannerConfiguration) -> Unit,
) {
    val operations = remember {
        listOf(
            "AR Overlay" to { onConfigurationSelected(arOverlayUseCaseSnippet()) },
            "Single Scanning" to { onConfigurationSelected(singleScanningUseCaseSnippet()) },
            "Multiple Scanning" to { onConfigurationSelected(multipleScanningUseCaseSnippet()) },
            "Find and Pick Mode" to { onConfigurationSelected(findAndPickModeUseCaseSnippet()) },
            "Palette Config" to { onConfigurationSelected(paletteConfigSnippet()) },
            "Top Bar Config" to { onConfigurationSelected(topBarConfigSnippet()) },
            "User Guidance Config" to { onConfigurationSelected(userGuidanceConfigSnippet()) },
            "Action Bar Config" to { onConfigurationSelected(actionBarConfigSnippet()) },
            "Item Mapping Config" to { onConfigurationSelected(itemMappingSnippet()) }
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(operations) { (label, action) ->
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                onClick = action,
            ) {
                Text(
                    text = label,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                shape = RectangleShape,
                onClick = { launchGallery = true }) {
                Text(
                    color = Color.Red,
                    text = "Pick & Detect from Image")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        barcodeResult?.let {
            Text(text = "Barcode Result: $it")
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun DisplayScanbotLicenseStatus() {
    var licenseStatus by remember { mutableStateOf<String?>(null) }
    var isStatusRequested by remember { mutableStateOf(false) }

    if (isStatusRequested) {
        licenseStatus = ScanbotSDK.getLicenseStatus().status.toString()
        isStatusRequested = false
    }

    Column {
        Spacer(modifier = Modifier.height(40.dp))
        licenseStatus?.let {
            Text(
                color = Color.Red,
                text = "License Status: $it"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                shape = RectangleShape,
                onClick = {
                isStatusRequested = true
            }) {
                Text(
                    color = Color.Red,
                    text = "Get License Status"
                )
            }
        }
    }
}
