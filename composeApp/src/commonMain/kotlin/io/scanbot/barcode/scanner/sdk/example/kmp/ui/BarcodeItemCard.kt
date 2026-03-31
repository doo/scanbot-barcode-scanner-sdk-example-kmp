package io.scanbot.barcode.scanner.sdk.example.kmp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.kmp.barcode.BarcodeItem

@Composable
fun BarcodeItemCard(
    barcode: BarcodeItem
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            barcode.sourceImage?.encode()?.getOrNull()?.decodeToImageBitmap()?.let { bitmap ->
                Image(
                    bitmap = bitmap,
                    contentDescription = "Barcode image",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
            }

            Text(
                text = "Format:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(text = barcode.format.name)

            Text(
                text = "Text:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(text = barcode.text)

            barcode.extractedDocument?.let {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Extracted Document:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 5.dp)
                )
                _root_ide_package_.io.scanbot.barcode.scanner.sdk.example.kmp.ui.common.GenericDocumentView(
                    it
                )
            }
        }
    }
}