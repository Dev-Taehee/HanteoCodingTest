package com.problem1.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRelationshipRepository extends JpaRepository<CategoryRelationship, Long> {
    @Query(value = "SELECT * FROM CATEGORY_RELATIONSHIP WHERE PARENT_IDX IN (SELECT CHILD_ID FROM CATEGORY_RELATIONSHIP WHERE PARENT_IDX = :categoryId) OR PARENT_IDX = :categoryId", nativeQuery = true)
    List<ChildParentId> findCategoryRelationships(Long categoryId);
}
