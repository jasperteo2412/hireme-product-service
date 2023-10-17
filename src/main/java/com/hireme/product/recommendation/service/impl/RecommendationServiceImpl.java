package com.hireme.product.recommendation.service.impl;

import com.hireme.product.assignment.dto.AssignmentDTO;
import com.hireme.product.assignment.service.AssignmentService;
import com.hireme.product.recommendation.mapper.RecommendationMapper;
import com.hireme.product.recommendation.repository.RecommendationRepository;
import com.hireme.product.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;
    private final AssignmentService assignmentService;


    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository,
                                     RecommendationMapper recommendationMapper,
                                     AssignmentService assignmentService) {
        this.recommendationRepository = recommendationRepository;
        this.recommendationMapper = recommendationMapper;
        this.assignmentService = assignmentService;
    }
///////////////////////////////////
//    @Override
//    public List<AssignmentDTO> getRecommendationsByAssignmentId(Long assignmentId) {
//        // Retrieve the target assignment based on the provided assignmentId
//        AssignmentDTO targetAssignment = assignmentService.getAssignmentById(assignmentId);
//
//        if (targetAssignment == null) {
//            return new ArrayList<>(); // Handle the case where the target assignment is not found
//        }
//        // Extract subjects from the target assignment's title
//        List<String> targetSubjects = extractSubjectsFromTitle(targetAssignment.getTitle());
//
//        // Retrieve all assignments from the database (you may want to limit this to relevant assignments)
//        List<AssignmentDTO> allAssignments = assignmentService.getAllAssignments();
//
//        // Implement content-based filtering based on subject similarity
//        List<AssignmentDTO> recommendations = new ArrayList<>();
//
//        for (AssignmentDTO assignment : allAssignments) {
////            if (assignment.getAssignmentId() != assignmentId) { // Exclude the target assignment
//            if (!Objects.equals(assignment.getAssignmentId(), assignmentId)) {
//                List<String> assignmentSubjects = extractSubjectsFromTitle(assignment.getTitle());
//
//                // Calculate subject similarity using Jaccard similarity (for example)
//                double jaccardSimilarity = calculateJaccardSimilarity(targetSubjects, assignmentSubjects);
//
//                // You can adjust the threshold for similarity based on your requirements
//                if (jaccardSimilarity >= 0.5) {
//                    recommendations.add(assignment);
//                }
//            }
//        }
//        return recommendations;
//    }
//    // Helper method to extract subjects from an assignment title using Apache OpenNLP
//    private List<String> extractSubjectsFromTitle(String title) {
//        List<String> subjects = new ArrayList<>();
//
//        // Implement subject extraction using OpenNLP or any other NLP library
//        // assume the title contains subjects separated by commas
//        String[] parts = title.split(",");
//
//        for (String part : parts) {
//            subjects.add(part.trim()); // Trim to remove any leading/trailing spaces
//        }
//        return subjects;
//    }
//
//    // Helper method to calculate Jaccard similarity between two sets of subjects
//    private double calculateJaccardSimilarity(List<String> set1, List<String> set2) {
//        // Implement Jaccard similarity calculation
//        Set<String> union = new HashSet<>(set1);
//        union.addAll(set2);
//        Set<String> intersection = new HashSet<>(set1);
//        intersection.retainAll(set2);
//
//        return (double) intersection.size() / union.size();
//    }
    //////////////////////

        @Override
        public List<AssignmentDTO> getRecommendationsByAssignmentId(Long assignmentId) {
            AssignmentDTO targetAssignment = assignmentService.getAssignmentById(assignmentId);

            if (targetAssignment == null) {
                return new ArrayList<>();
            }

            List<String> targetKeywords = extractKeywordsFromTitle(targetAssignment.getTitle());
            List<AssignmentDTO> allAssignments = assignmentService.getAllAssignments();
            List<AssignmentDTO> recommendations = new ArrayList<>();

            for (AssignmentDTO assignment : allAssignments) {
                if (!Objects.equals(assignment.getAssignmentId(), assignmentId)) {
                    List<String> assignmentKeywords = extractKeywordsFromTitle(assignment.getTitle());

                    // Check if there are common keywords between the target assignment and the current assignment
                    if (haveCommonKeywords(targetKeywords, assignmentKeywords)) {
                        recommendations.add(assignment);
                    }
                }
            }
            return recommendations;
        }

        // Modify this method to extract keywords from the assignment title
        private List<String> extractKeywordsFromTitle(String title) {
            List<String> keywords = new ArrayList<>();

            // This is a simplified example; you might use a more advanced NLP library for keyword extraction
            // Here, we split the title into words and add them as keywords
            String[] words = title.split(" ");
            keywords.addAll(Arrays.asList(words));

            return keywords;
        }

        private boolean haveCommonKeywords(List<String> keywords1, List<String> keywords2) {
            // Implement logic to check for common keywords (e.g., using set intersection)
            Set<String> set1 = new HashSet<>(keywords1);
            Set<String> set2 = new HashSet<>(keywords2);

            // Calculate the intersection of sets
            set1.retainAll(set2);

            // Adjust the threshold based on your requirements
            return set1.size() > 0;
        }
}



