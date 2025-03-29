package com.example.ms_security.enums;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public enum MapperUtil {
    MAPPER_UTIL;

    public<T> T map(InputStream source, Class<T> target) throws IllegalAccessException {
        try{
            return new ObjectMapper().readValue(source,target);
        }
        catch(Exception e){
            throw new IllegalAccessException(e.getMessage());
        }
    }
}
