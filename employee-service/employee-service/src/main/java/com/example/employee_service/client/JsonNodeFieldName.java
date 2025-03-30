package com.example.employee_service.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JsonNodeFieldName {
    MESSAGE("message"),
    ERRORS("errors");

    private final String value;
}
