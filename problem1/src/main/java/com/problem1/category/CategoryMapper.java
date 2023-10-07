package com.problem1.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryPostDtoToCategory(CategoryDto.Post post);
    CategoryRelationship makeCategoryRelationship(Category parent, Category child);
}
