package com.problem1.exception;

import lombok.Getter;

public enum ExceptionCode {

    CATEGORY_NOT_FOUND(404, "Category not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }

}
