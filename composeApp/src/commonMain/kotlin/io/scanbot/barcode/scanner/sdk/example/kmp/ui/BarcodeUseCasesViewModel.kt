package io.scanbot.barcode.scanner.sdk.example.kmp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult

class BarcodeUseCasesViewModel : ViewModel() {
    var scanResult by mutableStateOf<BarcodeScannerUiResult?>(null)
        private set
    var error by mutableStateOf<Throwable?>(null)
        private set

    fun onScanResult(result: BarcodeScannerUiResult) {
        scanResult = result
    }

    fun onScanResultHandled() {
        scanResult = null
    }

    fun onError(error: Throwable) {
        this.error = error
    }

    fun onErrorDismissed() {
        error = null
    }
}
