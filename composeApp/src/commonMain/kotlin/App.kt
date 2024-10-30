import androidx.compose.runtime.*
import io.scanbot.sdk.compose.multiplatform.common.ScanbotSdkConfig
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerResult
import io.scanbot.sdk.compose.multiplatform.sdk.ScanbotSDK
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import screens.*

@Serializable
object Home

@Serializable
data class BarcodeScanner(
    var scannerConfiguration: String
)

@Serializable
data class ScannerResult(
    var barcodeScannerResult: String
)

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {
    val scanbotSdkConfig = remember { ScanbotSdkConfig() }

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
            HomeScreen(onNavigateToScanner = { config ->
                val configJson = config.toJson().toString()
                navController.navigate(BarcodeScanner(configJson))
            })
        }

        composable<BarcodeScanner> { backStackEntry ->
            backStackEntry.arguments?.getString("scannerConfiguration")?.let {
                val configuration = parseBarcodeScannerConfiguration(it)
                BarcodeScannerScreen(
                    configuration = configuration,
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

fun parseBarcodeScannerConfiguration(json: String): BarcodeScannerConfiguration {
    return BarcodeScannerConfiguration(Json.parseToJsonElement(json).jsonObject)
}

fun parseBarcodeScannerResult(json: String): BarcodeScannerResult {
    return BarcodeScannerResult(Json.parseToJsonElement(json).jsonObject)
}

