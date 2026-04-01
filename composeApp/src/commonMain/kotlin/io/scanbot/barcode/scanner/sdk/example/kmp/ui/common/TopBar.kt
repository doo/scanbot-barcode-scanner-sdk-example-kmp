package io.scanbot.barcode.scanner.sdk.example.kmp.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean = false,
    onPopBackStack: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    var enabled by remember { mutableStateOf(true) }

    TopAppBar(
        title = {
            Text(
                text = title, fontSize = 20.sp, color = Color.White
            )
        }, navigationIcon = {
            if (showBackButton) {
                IconButton(
                    onClick = {
                        enabled = false
                        onPopBackStack()
                    }, enabled = enabled
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }, actions = actions, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ScanbotRed,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}
