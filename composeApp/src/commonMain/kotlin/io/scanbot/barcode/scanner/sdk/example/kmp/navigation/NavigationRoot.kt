package io.scanbot.barcode.scanner.sdk.example.kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.BarcodeCustomUIScreen
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.BarcodePreviewScreen
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.BarcodeUseCasesScreen

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    val onPopBackStack: () -> Unit = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = Route.BarcodeUseCases
    ) {
        composable<Route.BarcodeUseCases> {
            BarcodeUseCasesScreen(
                onResultPreview = { result ->
                    navController.navigate(Route.BarcodePreview(result.toJsonString()))
                },
                navigateToBarcodeCustomUI = { navController.navigate(Route.BarcodeCustomUI) },
            )
        }

        composable<Route.BarcodePreview> { backStackEntry ->
            val screen: Route.BarcodePreview = backStackEntry.toRoute()
            BarcodePreviewScreen(
                resultJson = screen.barcodeJson, onPopBackStack = onPopBackStack
            )
        }

        composable<Route.BarcodeCustomUI> {
            BarcodeCustomUIScreen(
                onPopBackStack = onPopBackStack
            )
        }
    }
}