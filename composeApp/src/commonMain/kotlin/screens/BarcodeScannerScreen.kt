package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeItem
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerResult
import io.scanbot.sdk.compose.multiplatform.scanner.BarcodeScannerView
import io.scanbot.sdk.compose.multiplatform.scanner.IBarcodeScannerViewOperations

@Composable
fun BarcodeScannerScreen(
    configuration: BarcodeScannerConfiguration,
    onScanComplete: (String) -> Unit,
    onScannerClosed: () -> Unit,
) {
    var serializedResult by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(serializedResult) {
        serializedResult?.let { result ->
            onScanComplete(result)
            serializedResult = null
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        BarcodeContent(
            configuration = configuration,
            onBarcodeScanned = { barcodes ->
                val result = BarcodeScannerResult(items = barcodes.orEmpty())
                serializedResult = result.toJson().toString()
            },
            onScannerClosed = onScannerClosed
        )
    }
}

@Composable
private fun BarcodeContent(
    configuration: BarcodeScannerConfiguration,
    onBarcodeScanned: (List<BarcodeItem>?) -> Unit,
    onScannerClosed: () -> Unit
) {
    BarcodeScannerView(
        barcodeScannerConfiguration = configuration,
        barcodeScannerOperations = remember {
            object : IBarcodeScannerViewOperations {
                override fun onBarcodeScanned(barcodes: List<BarcodeItem>?) {
                    onBarcodeScanned(barcodes)
                }

                override fun onScannerClosed() {
                    onScannerClosed()
                }
            }
        }
    )
}
