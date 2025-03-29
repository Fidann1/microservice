package com.example.ms_security.exception;

import com.example.ms_security.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleForbiddenException(ForbiddenException ex){
       return ErrorResponse.builder().message(ex.getMessage()).build();
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserAlreadyExistException(UserAlreadyExistException ex){
        return ErrorResponse.builder()
                .message(ex.getMessage()).build();
    }

    @ExceptionHandler(CustomFeignException.class)
    @ResponseStatus()
    public ResponseEntity<ErrorResponse> handleCustomFeignException(CustomFeignException ex){
        return ResponseEntity
                .status(ex.getErrorResponse().getCode())
                .body(ex.getErrorResponse());}

}
