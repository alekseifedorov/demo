package com.example.demo.api.composite.book.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<String> handleError(ValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}

