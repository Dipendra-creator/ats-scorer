package com.student.ats_scorer.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Benchmarks {
    private Map<String, Object> benchmarks;
    private Map<String, Integer> weightMapping;

    public Benchmarks() {
        benchmarks = new HashMap<>();
        weightMapping = new HashMap<>();
        setDefaultBenchmarks();
        setDefaultWeightMapping();
    }

    private void setDefaultBenchmarks() {
        benchmarks.put("Programming Languages", 300);
        benchmarks.put("Web Development", Map.of(
            "Frontend", 8,
            "Backend", 5,
            "Full Stack & Misc", 5
        ));
        benchmarks.put("Software Engineering & Methodologies", Map.of(
            "Design & Architecture", 5,
            "Development Processes", 5,
            "Tools & Collaboration", 5
        ));
        benchmarks.put("Data Science & Analytics", Map.of(
            "Programming & Scripting", 100,
            "Libraries & Frameworks", 5,
            "Tools & Platforms", 5,
            "Big Data", 5
        ));
        benchmarks.put("Database Technologies", Map.of(
            "Relational Databases", 3,
            "NoSQL Databases", 3,
            "Data Warehousing", 3,
            "ORM & Data Access", 3
        ));
        benchmarks.put("Cloud Computing & DevOps", Map.of(
            "Cloud Providers", 2,
            "Containers & Orchestration", 2,
            "Infrastructure & Automation", 2,
            "Serverless & Functions", 2
        ));
        benchmarks.put("Cybersecurity", Map.of(
            "Fundamentals", 2,
            "Testing & Assessment", 2,
            "Tools & Technologies", 2,
            "Standards & Compliance", 2
        ));
        benchmarks.put("Mobile Development", Map.of(
            "Platforms", 2,
            "Languages", 5,
            "Frameworks", 2
        ));
        benchmarks.put("Operating Systems & Networking", Map.of(
            "Operating Systems", 3,
            "Networking Fundamentals", 3,
            "Protocols & Tools", 5
        ));
        benchmarks.put("Emerging Technologies", Map.of(
            "Blockchain & Cryptocurrency", 2,
            "Internet of Things (IoT)", 2,
            "Quantum Computing", 2,
            "VR, AR & XR", 2,
            "5G & Edge", 2
        ));
        benchmarks.put("Machine Learning & AI", Map.of(
            "Frameworks", 5,
            "Techniques & Algorithms", 5,
            "Applications & Use Cases", 5
        ));
    }

    private void setDefaultWeightMapping() {
        weightMapping.put("Programming Languages", 65);
        weightMapping.put("Web Development", 30);
        weightMapping.put("Software Engineering & Methodologies", 40);
        weightMapping.put("Database Technologies", 10);
        weightMapping.put("Data Science & Analytics", 30);
        weightMapping.put("Cloud Computing & DevOps", 5);
        weightMapping.put("Cybersecurity", 0);
        weightMapping.put("Mobile Development", 10);
        weightMapping.put("Operating Systems & Networking", 15);
        weightMapping.put("Emerging Technologies", 10);
        weightMapping.put("Machine Learning & AI", 30);
    }

    public Map<String, Object> getBenchmarks() {
        return benchmarks;
    }

    public Map<String, Integer> getWeightMapping() {
        return weightMapping;
    }
}
