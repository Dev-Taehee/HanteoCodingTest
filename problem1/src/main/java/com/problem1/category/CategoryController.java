package com.problem1.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping("/category")
    public ResponseEntity postCategory(@RequestParam(required = false, name = "parent_idx") Long parentId,
                                       @Valid @RequestBody CategoryDto.Post requestBody) {
        Category postCategory = categoryMapper.categoryPostDtoToCategory(requestBody);
        Category childCategory = categoryService.createCategory(postCategory);
        // parentId가 있는 경우랑 없는 경우 나눠서 체크해야한다.
        if(parentId != null) { // 하위 카테고리를 만드는 경우라면
            Category parentCategory = categoryService.findCategory(parentId);
            CategoryRelationship categoryRelationship = categoryMapper.makeCategoryRelationship(parentCategory, childCategory);
            categoryService.createCategoryRelationship(categoryRelationship);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
