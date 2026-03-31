package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.detailed_setup_guide

import io.scanbot.sdk.kmp.image.BufferImageLoadOptions
import io.scanbot.sdk.kmp.image.EncodeImageOptions
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.image.PathImageLoadOptions
import io.scanbot.sdk.kmp.image.SaveImageOptions

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

class ImageRefSnippets {

    fun createImageRefFromPath() {
        // @Tag("Create ImageRef from file path")
        val imageRef = ImageRef.fromPath(
            path = "path/to/image.jpg",
            options = PathImageLoadOptions()
        )
        // @EndTag("Create ImageRef from file path")
    }

    // @Tag("Create ImageRef from encoded buffer")
    fun createImageRefFromBuffer(imageBytes: ByteArray) {
        val imageRef = ImageRef.fromEncodedBuffer(
            buffer = imageBytes,
            options = BufferImageLoadOptions()
        )
    }
    // @EndTag("Create ImageRef from encoded buffer")

    fun getImageInformation() {
        // @Tag("Get image information")
        val imageRef = ImageRef.fromPath("path/to/image.jpg")

        imageRef?.info()?.onSuccess { info ->
            println("Image width: ${info.width}")
            println("Image height: ${info.height}")
            // Access other image metadata from ImageInfo
        }?.onFailure { error ->
            println("Failed to get image info: ${error.message}")
        }
        // @EndTag("Get image information")
    }

    fun saveImageToFile(imageBytes: ByteArray) {
        // @Tag("Save image to file")
        val imageRef = ImageRef.fromEncodedBuffer(imageBytes)

        imageRef?.save(
            path = "path/to/output.jpg",
            options = SaveImageOptions()
        )?.onSuccess {
            println("Image saved successfully")
        }?.onFailure { error ->
            println("Failed to save image: ${error.message}")
        }
        // @EndTag("Save image to file")
    }

    fun encodeImageToBytes() {
        // @Tag("Encode image to bytes")
        val imageRef = ImageRef.fromPath("path/to/image.jpg")

        imageRef?.encode(
            options = EncodeImageOptions()
        )?.onSuccess { bytes ->
            // Use the encoded bytes (e.g., upload to server, save to file)
            println("Encoded image size: ${bytes.size} bytes")
        }?.onFailure { error ->
            println("Failed to encode image: ${error.message}")
        }
        // @EndTag("Encode image to bytes")
    }
}