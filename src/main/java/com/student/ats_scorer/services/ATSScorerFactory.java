package com.student.ats_scorer.services;

import com.student.ats_scorer.config.Ats;
import com.student.ats_scorer.config.Benchmarks;
import org.springframework.stereotype.Service;

@Service
public class ATSScorerFactory {
  private final Ats ats;
  private final Benchmarks benchmarks;

  public ATSScorerFactory(Ats ats, Benchmarks benchmarks) {
    this.ats = ats;
    this.benchmarks = benchmarks;
  }

  public ATSScorer createScorer(String resumeText) {
    return new ATSScorer(resumeText, ats, benchmarks);
  }
}
