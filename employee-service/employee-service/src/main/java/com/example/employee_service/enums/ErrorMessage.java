package com.example.employee_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    CLIENT_ERROR("Client error happened");

    private final String message;
}
