package com.example.demo.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@SuppressWarnings("ConstantNamingConvention")
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(10000, "error.internalServerError"),
    ANNOTATION_VALIDATION_ERROR(10050, "error.annotationValidationError"),
    VALIDATION_AUTHOR_NAME_NOT_VALID(10100, "Author name [%s] not valid");

    private final int code;
    private final String message;
}
