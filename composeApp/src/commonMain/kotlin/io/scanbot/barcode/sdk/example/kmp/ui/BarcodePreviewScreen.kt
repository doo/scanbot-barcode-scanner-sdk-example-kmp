package io.scanbot.barcode.sdk.example.kmp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.scanbot.barcode.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult

@Composable
fun BarcodePreviewScreen(
    resultJson: String,
    onPopBackStack: () -> Unit,
) {
    val parsedResult = remember(resultJson) {
        BarcodeScannerUiResult.fromJson(resultJson)
    }

    Scaffold(
        topBar = {
            TopBar(title = "Barcodes preview", showBackButton = true, onPopBackStack)
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(parsedResult.items) { index, item ->
                Text(
                    text = "Barcode №${index + 1}",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold
                )

                BarcodeItemCard(item.barcode)
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}