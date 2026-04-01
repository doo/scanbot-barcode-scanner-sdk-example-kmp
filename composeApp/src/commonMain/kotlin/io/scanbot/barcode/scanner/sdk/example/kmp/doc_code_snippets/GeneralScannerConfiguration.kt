package io.scanbot.barcode.scanner.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.barcode.AustraliaPostCustomerFormat
import io.scanbot.sdk.kmp.barcode.BarcodeDocumentFormat
import io.scanbot.sdk.kmp.barcode.BarcodeFormat
import io.scanbot.sdk.kmp.barcode.BarcodeFormatAustraliaPostConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatCode11Configuration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatCode2Of5Configuration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatCommonConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormatConfigurationBase
import io.scanbot.sdk.kmp.barcode.BarcodeFormatMsiPlesseyConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeFormats
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerEngineMode
import io.scanbot.sdk.kmp.barcode.Gs1Handling
import io.scanbot.sdk.kmp.barcode.MsiPlesseyChecksumAlgorithm
import io.scanbot.sdk.kmp.image.ImageRef

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

class GeneralScannerConfiguration {

    fun filterIndividualBarcodeSymbologies() {
        // @Tag("Filter individual barcode symbologies")
        val baseConfig = BarcodeFormatCommonConfiguration.default().copy(
            formats = listOf(BarcodeFormat.QR_CODE, BarcodeFormat.AZTEC, BarcodeFormat.CODE_128)
        )
        // @EndTag("Filter individual barcode symbologies")
    }

    fun filterGroupsOfBarcodeSymbologies() {
        // @Tag("Filter groups of barcode symbologies")
        val baseConfig = BarcodeFormatCommonConfiguration.default().copy(
            formats = BarcodeFormats.common
        )
        // @EndTag("Filter groups of barcode symbologies")
    }

    fun advancedBarcodeScannerConfiguration(imageRef: ImageRef) {
        // @Tag("Advanced barcode scanner configuration")
        val configuration = BarcodeScannerConfiguration()

        val configs = mutableListOf<BarcodeFormatConfigurationBase>()

        val baseConfig = BarcodeFormatCommonConfiguration.default().copy(
            regexFilter = "",
            minimum1DQuietZoneSize = 10,
            stripCheckDigits = false,
            minimumTextLength = 0,
            maximumTextLength = 0,
            gs1Handling = Gs1Handling.PARSE,
            strictMode = true,
            formats = BarcodeFormats.common,
            addAdditionalQuietZone = false
        )
        configs.add(baseConfig)

        // Add individual configurations for specific barcode formats
        val australiaPostConfig = BarcodeFormatAustraliaPostConfiguration(
            regexFilter = "",
            australiaPostCustomerFormat = AustraliaPostCustomerFormat.ALPHA_NUMERIC
        )
        configs.add(australiaPostConfig)

        val msiPlesseyConfig = BarcodeFormatMsiPlesseyConfiguration(
            regexFilter = "",
            minimum1DQuietZoneSize = 10,
            stripCheckDigits = false,
            minimumTextLength = 0,
            maximumTextLength = 0,
            checksumAlgorithms = listOf(MsiPlesseyChecksumAlgorithm.MOD_10)
        )
        configs.add(msiPlesseyConfig)

        val code11Config = BarcodeFormatCode11Configuration(
            regexFilter = "",
            minimum1DQuietZoneSize = 10,
            stripCheckDigits = false,
            minimumTextLength = 0,
            maximumTextLength = 0,
            checksum = true
        )
        configs.add(code11Config)

        val code2Of5Config = BarcodeFormatCode2Of5Configuration(
            regexFilter = "",
            minimum1DQuietZoneSize = 10,
            stripCheckDigits = false,
            minimumTextLength = 0,
            maximumTextLength = 0,
            iata2of5 = true,
            code25 = false,
            industrial2of5 = false,
            useIATA2OF5Checksum = true
        )
        configs.add(code2Of5Config)

        // Set the configurations
        configuration.barcodeFormatConfigurations = configs
        configuration.extractedDocumentFormats = listOf(
            BarcodeDocumentFormat.AAMVA,
            BarcodeDocumentFormat.BOARDING_PASS,
            BarcodeDocumentFormat.DE_MEDICAL_PLAN,
            BarcodeDocumentFormat.MEDICAL_CERTIFICATE,
            BarcodeDocumentFormat.ID_CARD_PDF_417,
            BarcodeDocumentFormat.SEPA,
            BarcodeDocumentFormat.SWISS_QR,
            BarcodeDocumentFormat.VCARD,
            BarcodeDocumentFormat.GS1,
            BarcodeDocumentFormat.HIBC
        )
        configuration.onlyAcceptDocuments = false
        configuration.engineMode = BarcodeScannerEngineMode.NEXT_GEN
        configuration.returnBarcodeImage = true

        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef,
            configuration = configuration
        )
        // @EndTag("Advanced barcode scanner configuration")
    }

    fun configureDocumentParsers(imageRef: ImageRef) {
        // @Tag("Configure document parsers")
        val configuration = BarcodeScannerConfiguration().apply {
            barcodeFormatConfigurations = mutableListOf(BarcodeFormatCommonConfiguration())
            // Example of adding a specific configuration for parsed documents
            extractedDocumentFormats = listOf(
                BarcodeDocumentFormat.AAMVA,
                BarcodeDocumentFormat.BOARDING_PASS,
                BarcodeDocumentFormat.DE_MEDICAL_PLAN,
                BarcodeDocumentFormat.MEDICAL_CERTIFICATE,
                BarcodeDocumentFormat.ID_CARD_PDF_417,
                BarcodeDocumentFormat.SEPA,
                BarcodeDocumentFormat.SWISS_QR,
                BarcodeDocumentFormat.VCARD,
                BarcodeDocumentFormat.GS1,
                BarcodeDocumentFormat.HIBC
            )
            onlyAcceptDocuments =
                true // Set to true if you want to only accept barcode with parsed documents
            engineMode = BarcodeScannerEngineMode.NEXT_GEN

        }
        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef,
            configuration = configuration
        )
        // @EndTag("Configure document parsers")
    }

    fun configureRegexFilter(imageRef: ImageRef) {
        // @Tag("Configure regex filter")
        val configuration = BarcodeScannerConfiguration().apply {
            barcodeFormatConfigurations = mutableListOf<BarcodeFormatConfigurationBase>(
                BarcodeFormatCommonConfiguration.default().copy(
                    // You can set a regex filter here to limit the barcodes that will be scanned
                    // Here is an example of a regex that matches only barcodes contained numbers from 0 to 5
                    regexFilter = "\b[0-5]+\b",
                    minimum1DQuietZoneSize = 10,
                    stripCheckDigits = false,
                    minimumTextLength = 0,
                    maximumTextLength = 0,
                    gs1Handling = Gs1Handling.PARSE,
                    strictMode = true,
                    formats = BarcodeFormats.common,
                    addAdditionalQuietZone = false
                )
            )
            // Example of adding a specific configuration for parsed documents
            engineMode = BarcodeScannerEngineMode.NEXT_GEN
        }

        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef,
            configuration = configuration
        )
        // @EndTag("Configure regex filter")
    }
}