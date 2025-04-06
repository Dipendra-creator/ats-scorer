package com.student.ats_scorer.controllers;

import com.student.ats_scorer.processors.PdfProcessor;
import com.student.ats_scorer.services.ATSScorer;
import com.student.ats_scorer.services.ATSScorerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ats-score")
public class ATSScoreController {

  private final ATSScorerFactory scorerFactory;

  @Autowired
  public ATSScoreController(ATSScorerFactory scorerFactory) {
    this.scorerFactory = scorerFactory;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> scorePdf(@RequestParam("pdf") MultipartFile pdfFile) {
    // Check if the file is provided and not empty
    if (pdfFile == null || pdfFile.isEmpty()) {
      return ResponseEntity.badRequest()
          .body(Map.of("error", "PDF file not provided or empty"));
    }

    try {
      // Extract text from the uploaded PDF
      InputStream inputStream = pdfFile.getInputStream();
      PdfProcessor processor = new PdfProcessor(inputStream);
      String text = processor.extractText();

      // Calculate the ATS score using the extracted text
      ATSScorer scorer = scorerFactory.createScorer(text);
      Map<String, Object> score = scorer.calculateScore();

      // Return the score as JSON
      return ResponseEntity.ok(Map.of("ats_score", score));
    } catch (Exception e) {
      // If any error occurs, return a 500 error with the error message
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Map.of("error", e.getMessage()));
    }
  }
}
