package com.example.department_service.exception;

import com.example.department_service.dto.ErrorResponse;

public class ResourceNotFoundException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public ResourceNotFoundException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;

    }
}
