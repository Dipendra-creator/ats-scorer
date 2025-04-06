package com.student.ats_scorer.processors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.InputStream;

public class PdfProcessor {

    public InputStream pdfFile;

    public PdfProcessor(InputStream pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String extractText() {
        String extractedText = "";

        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            extractedText = pdfStripper.getText(document);
        } catch (Exception e) {
            e.printStackTrace();
            extractedText = "Error extracting text from PDF file";
        }
        return extractedText;
    }
}
