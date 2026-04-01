package io.scanbot.barcode.scanner.sdk.example.kmp.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.kmp.genericdocument.GenericDocument
import io.scanbot.sdk.kmp.genericdocument.TextFieldWrapper
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

@Composable
fun GenericDocumentView(
    genericDocument: GenericDocument
) {
    val wrappedField = remember(genericDocument) {
        getGenericFieldValue(genericDocument)
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = """
                Document: ${genericDocument.type.name}
                Field: ${wrappedField?.type?.name ?: "N/A"}
                Value: ${wrappedField?.value?.text ?: "N/A"}
            """.trimIndent(), color = Color.Black
        )
    }
}

fun getGenericFieldValue(
    genericDocument: GenericDocument
): TextFieldWrapper? {
    return when (genericDocument.type.name) {

        BoardingPass.DOCUMENT_TYPE -> BoardingPass(genericDocument).electronicTicketIndicator

        SwissQR.DOCUMENT_TYPE -> SwissQR(genericDocument).iban

        DEMedicalPlan.DOCUMENT_TYPE -> DEMedicalPlan(genericDocument).doctor.issuerName

        IDCardPDF417.DOCUMENT_TYPE -> IDCardPDF417(genericDocument).dateExpired

        GS1.DOCUMENT_TYPE -> GS1(genericDocument).elements.firstOrNull()?.applicationIdentifier

        SEPA.DOCUMENT_TYPE -> SEPA(genericDocument).receiverIBAN

        MedicalCertificate.DOCUMENT_TYPE -> MedicalCertificate(genericDocument).doctorNumber

        VCard.DOCUMENT_TYPE -> VCard(genericDocument).formattedName?.rawValue

        AAMVA.DOCUMENT_TYPE -> AAMVA(genericDocument).issuerIdentificationNumber

        HIBC.DOCUMENT_TYPE -> HIBC(genericDocument).labelerIdentificationCode

        BritishColumbiaDriverLicense.DOCUMENT_TYPE -> BritishColumbiaDriverLicense(genericDocument).address

        else -> null
    }
}