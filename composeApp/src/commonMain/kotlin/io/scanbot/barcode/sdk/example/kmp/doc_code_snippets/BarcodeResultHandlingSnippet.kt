package io.scanbot.barcode.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.barcode.BarcodeDocumentFormat
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.genericdocument.barcode.AAMVA
import io.scanbot.sdk.kmp.genericdocument.barcode.BoardingPass
import io.scanbot.sdk.kmp.genericdocument.barcode.BritishColumbiaDriverLicense
import io.scanbot.sdk.kmp.genericdocument.barcode.DEMedicalPlan
import io.scanbot.sdk.kmp.genericdocument.barcode.GS1
import io.scanbot.sdk.kmp.genericdocument.barcode.HIBC
import io.scanbot.sdk.kmp.genericdocument.barcode.IDCardPDF417
import io.scanbot.sdk.kmp.genericdocument.barcode.MedicalCertificate
import io.scanbot.sdk.kmp.genericdocument.barcode.SEPA
import io.scanbot.sdk.kmp.genericdocument.barcode.SwissQR
import io.scanbot.sdk.kmp.genericdocument.barcode.VCard
import io.scanbot.sdk.kmp.image.ImageRef

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

class BarcodeResultHandlingSnippets {

    fun handlingBarcodeResult(imageRef: ImageRef) {
        // @Tag("Handling barcode result")
        val configuration = BarcodeScannerConfiguration()
        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef,
            configuration = configuration
        )

        result.getOrNull()?.barcodes?.forEach { barcodeItem ->
            // Handle the detected barcode(s) from result
            val barcodeText = barcodeItem.text
            val barcodeFormat = barcodeItem.format

            // The barcodeItem contains the scanned barcode data as ByteArray
            val barcodeRawData = barcodeItem.rawBytes

            // This is the image of the barcode that was scanned (SDK's internal representation)
            val barcodeImage = barcodeItem.sourceImage
        }
        // @EndTag("Handling barcode result")
    }

    fun configureBarcodeParsers(imageRef: ImageRef) {
        // @Tag("Configure barcode parsers")
        val configuration = BarcodeScannerConfiguration()

        // Enable parsing of specific document formats
        configuration.extractedDocumentFormats = listOf(
            BarcodeDocumentFormat.AAMVA,
            BarcodeDocumentFormat.BOARDING_PASS,
            BarcodeDocumentFormat.MEDICAL_CERTIFICATE,
            BarcodeDocumentFormat.SEPA,
            BarcodeDocumentFormat.SWISS_QR,
            BarcodeDocumentFormat.VCARD
        )

        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef,
            configuration = configuration
        )
        // @EndTag("Configure barcode parsers")
    }

    fun handleParsedDocumentResult(imageRef: ImageRef) {
        // @Tag("Handle parsed document result")
        val configuration = BarcodeScannerConfiguration()
        configuration.extractedDocumentFormats = listOf(
            BarcodeDocumentFormat.AAMVA,
            BarcodeDocumentFormat.BOARDING_PASS,
            BarcodeDocumentFormat.MEDICAL_CERTIFICATE
        )

        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef,
            configuration = configuration
        )

        result.getOrNull()?.barcodes?.forEach { barcodeItem ->
            // Check if the barcode contains parsed document data
            val parsedDocument = barcodeItem.extractedDocument

            parsedDocument?.let { document ->
                when (document.type.name) {
                    AAMVA.DOCUMENT_TYPE -> {
                        // Handle AAMVA document
                        val aamva = AAMVA(document)
                        val titleData = aamva.titleData
                        val vehicleData = aamva.vehicleData
                        val registrationData = aamva.registrationData
                    }

                    BoardingPass.DOCUMENT_TYPE -> {
                        // Handle Boarding Pass document
                        val boardingPass = BoardingPass(document)
                        val passengerName = boardingPass.passengerName
                        val legs = boardingPass.legs
                    }

                    MedicalCertificate.DOCUMENT_TYPE -> {
                        // Handle Medical Certificate document
                        val medicalCertificate = MedicalCertificate(document)
                        val firstName = medicalCertificate.firstName
                    }

                    DEMedicalPlan.DOCUMENT_TYPE -> {
                        // Handle DeMedical Plan document
                        val deMedicalPlan = DEMedicalPlan(document)
                        val doctor = deMedicalPlan.doctor
                        val patient = deMedicalPlan.patient
                    }

                    IDCardPDF417.DOCUMENT_TYPE -> {
                        // Handle ID Card PDF 417 document
                        val idCardPdf417 = IDCardPDF417(document)
                        val firstName = idCardPdf417.firstName
                        val lastName = idCardPdf417.lastName
                    }

                    GS1.DOCUMENT_TYPE -> {
                        // Handle GS1 document
                        val gs1 = GS1(document)
                        val elements = gs1.elements
                    }

                    SEPA.DOCUMENT_TYPE -> {
                        // Handle SEPA document
                        val sepa = SEPA(document)
                        val receiverName = sepa.receiverName
                        val amount = sepa.amount
                    }

                    SwissQR.DOCUMENT_TYPE -> {
                        // Handle Swiss QR document
                        val swissQr = SwissQR(document)
                        val payeeName = swissQr.payeeName
                        val amount = swissQr.amount
                    }

                    VCard.DOCUMENT_TYPE -> {
                        // Handle vCard document
                        val vCard = VCard(document)
                        val name = vCard.name
                        val emails = vCard.emails
                    }

                    HIBC.DOCUMENT_TYPE -> {
                        // Handle HIBC document
                        val hibc = HIBC(document)
                        val lotNumber = hibc.lotNumber
                        val serialNumber = hibc.serialNumber
                    }

                    BritishColumbiaDriverLicense.DOCUMENT_TYPE -> {
                        // Handle BritishColumbiaDriverLicense document
                        val license = BritishColumbiaDriverLicense(document)
                        val address = license.address
                        val expirationDate = license.cardExpiry
                    }
                }
            }
        }
        // @EndTag("Handle parsed document result")
    }

    fun handleBarcodeImageResult(imageRef: ImageRef) {
        // @Tag("Handle barcode image result")
        val configuration = BarcodeScannerConfiguration()
        val result = ScanbotSDK.barcode.scanFromImage(
            image = imageRef,
            configuration = configuration
        )

        result.getOrNull()?.barcodes?.forEach { barcodeItem ->
            // Handle the detected barcode(s) from result
            val barcodeText = barcodeItem.text
            val barcodeFormat = barcodeItem.format

            // The barcodeItem contains the scanned barcode data as ByteArray
            val barcodeRawData = barcodeItem.rawBytes

            // This is the image of the barcode that was scanned (SDK's internal representation)
            val barcodeImage = barcodeItem.sourceImage
        }
        // @EndTag("Handle barcode image result")
    }
}