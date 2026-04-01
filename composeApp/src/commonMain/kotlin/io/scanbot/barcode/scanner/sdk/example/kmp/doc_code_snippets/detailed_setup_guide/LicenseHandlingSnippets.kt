package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets.detailed_setup_guide

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.licensing.LicenseStatus

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

class LicenseHandlingSnippets {

    fun checkLicenseStatus() {
        // @Tag("Check license status")
        val licenseInfo = ScanbotSDK.getLicenseInfo()

        licenseInfo.onSuccess { info ->
            println("License status: ${info.status}")
            println("Is valid: ${info.isValid}")
            println("Expiration date: ${info.expirationDateString}")
        }.onFailure { exception ->
            println("Failed to get license info: ${exception.message}")
        }
        // @EndTag("Check license status")
    }

    fun handleLicenseStatus() {
        // @Tag("Handle license status")
        ScanbotSDK.getLicenseInfo().onSuccess { licenseInfo ->
            when (licenseInfo.status) {
                LicenseStatus.OKAY -> {
                    // License is valid - proceed with SDK operations
                    println("License is valid until: ${licenseInfo.expirationDateString}")
                }

                LicenseStatus.OKAY_EXPIRING_SOON -> {
                    // License will expire soon - notify user to renew
                    println("Warning: License expires soon on ${licenseInfo.expirationDateString}")
                }

                LicenseStatus.TRIAL -> {
                    // SDK is in trial mode
                    println("SDK is running in trial mode")
                }

                LicenseStatus.FAILURE_EXPIRED -> {
                    // License has expired
                    println("License expired on ${licenseInfo.expirationDateString}")
                }

                LicenseStatus.FAILURE_APP_ID_MISMATCH -> {
                    // License doesn't match the app bundle ID
                    println("License does not match app bundle identifier")
                }

                LicenseStatus.FAILURE_NOT_SET -> {
                    // No license set and trial has ended
                    println("No license set. Trial period has ended")
                }

                else -> {
                    // Handle other failure cases
                    println("License error: ${licenseInfo.licenseStatusMessage}")
                    if (licenseInfo.errorMessage.isNotEmpty()) {
                        println("Error details: ${licenseInfo.errorMessage}")
                    }
                }
            }
        }.onFailure { exception ->
            // Handle error when getting license info
            println("Failed to retrieve license info: ${exception.message}")
        }
        // @EndTag("Handle license status")
    }
}