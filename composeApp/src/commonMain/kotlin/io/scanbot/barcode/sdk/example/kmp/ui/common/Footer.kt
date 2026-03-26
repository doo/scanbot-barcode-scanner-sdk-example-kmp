package io.scanbot.barcode.sdk.example.kmp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Footer() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxWidth().background(Color(0xFFEEEEEE)).padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = {
                uriHandler.openUri("https://scanbot.io")
            }, contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Learn More About Scanbot SDK", color = ScanbotRed
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Copyright 2026 Scanbot SDK GmbH. All rights reserved.",
            fontSize = 10.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}