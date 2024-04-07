package com.example.demo.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SecurePdfWithBytes {
    public static void main(String[] args) {
        try {
            // Secure the PDF
            byte[] securedPdf = securePdf();

            // Save the secured PDF (you can also return it as needed)
            // For demonstration, we'll just print the length of the secured PDF
            System.out.println("Secured PDF Length: " + securedPdf.length);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    // Method to create and secure a sample PDF as a byte array
    public static byte[] securePdf() throws DocumentException, IOException {
        // Create a new Document
        Document document = new Document();

        // Create a ByteArrayOutputStream to hold the PDF data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create a PdfWriter instance to write to the ByteArrayOutputStream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        // Open the document for writing
        document.open();

        // Add content to the document
        document.add(new Paragraph("This is a sample PDF created with iText."));

        // Close the document
        document.close();

        // Secure the PDF
        PdfReader reader = new PdfReader(outputStream.toByteArray());
        ByteArrayOutputStream securedOutputStream = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, securedOutputStream);
        stamper.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);
        stamper.close();

        return securedOutputStream.toByteArray();
    }
}
