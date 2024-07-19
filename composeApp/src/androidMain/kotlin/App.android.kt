import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import io.scanbot.sdk.barcode_scanner.ScanbotBarcodeScannerSDKInitializer
import io.scanbot.sdk.ui_v2.barcode.BarcodeScannerView
import io.scanbot.sdk.ui_v2.barcode.configuration.BarcodeNativeConfiguration
import io.scanbot.sdk.ui_v2.barcode.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.ui_v2.barcode.configuration.LocalScanbotNativeConfiguration

@Composable
actual fun BarcodeScannerNativeView(onBarcodeScanned: OnBarcodeScanned) {
    val configuration = BarcodeScannerConfiguration()
    CompositionLocalProvider(
        LocalScanbotNativeConfiguration provides BarcodeNativeConfiguration(
            enableContinuousScanning = true
        )
    ) {
        BarcodeScannerView(
            configuration = configuration,
            onBarcodeScanned = {
                onBarcodeScanned.onBarcodeScanned(it.items.first().text)
            },
            onBarcodeScannerClosed = {
            }
        )
    }
}

@Composable
actual fun initializeScanbot(licenseKey: String) {
    val current = LocalContext.current
    LaunchedEffect(true) {
        val application = current.applicationContext as Application
        ScanbotBarcodeScannerSDKInitializer()
            .license(application, licenseKey)
            .initialize(application)
    }
}