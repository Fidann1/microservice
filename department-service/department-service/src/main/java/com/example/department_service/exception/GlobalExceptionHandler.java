package com.example.department_service.exception;

import com.example.department_service.dto.ErrorResponse;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.SC_NOT_FOUND)
                .build();
    }
}
