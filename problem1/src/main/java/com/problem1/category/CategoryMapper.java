package com.problem1.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryPostDtoToCategory(CategoryDto.Post post);
    CategoryRelationship makeCategoryRelationship(Category parent, Category child);
    default HashMap<Long, List<Long>> categoryRelationshipsToHashMap(List<ChildParentId> categoryRelationships) {
        // 최하단의 카테고리에 대해 응답하는 경우에 대해 고민해봐야함 -> categoryRelationships가 null일 것이다.
        HashMap<Long, List<Long>> hashMap = new HashMap<>();

        for(ChildParentId childParentId : categoryRelationships) {
            if(hashMap.containsKey(childParentId.getParent_Idx())) {
                hashMap.get(childParentId.getParent_Idx()).add(childParentId.getChild_Id());
            } else {
                ArrayList<Long> list = new ArrayList<>();
                list.add(childParentId.getChild_Id());
                hashMap.put(childParentId.getParent_Idx(), list);
            }
        }

        return hashMap;
    }

    default HashMap<Long, String> categoriesToHashMap(List<Category> categories) {
        HashMap<Long, String> hashMap = new HashMap<>();

        for(Category category : categories) {
            hashMap.put(category.getCategoryId(), category.getCategoryName());
        }

        return hashMap;
    }

    default CategoryDto.Response mapCategoriesByCategoryId(Long categoryId, HashMap<Long, List<Long>> hashMap, HashMap<Long, String> categories,CategoryDto.Response response) {
        response = response.builder().categoryId(categoryId).categoryName(categories.get(categoryId)).build();
        ArrayList<CategoryDto.Response> list = new ArrayList<>();

        if(hashMap.containsKey(categoryId)) {
            for(Long id : hashMap.get(categoryId)) {
                list.add(mapCategoriesByCategoryId(id, hashMap, categories, new CategoryDto.Response()));
            }
        }

        return response.toBuilder().subcategories(list).build();
    }
}
