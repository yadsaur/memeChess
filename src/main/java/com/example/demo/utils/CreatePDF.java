package com.example.demo.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfEncryptor;

public class CreatePDF {
    public static void main(String[] args) {
        // Step 1: Create a new Document
        Document document = new Document();

        try {
            // Step 2: Create a PdfWriter instance to write to a file
            PdfWriter.getInstance(document, new FileOutputStream("sample.pdf"));

            // Step 3: Open the document for writing
            document.open();

            // Step 4: Add content to the document
            document.add(new Paragraph("This is a sample PDF created with iText."));

            // Step 5: Close the document
            document.close();
            
            try {
				setPermissions("sample.pdf", "secure_sample.pdf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            System.out.println("PDF created successfully.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void setPermissions(String sourcePath, String outputPath) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(new FileInputStream(sourcePath));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPath));
        stamper.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
        stamper.close();
    }
}
