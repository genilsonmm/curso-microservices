package com.gm2.dev.controller;

import com.gm2.dev.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    @Autowired
    private Environment environment;

    private String getPort() { return environment.getProperty("local.server.port"); }

    protected ResponseEntity getResponseError(String message, HttpStatus status){
        return new ResponseEntity(ResponseDto.builder()
                .port(getPort())
                .message(message)
                .build(), status);
    }

    protected ResponseEntity getResponseSuccess(Object data, String message, HttpStatus status){
        return new ResponseEntity(ResponseDto.builder()
                .data(data)
                .port(getPort())
                .message(message)
                .build(), status);
    }

    protected ResponseEntity getResponseSuccess(Object data, HttpStatus status){
        return new ResponseEntity(ResponseDto.builder()
                .data(data)
                .port(getPort())
                .build(), status);
    }
}
