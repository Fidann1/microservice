package com.example.employee_service.client;

import com.example.employee_service.dto.ErrorResponse;
import com.example.employee_service.enums.MapperUtil;
import com.example.employee_service.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.ArrayList;

import static com.example.employee_service.client.JsonNodeFieldName.ERRORS;
import static com.example.employee_service.enums.ErrorMessage.CLIENT_ERROR;

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
                .message(errorMessage)
                .errors(errors)
                .build());

    }
}
