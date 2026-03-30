package io.scanbot.barcode.sdk.example.kmp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.outlined.LocationDisabled
import androidx.compose.material.icons.outlined.LocationSearching
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.scanbot.barcode.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.compose.barcode.BarcodeCameraConfiguration
import io.scanbot.sdk.compose.barcode.FinderViewConfiguration
import io.scanbot.sdk.compose.barcode.SelectionOverlay
import io.scanbot.sdk.compose.barcode.ui.BarcodeScannerView
import io.scanbot.sdk.kmp.barcode.BarcodeItem
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerEngineMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

@Composable
fun BarcodeCustomUIScreen(
    onPopBackStack: () -> Unit,
) {
    var barcodes by remember {
        mutableStateOf<List<BarcodeItem>>(emptyList())
    }
    var flashEnabled by rememberSaveable { mutableStateOf(false) }

    val selectionOverlay = remember {
        SelectionOverlay(
            overlayEnabled = true
        )
    }

    var finderEnabled by rememberSaveable { mutableStateOf(true) }

    val finderConfiguration = remember(finderEnabled) {
        FinderViewConfiguration(
            enabled = finderEnabled,
            lineWidth = 3,
            lineColor = ScanbotColor("#2196F3"),
            backgroundColor = ScanbotColor("#33000000"),
        )
    }

    val scannerConfiguration = remember {
        BarcodeScannerConfiguration(
            engineMode = BarcodeScannerEngineMode.NEXT_GEN, returnBarcodeImage = true
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Barcode Custom UI",
                showBackButton = true,
                onPopBackStack = onPopBackStack,
                actions = {
                    IconButton(
                        onClick = {
                            flashEnabled = !flashEnabled
                        }) {
                        Icon(
                            imageVector = if (flashEnabled) Icons.Default.FlashOff
                            else Icons.Default.FlashOn, contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = { finderEnabled = !finderEnabled }) {
                        Icon(
                            imageVector = if (finderEnabled) Icons.Outlined.LocationDisabled
                            else Icons.Outlined.LocationSearching, contentDescription = null
                        )
                    }
                })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ) {
            BarcodeScannerView(
                modifier = Modifier.fillMaxWidth().weight(1f),
                configuration = BarcodeCameraConfiguration(
                    detectionEnabled = true,
                    flashEnabled = flashEnabled,
                    cameraZoomFactor = 0.01f,
                    scannerConfiguration = scannerConfiguration,
                    overlayConfiguration = selectionOverlay,
                    finderConfiguration = finderConfiguration
                ),
                onBarcodesDetected = { detected ->
                    barcodes = detected
                },
                onError = { error ->
                    // Handle errors here, e.g. show a message to the user
                    println("Barcode scanner error: ${error.exception.message}")
                })

            if (barcodes.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    itemsIndexed(barcodes) { index, barcode ->
                        Text(
                            text = "Barcode №${index + 1}",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        BarcodeItemCard(barcode)

                        Spacer(Modifier.height(16.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Point the camera at a barcode", color = Color.Gray)
                }
            }
        }
    }
}