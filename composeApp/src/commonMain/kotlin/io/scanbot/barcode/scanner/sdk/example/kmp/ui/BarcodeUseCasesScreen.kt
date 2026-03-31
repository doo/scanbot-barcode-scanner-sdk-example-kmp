package io.scanbot.barcode.scanner.sdk.example.kmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.window.Dialog
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import io.scanbot.sdk.kmp.barcode.BarcodeItem
import io.scanbot.sdk.kmp.barcode.BarcodeScannerResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import kotlinx.coroutines.launch

@Composable
fun BarcodeUseCasesScreen(
    onResultPreview: (BarcodeScannerUiResult) -> Unit,
    navigateToBarcodeCustomUI: () -> Unit,
) {
    var scanFromImageResult by remember { mutableStateOf<BarcodeScannerResult?>(null) }
    var useCaseError by remember { mutableStateOf<Throwable?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) { factory.createPermissionsController() }

    BindEffect(controller)

    var showGalleryPicker by remember { mutableStateOf(false) }
    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }

    _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.LicenseGuard { checkLicense ->
        Scaffold(topBar = {
            _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.TopBar(title = "Scanbot SDK KMP Example")
        }, bottomBar = {
            _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.Footer()
        }) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Single Scan with confirmation") {
                    checkLicense {
                        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSingleScanning(
                            onResultPreview,
                            onErrorHandler = { useCaseError = it })
                    }
                }
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Multiple Scan") {
                    checkLicense {
                        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMultiScanning(
                            onResultPreview,
                            onErrorHandler = { useCaseError = it })
                    }
                }
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Scan and Count") {
                    checkLicense {
                        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startScanAndCount(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Find and Pick") {
                    checkLicense {
                        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startFindAndPickScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Multiple Scan With AR Overlay") {
                    checkLicense {
                        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startArOverlayScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Multiple Scan with Info Mapping") {
                    checkLicense {
                        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMappingItemScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Barcode Custom UI") {
                    checkLicense {
                        coroutineScope.launch {
                            try {
                                if (!controller.isPermissionGranted(Permission.CAMERA)) {
                                    controller.providePermission(Permission.CAMERA)
                                }
                                navigateToBarcodeCustomUI()
                            } catch (e: Exception) {
                                println("Camera permission error: ${e.message}")
                            }
                        }
                    }
                }
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("Scan from Image") {
                    checkLicense {
                        showGalleryPicker = true
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.MenuItem("View License Info") {
                    showLicenseDialog = true
                }
            }

            if (showGalleryPicker) {
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.GalleryPicker(
                    allowMultiple = false,
                    onImagesSelected = { images ->
                        showGalleryPicker = false
                        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.scanBarcodeFromImageWithResult(
                            images.first()
                        ).onSuccess {
                            scanFromImageResult = it
                        }.onFailure {
                            useCaseError = it
                        }
                    },
                    onDismiss = { showGalleryPicker = false })
            }

            if (showLicenseDialog) {
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.LicenseInfoDialog(
                    onDismiss = { showLicenseDialog = false })
            }

            scanFromImageResult?.let { result ->
                ImageScanningResult(result.barcodes, onDismiss = { scanFromImageResult = null })
            }

            useCaseError?.let {
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.ErrorDialog(
                    message = it.message,
                    onDismiss = { useCaseError = null })
            }
        }
    }
}

@Composable
fun ImageScanningResult(barcodeItems: List<BarcodeItem>, onDismiss: () -> Unit) {
    if (barcodeItems.isEmpty()) {
        _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.InfoDialog(
            title = "No barcodes found",
            text = "No barcodes were detected in the selected image.",
            onDismiss = onDismiss
        )
    } else {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(
                    "Scanned Barcodes",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                BarcodeItemsPreview(
                    modifier = Modifier
                        .heightIn(max = 350.dp), items = barcodeItems
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}
