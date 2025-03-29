package com.example.ms_security.exception;

import com.example.ms_security.dto.ErrorResponse;
import lombok.Getter;

@Getter
public class CustomFeignException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public CustomFeignException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }
}
