package com.problem1.category;

import com.problem1.exception.BusinessLogicException;
import com.problem1.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRelationshipRepository categoryRelationshipRepository;

    private HashSet<String> hashSet = new HashSet<>(){{add("익명게시판");}};

    public Category createCategory(Category category) {
        // TO DO: 카테고리 이름이 중복될 때, 부모 카테고리도 중복되는지 확인해야 함
        if(hashSet.contains(category.getCategoryName())) { // 익명게시판의 경우 이미 존재한다면 저장하지 않고 익명게시판의 카테고리 객체를 반환해야한다.
            Optional<Category> optionalCategory = categoryRepository.findByCategoryName(category.getCategoryName());
            if(optionalCategory.isPresent()) {
                return optionalCategory.get();
            }
        }
        return categoryRepository.save(category);
    }

    public CategoryRelationship createCategoryRelationship(CategoryRelationship categoryRelationship) {
        return categoryRelationshipRepository.save(categoryRelationship);
    }

    public Category findCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.orElseThrow(() -> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }

    public Category findCategoryByName(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
        return optionalCategory.orElseThrow(() -> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }

    public List<ChildParentId> getCategoryRelationships(Long categoryId) {
        List<ChildParentId> categoryRelationships = categoryRelationshipRepository.findCategoryRelationships(categoryId);
        return categoryRelationships;
    }

    public List<Category> getCategories(Long categoryId, List<ChildParentId> categoryRelationships) {
        List<Long> ids = new ArrayList<>();
        ids.add(categoryId);

        for(ChildParentId categoryRelationship : categoryRelationships) {
            ids.add(categoryRelationship.getChild_Id());
        }

        List<Category> categories = categoryRepository.findAllById(ids);
        return categories;
    }

}
