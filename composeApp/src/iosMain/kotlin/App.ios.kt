import ScanbotBarcodeScannerSDK.Scanbot
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun BarcodeScannerNativeView(onBarcodeScanned: OnBarcodeScanned) {
    UIKitViewController(
        factory = { BarcodeScannerViewController(onBarcodeScanned) },
        modifier = Modifier.fillMaxSize(),
    )
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun initializeScanbot(licenseKey: String) {
    LaunchedEffect(true) {
        if (licenseKey.isNotEmpty()) {
            Scanbot.setLicense(licenseKey)
        }
        Scanbot.initialize()
    }
}