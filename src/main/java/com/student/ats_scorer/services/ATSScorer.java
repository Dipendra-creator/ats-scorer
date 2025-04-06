package com.student.ats_scorer.services;

import com.student.ats_scorer.config.Ats;
import com.student.ats_scorer.config.Benchmarks;

import java.util.*;


public class ATSScorer {
  private final String resumeText; // stored in lower-case for case-insensitive matching
  private final Map<String, Object> benchmarks;
  private final Map<String, Integer> weightMapping;
  private final Map<String, Map<String, Map<String, List<String>>>> atsMap;

  public ATSScorer(String resumeText, Ats ats, Benchmarks benchmarksConfig) {
    this.resumeText = resumeText.toLowerCase();
    this.atsMap = ats.getAts();
    this.benchmarks = benchmarksConfig.getBenchmarks();
    this.weightMapping = benchmarksConfig.getWeightMapping();
  }

  // Helper method to count non-overlapping occurrences of a keyword in the resume text.
  private int countOccurrences(String keyword) {
    int count = 0;
    int index = 0;
    // Since both resumeText and keyword are lower-case, simple indexOf works fine.
    while ((index = resumeText.indexOf(keyword.toLowerCase(), index)) != -1) {
      count++;
      index += keyword.length();
    }
    return count;
  }

  // Score a leaf node (which contains a list of keywords) using benchmark normalization.
  // Additionally, build a list of keywords not found to suggest improvements.
  private Map<String, Object> scoreLeaf(List<String> keywords, int benchmark) {
    int totalHits = 0;
    List<String> foundKeywords = new ArrayList<>();
    List<String> notFoundKeywords = new ArrayList<>();

    for (String keyword : keywords) {
      int hits = countOccurrences(keyword);
      totalHits += hits;
      if (hits > 0) {
        foundKeywords.add(keyword);
      } else {
        notFoundKeywords.add(keyword);
      }
    }

    // Calculate how many keywords are "missing" to reach the benchmark.
    // For example, if benchmark is 5 and the resume contains 3 distinct keywords,
    // then we require 2 more suggestions.
    int required = benchmark - foundKeywords.size();
    if (required < 0) {
      required = 0;
    }
    // Limit the suggestions to at most 'required' keywords.
    if (notFoundKeywords.size() > required) {
      notFoundKeywords = notFoundKeywords.subList(0, required);
    }

    // Calculate score: if totalHits/benchmark is more than 100, cap at 100.
    int score = Math.min((totalHits * 100) / benchmark, 100);

    Map<String, Object> result = new HashMap<>();
    result.put("score", score);
    result.put("hits", totalHits);
//    result.put("found_keywords", foundKeywords);
    result.put("suggestion_keywords", notFoundKeywords);
    return result;
  }

  // Recursive function to score a category node.
  // If the node contains a "keywords" list, it is treated as a leaf node.
  private Map<String, Object> scoreCategory(Object categoryData, Object benchmarkData) {
    if (categoryData instanceof Map) {
      Map<String, Object> catMap = (Map<String, Object>) categoryData;
      // Check if this is a leaf node by testing for the "keywords" key.
      if (catMap.containsKey("keywords")) {
        int bench = (benchmarkData instanceof Integer) ? (Integer) benchmarkData : 5;
        List<String> keywords = (List<String>) catMap.get("keywords");
        return scoreLeaf(keywords, bench);
      } else {
        // Nested category: iterate through each subcategory.
        Map<String, Object> subScores = new HashMap<>();
        int totalScore = 0;
        int count = 0;
        for (Map.Entry<String, Object> entry : catMap.entrySet()) {
          String subCategory = entry.getKey();
          Object subData = entry.getValue();
          Object subBenchmark = null;
          if (benchmarkData instanceof Map) {
            // If benchmarkData is a map, try to get a benchmark for this subcategory.
            subBenchmark = ((Map<String, Object>) benchmarkData).getOrDefault(subCategory, 5);
          }
          Map<String, Object> subResult = scoreCategory(subData, subBenchmark);
          int subScore = (int) subResult.get("score");
          subScores.put(subCategory, subResult);
          totalScore += subScore;
          count++;
        }
        int avgScore = count > 0 ? totalScore / count : 0;
        Map<String, Object> result = new HashMap<>();
        result.put("score", avgScore);
        result.put("breakdown", subScores);
        return result;
      }
    }
    // In case categoryData is not a Map (shouldn't happen), return a default score of 0.
    Map<String, Object> defaultResult = new HashMap<>();
    defaultResult.put("score", 0);
    return defaultResult;
  }

  // Calculates the overall ATS score along with a detailed breakdown per category.
  public Map<String, Object> calculateScore() {
    Map<String, Object> categoryBreakdown = new HashMap<>();
    int weightedSum = 0;
    int totalWeight = 0;

    // Process each top-level category from the ATS data.
    for (Map.Entry<String, Map<String, Map<String, List<String>>>> entry : atsMap.entrySet()) {
      String category = entry.getKey();
      Object data = entry.getValue();
      Object benchmark = benchmarks.getOrDefault(category, null);
      Map<String, Object> categoryResult = scoreCategory(data, benchmark);
      categoryBreakdown.put(category, categoryResult);

      int weight = weightMapping.getOrDefault(category, 0);
      int catScore = (int) categoryResult.get("score");
      if (weight > 0) {
        weightedSum += catScore * weight;
        totalWeight += weight;
      }
    }
    int overallScore = totalWeight > 0 ? weightedSum / totalWeight : 0;
    Map<String, Object> result = new HashMap<>();
    result.put("overall_score", overallScore);
    result.put("category_scores", categoryBreakdown);
    return result;
  }
}
