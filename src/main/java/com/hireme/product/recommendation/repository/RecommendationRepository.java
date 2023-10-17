package com.hireme.product.recommendation.repository;

import com.hireme.product.recommendation.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByRequestId(String requestId);
    List<Recommendation> findByAssignmentId(Long assignmentId);
}
