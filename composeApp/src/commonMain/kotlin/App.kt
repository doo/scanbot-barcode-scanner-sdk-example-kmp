import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.scanbot.sdk.compose.multiplatform.common.ScanbotSdkConfig
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerResult
import io.scanbot.sdk.compose.multiplatform.sdk.ScanbotSDK
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import screens.BarcodeScannerScreen
import screens.HomeScreen
import screens.ResultScreen
import use_cases.UseCaseSnippet

object ScanbotConfigs {
    /**
     * TODO Add the Scanbot Barcode Scanner SDK license key here.
     * Please note: Scanbot Barcode Scanner SDK will run without a license key for one minute per session!
     * After the trial period has expired all SDK features and UI components will stop working.
     * You can get a free "no-strings-attached" trial license key. Please submit the trial license
     * form (https://scanbot.io/trial) on our website by using the app identifier
     * "io.scanbot.example.kmpbarcodescanner" of this example app.
     */
    const val LICENSE_KEY = ""
}

@Serializable
object Home

@Serializable
data class BarcodeScanner(
    var useCaseSnippet: String
)

@Serializable
data class ScannerResult(
    var barcodeScannerResult: String
)

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {
    val scanbotSdkConfig = remember {
        ScanbotSdkConfig(
            licenseKey = ScanbotConfigs.LICENSE_KEY,
            loggingEnabled = true
        )
    }

    ScanbotSDK.initialize(scanbotSdkConfig)
    NavigationGraph(navController)
}


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(onNavigateToScanner = { useCaseSnippet ->
                navController.navigate(BarcodeScanner(useCaseSnippet.toString()))
            })
        }

        composable<BarcodeScanner> { backStackEntry ->
            backStackEntry.arguments?.getString("useCaseSnippet")?.let {
                BarcodeScannerScreen(
                    useCaseSnippet = UseCaseSnippet.valueOf(it),
                    onScanComplete = { result ->
                        navController.navigate(ScannerResult(result))
                    },
                    onScannerClosed = {
                        navController.navigateUp()
                    }
                )
            }
        }

        composable<ScannerResult> { backStackEntry ->
            backStackEntry.arguments?.getString("barcodeScannerResult")?.let {
                val result = parseBarcodeScannerResult(it)

                ResultScreen(
                    barcodeScannerResult = result,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

fun parseBarcodeScannerResult(json: String): BarcodeScannerResult {
    return BarcodeScannerResult(Json.parseToJsonElement(json).jsonObject)
}

