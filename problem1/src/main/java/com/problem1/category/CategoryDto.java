package com.problem1.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Post {
        @NotEmpty(message = "카테고리 이름은 반드시 값이 존재해야합니다.")
        private String categoryName;
    }

}
