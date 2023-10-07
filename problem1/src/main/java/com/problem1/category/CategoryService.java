package com.problem1.category;

import com.problem1.exception.BusinessLogicException;
import com.problem1.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRelationshipRepository categoryRelationshipRepository;

    public Category createCategory(Category category) {
        // 카테고리 이름이 중복될 때, 부모 카테고리도 중복되는지 확인해야 함
        // 익명게시판의 경우 저장하지 않고 익명게시판의 카테고리 객체를 반환해야한다.
        // -> 확장성을 고려하면 익명게시판과 같이 동일한 카테고리가 여러 부모를 가지는 경우를 해당하는 Map을 생성해두고 확인하는 것이 좋을 것 같다.
        return categoryRepository.save(category);
    }

    public CategoryRelationship createCategoryRelationship(CategoryRelationship categoryRelationship) {
        return categoryRelationshipRepository.save(categoryRelationship);
    }

    public Category findCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
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
