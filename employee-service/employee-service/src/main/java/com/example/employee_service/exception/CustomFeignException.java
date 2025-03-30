package com.example.employee_service.exception;

import com.example.employee_service.dto.ErrorResponse;
import lombok.Getter;

@Getter
public class CustomFeignException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public CustomFeignException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }
}
