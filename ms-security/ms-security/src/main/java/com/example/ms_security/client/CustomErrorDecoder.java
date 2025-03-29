package com.example.ms_security.client;

import com.example.ms_security.dto.ErrorResponse;
import com.example.ms_security.enums.MapperUtil;
import com.example.ms_security.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import static com.example.ms_security.client.JsonNodeFieldName.ERRORS;
import static com.example.ms_security.enums.ErrorMessage.CLIENT_ERROR;


public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String errorMessage= CLIENT_ERROR.getMessage();
        int statusCode = response.status();
        ArrayList<String> errors= new ArrayList<>();

        JsonNode jsonNode;
        try(var body=response.body().asInputStream()){
            jsonNode= MapperUtil.MAPPER_UTIL.map(body,JsonNode.class);
        }
        catch(Exception exception){
            throw new CustomFeignException(ErrorResponse.builder()
                    .code(statusCode)
                    .message(errorMessage)
                    .errors(errors)
                    .build());
        }
        if(jsonNode.has(JsonNodeFieldName.MESSAGE.getValue())){
            errorMessage=jsonNode.get(JsonNodeFieldName.MESSAGE.getValue()).asText();
        }
        if(jsonNode.has(ERRORS.getValue()) && jsonNode.get(ERRORS.getValue()).isArray()){
            jsonNode.get(ERRORS.getValue()).forEach(error -> errors.add(error.asText()));
        }
        return new CustomFeignException(ErrorResponse.builder()
                .code(statusCode)
                .message(errorMessage)
                .errors(errors)
                .build());

    }
}
