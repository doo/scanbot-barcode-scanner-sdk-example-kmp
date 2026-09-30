package io.scanbot.barcode.scanner.sdk.example.kmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanBarcodeFromPdf
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanBarcodeFromImageWithResult
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startArOverlayScanning
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startFindAndPickScanning
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMappingItemScanning
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMultiScanning
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startScanAndCount
import io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSingleScanning
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.ConfirmDialog
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.ErrorDialog
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.Footer
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.InfoDialog
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.LicenseInfoDialog
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.TopBar
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.rememberImagePickerLauncher
import io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.rememberPdfPickerLauncher
import io.scanbot.sdk.kmp.barcode.BarcodeScannerResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiItem
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import io.scanbot.sdk.kmp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun BarcodeUseCasesScreen(
    onResultPreview: (BarcodeScannerUiResult) -> Unit,
    navigateToBarcodeCustomUI: () -> Unit,
) {
    var showNoBarcodesFound by remember { mutableStateOf(false) }
    var useCaseError by remember { mutableStateOf<Throwable?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) { factory.createPermissionsController() }

    BindEffect(controller)

    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }
    var cameraPermissionPrompt by remember { mutableStateOf<CameraPermissionPrompt?>(null) }

    suspend fun requestCameraPermission() {
        try {
            controller.providePermission(Permission.CAMERA)
            navigateToBarcodeCustomUI()
        } catch (_: DeniedAlwaysException) {
            cameraPermissionPrompt = CameraPermissionPrompt.OpenSettings
        } catch (_: DeniedException) {
            useCaseError = IllegalStateException("Camera permission is required to use the Barcode Custom UI.")
        } catch (_: RequestCanceledException) {
            // The request was dismissed, nothing to do.
        }
    }

    fun handlePickerUseCaseResult(result: Result<BarcodeScannerResult>) {
        result.onSuccess {
            if (it.barcodes.isEmpty()) {
                showNoBarcodesFound = true
            } else {
                val items = it.barcodes.map { barcode -> BarcodeScannerUiItem(barcode, count = 1) }
                onResultPreview(BarcodeScannerUiResult(items = items))
            }
        }.onFailure({ useCaseError = it })
    }

    val launchImagePicker = rememberImagePickerLauncher(
        allowMultiple = false,
        onImagesSelected = { images ->
            handlePickerUseCaseResult(scanBarcodeFromImageWithResult(images.first()))
        },
        onError = { useCaseError = it }
    )

    val launchPdfPicker = rememberPdfPickerLauncher(
        onPdfSelected = { pdfPath ->
            val result = withContext(Dispatchers.Default) { scanBarcodeFromPdf(pdfPath) }
            handlePickerUseCaseResult(result)
        },
        onError = { useCaseError = it }
    )

    LicenseGuard { checkLicense ->
        Scaffold(topBar = {
            TopBar(title = "Scanbot SDK KMP Example")
        }, bottomBar = {
            Footer()
        }) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MenuItem("Single Scan with confirmation") {
                    checkLicense {
                        startSingleScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem("Multiple Scan") {
                    checkLicense {
                        startMultiScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem("Scan and Count") {
                    checkLicense {
                        startScanAndCount(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem("Find and Pick") {
                    checkLicense {
                        startFindAndPickScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem("Multiple Scan With AR Overlay") {
                    checkLicense {
                        startArOverlayScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem("Multiple Scan with Info Mapping") {
                    checkLicense {
                        startMappingItemScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem("Barcode Custom UI") {
                    checkLicense {
                        coroutineScope.launch {
                            when (controller.getPermissionState(Permission.CAMERA)) {
                                PermissionState.Granted -> navigateToBarcodeCustomUI()
                                // Previously denied: explain why the permission is needed before asking again.
                                PermissionState.Denied -> cameraPermissionPrompt = CameraPermissionPrompt.Rationale
                                // Permanently denied: the system won't show the dialog again, only the settings can help.
                                PermissionState.DeniedAlways -> cameraPermissionPrompt = CameraPermissionPrompt.OpenSettings
                                else -> requestCameraPermission()
                            }
                        }
                    }
                }
                MenuItem("Scan from Image") {
                    checkLicense {
                        launchImagePicker()
                    }
                }
                MenuItem("Scan from PDF") {
                    checkLicense {
                        launchPdfPicker()
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

                MenuItem("View License Info") {
                    showLicenseDialog = true
                }
            }

            if (showLicenseDialog) {
                LicenseInfoDialog(
                    onDismiss = { showLicenseDialog = false })
            }

            if (showNoBarcodesFound) {
                InfoDialog(
                    title = "No barcodes found",
                    text = "No barcodes were detected.",
                    onDismiss = { showNoBarcodesFound = false })
            }

            useCaseError?.let {
                ErrorDialog(
                    message = it.message, onDismiss = { useCaseError = null })
            }

            when (cameraPermissionPrompt) {
                CameraPermissionPrompt.Rationale -> ConfirmDialog(
                    title = "Camera permission",
                    text = "The Barcode Custom UI needs access to the camera to scan barcodes.",
                    confirmText = "Continue",
                    onConfirm = {
                        cameraPermissionPrompt = null
                        coroutineScope.launch { requestCameraPermission() }
                    },
                    onDismiss = { cameraPermissionPrompt = null })

                CameraPermissionPrompt.OpenSettings -> ConfirmDialog(
                    title = "Camera permission",
                    text = "Camera access was denied. Enable it in the app settings to use the Barcode Custom UI.",
                    confirmText = "Open settings",
                    onConfirm = {
                        cameraPermissionPrompt = null
                        controller.openAppSettings()
                    },
                    onDismiss = { cameraPermissionPrompt = null })

                null -> Unit
            }
        }
    }
}

private enum class CameraPermissionPrompt { Rationale, OpenSettings }
