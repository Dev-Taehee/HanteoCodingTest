package com.problem1.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CategoryDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Post {
        @NotEmpty(message = "카테고리 이름은 반드시 값이 존재해야합니다.")
        private String categoryName;
    }

    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response {
        private Long categoryId;
        private String categoryName;
        private List<CategoryDto.Response> subcategories;
    }

}
