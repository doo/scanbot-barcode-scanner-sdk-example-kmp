package io.scanbot.barcode.scanner.sdk.example.kmp.ui.common

import androidx.compose.runtime.Composable
import io.github.ismoy.imagepickerkmp.domain.extensions.loadBytes
import io.github.ismoy.imagepickerkmp.presentation.ui.components.GalleryPickerLauncher
import io.scanbot.sdk.kmp.image.ImageRef

@Composable
fun GalleryPicker(
    allowMultiple: Boolean = true,
    onImagesSelected: (List<ImageRef>) -> Unit,
    onDismiss: () -> Unit,
) {
    GalleryPickerLauncher(allowMultiple = allowMultiple, onPhotosSelected = { photos ->
        val imageRefs = photos.mapNotNull { photo ->
            photo.loadBytes().let(ImageRef::fromEncodedBuffer)
        }

        if (imageRefs.isNotEmpty()) {
            onImagesSelected(imageRefs)
        } else {
            onDismiss()
        }
    }, onDismiss = onDismiss, onError = {})
}
