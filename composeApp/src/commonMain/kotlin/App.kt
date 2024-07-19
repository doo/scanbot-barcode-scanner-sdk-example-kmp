import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf("") }

        /*
         * TODO: Add the Scanbot Barcode SDK license key here.
         * Please note: The Scanbot Barcode SDK will run without a license key for one minute per session!
         * After the trial period is over all Scanbot SDK functions as well as the UI components will stop working.
         * You can get an unrestricted "no-strings-attached" 30 day trial license key for free.
         * Please submit the trial license form (https://scanbot.io/sdk/trial.html) on our website by using
         * the app identifier "io.scanbot.example.sdk.barcode.android" of this example app.
         */
        val licenseKey = ""
        initializeScanbot(licenseKey)

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text)
                    Text("Compose: $greeting")
                    BarcodeScannerNativeView(object : OnBarcodeScanned {
                        override fun onBarcodeScanned(barcode: String) {
                            text = barcode
                        }
                    })
                }
            }
        }
    }
}

@Composable
expect fun BarcodeScannerNativeView(onBarcodeScanned: OnBarcodeScanned)

@Composable
expect fun initializeScanbot(licenseKey: String)

interface OnBarcodeScanned {
    fun onBarcodeScanned(barcode: String)
}