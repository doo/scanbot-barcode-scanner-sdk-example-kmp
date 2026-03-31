package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.barcode.BarcodeScannerResult
import io.scanbot.sdk.kmp.common.InvalidDataError
import io.scanbot.sdk.kmp.common.InvalidImageRefError
import io.scanbot.sdk.kmp.common.InvalidLicenseError
import io.scanbot.sdk.kmp.common.IoError
import io.scanbot.sdk.kmp.common.OutOfMemoryError
import io.scanbot.sdk.kmp.common.TimeoutError
import io.scanbot.sdk.kmp.utils.Result

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

class ResultApiSnippets {

    // @Tag("Handle Result with when expression")
    fun processResultWithSwitch(result: Result<BarcodeScannerResult>) {
        when (result) {
            is Result.Success -> {
                val barcodeResult = result.value
                // Handle successful result
            }

            is Result.Failure -> {
                val exception = result.exception
                // Handle error
                println("Error scanning barcodes: ${exception.message}")
            }
        }
    }
    // @EndTag("Handle Result with when expression")

    // @Tag("Handle specific error types")
    fun handleSpecificErrors(result: Result<BarcodeScannerResult>) {
        result.onFailure { exception ->
            when (exception) {
                is InvalidLicenseError -> {
                    // Handle license error - show license activation UI
                    println("License is invalid or expired")
                }

                is InvalidImageRefError -> {
                    // Handle invalid image - prompt user to capture again
                    println("Image is corrupted or invalid")
                }

                is TimeoutError -> {
                    // Handle timeout - retry or inform user
                    println("Operation timed out, please try again")
                }

                is InvalidDataError -> {
                    // Handle invalid data
                    println("Invalid data encountered")
                }

                is OutOfMemoryError -> {
                    // Handle out of memory
                    println("Out of memory error")
                }

                is IoError -> {
                    // Handle I/O error - check storage permissions
                    println("File operation failed: ${exception.message}")
                }

                else -> {
                    // Handle other errors
                    println("Error: ${exception.message}")
                }
            }
        }
    }
    // @EndTag("Handle specific error types")

    // @Tag("Handle Result with getters")
    fun proceedWithGetters(result: Result<BarcodeScannerResult>) {
        // Get value or null if unsuccessful
        val nullableResult = result.getOrNull()

        // Get value or throw exception if unsuccessful
        val nonNullableResult = result.getOrThrow()

        // Get value or provide default value if unsuccessful
        val resultOrDefaultValue = result.recover {
            BarcodeScannerResult(emptyList(), false)
        }.getOrNull()
    }
    // @EndTag("Handle Result with getters")

    // @Tag("Handle Result with chain API")
    fun processResultWithChainApi(result: Result<BarcodeScannerResult>) {
        // Handle success and failure in a chained way
        result.onSuccess { barcodeResult ->
            // Handle successful barcode scanning result
            val barcodes = barcodeResult.barcodes
            // Process barcodes as needed
        }.onFailure { exception ->
            // Handle error during barcode scanning
            println("Error scanning barcodes: ${exception.message}")
        }

        // Chained transformations
        val barcodeSize = result.map { barcodeResult ->
            // Transform the successful result to the number of barcodes found
            barcodeResult.barcodes.size
        }.recover {
            // Return 0 if the scanning failed
            0
        }.getOrNull()
    }
    // @EndTag("Handle Result with chain API")

    // @Tag("Handle Result with fold function")
    fun handleResultWithFold(result: Result<BarcodeScannerResult>) {
        val message = result.fold(
            onSuccess = { barcodeResult ->
                "Found ${barcodeResult.barcodes.size} barcodes"
            },
            onFailure = { exception ->
                "Error: ${exception.message}"
            }
        )
    }
    // @EndTag("Handle Result with fold function")
}