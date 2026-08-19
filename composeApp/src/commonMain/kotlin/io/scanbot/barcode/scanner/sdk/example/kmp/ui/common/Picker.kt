package io.scanbot.barcode.scanner.sdk.example.kmp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.cacheDir
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.div
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.readBytes
import io.github.vinceglb.filekit.write
import io.scanbot.sdk.kmp.image.ImageRef
import kotlinx.coroutines.launch
import kotlin.random.Random

/** Native gallery picker returning decoded [ImageRef]s for the Scanbot SDK. */
@Composable
fun rememberImagePickerLauncher(
    allowMultiple: Boolean,
    onImagesSelected: (List<ImageRef>) -> Unit,
    onError: (Throwable) -> Unit = {},
    onDismiss: () -> Unit = {},
): () -> Unit {
    val scope = rememberCoroutineScope()
    val handle: (List<PlatformFile>) -> Unit = { files ->
        if (files.isEmpty()) onDismiss()
        else scope.launch {
            runCatching { files.map { it.toImageRef() } }
                .onSuccess(onImagesSelected)
                .onFailure(onError)
        }
    }

    val launcher = if (allowMultiple) {
        rememberFilePickerLauncher(FileKitType.Image, FileKitMode.Multiple()) { handle(it.orEmpty()) }
    } else {
        rememberFilePickerLauncher(FileKitType.Image, FileKitMode.Single) { handle(listOfNotNull(it)) }
    }
    return { launcher.launch() }
}

/** Native PDF picker. Copies the file to cache so the SDK gets a real file path. */
@Composable
fun rememberPdfPickerLauncher(
    onPdfSelected: (path: String) -> Unit,
    onError: (Throwable) -> Unit = {},
    onDismiss: () -> Unit = {},
): () -> Unit {
    val scope = rememberCoroutineScope()
    val launcher = rememberFilePickerLauncher(
        type = FileKitType.File(extensions = listOf("pdf")),
        mode = FileKitMode.Single,
    ) { file ->
        if (file == null) onDismiss()
        else scope.launch {
            runCatching { file.copyToCache() }
                .onSuccess(onPdfSelected)
                .onFailure(onError)
        }
    }
    return { launcher.launch() }
}

private suspend fun PlatformFile.toImageRef(): ImageRef =
    ImageRef.fromEncodedBuffer(readBytes())
        ?: error("Failed to decode image: $name")

private suspend fun PlatformFile.copyToCache(): String {
    val target = FileKit.cacheDir / "picked-${Random.nextLong().toString(16)}-${name.ifBlank { "picked" }}"
    target.write(readBytes())
    return target.absolutePath()
}