package io.scanbot.barcode.sdk.example.kmp.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import io.scanbot.sdk.kmp.ScanbotSDK

@Composable
fun InfoDialog(
    title: String, text: String, onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismiss, title = { Text(title) }, text = {
        Text(text = text, fontFamily = FontFamily.Monospace)
    }, confirmButton = {
        TextButton(onClick = onDismiss) {
            Text("Close")
        }
    })
}

@Composable
fun ErrorDialog(
    title: String = "Error", message: String?, onDismiss: () -> Unit
) {
    InfoDialog(title, message ?: "Unknown Error", onDismiss)
}

@Composable
fun LicenseInfoDialog(onDismiss: () -> Unit) {

    val text = ScanbotSDK.getLicenseInfo().fold(onSuccess = { info ->
        "Status: ${info.status.name}\nExpirationDate: ${info.expirationDateString}"
    }, onFailure = {
        "Error getting license status"
    })

    InfoDialog("License Info", text, onDismiss)
}
