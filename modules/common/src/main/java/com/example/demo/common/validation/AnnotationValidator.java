package com.example.demo.common.validation;

import com.example.demo.common.error.ErrorCode;
import com.example.demo.common.exception.PlatformException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnnotationValidator<T> {

    private static final String VALIDATION_MESSAGE_FORMAT = "%s: %s";
    private final Validator validator;

    public void validate(T target) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(target);
        if (!constraintViolations.isEmpty()) {
            String errorsStr = constraintViolations.stream().map(this::buildMessage).collect(Collectors.joining(", "));
            throw new PlatformException(ErrorCode.ANNOTATION_VALIDATION_ERROR, errorsStr, null);
        }
    }

    private String buildMessage(ConstraintViolation<T> constraintViolation) {
        String path = buildPath(constraintViolation.getPropertyPath());

        if (StringUtils.isEmpty(path)) {
            return constraintViolation.getMessage();
        }
        return String.format(VALIDATION_MESSAGE_FORMAT, path, constraintViolation.getMessage());
    }

    private String buildPath(Path path) {
        if (path == null) {
            return null;
        }

        return path.toString();
    }

}
