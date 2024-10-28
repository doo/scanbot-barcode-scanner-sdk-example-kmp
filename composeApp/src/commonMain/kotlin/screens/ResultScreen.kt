package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.compose.multiplatform.configuration.BarcodeScannerResult

@Composable
fun ResultScreen(
    barcodeScannerResult: BarcodeScannerResult,
    onBack: () -> Unit,
    backgroundColor: Color = Color.LightGray
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Scanned Result:",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp)
            )

            barcodeScannerResult.items.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(8.dp)
                        .border(1.dp, Color.Gray)
                        .padding(8.dp)
                ) {
                    Text(text = "Item #${index + 1}")
                    Text(text = "Text: ${item.text}")
                    Text(text = "Text with Extension: ${item.textWithExtension}")
                    Text(text = "Type: ${item.type}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
