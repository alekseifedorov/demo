package com.example.demo.api.composite.book.exception;

import com.example.demo.common.exception.PlatformException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {

    @ExceptionHandler(PlatformException.class)
    @ResponseBody
    public ResponseEntity<String> handleError(PlatformException e) {
        return new ResponseEntity<>(Optional.ofNullable(e.getMessage()).orElse(e.getErrorCode().getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}

