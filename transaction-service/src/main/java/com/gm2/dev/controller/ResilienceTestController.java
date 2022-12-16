package com.gm2.dev.controller;

import com.gm2.dev.dto.ResponseDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("resilience")
public class ResilienceTestController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ResilienceTestController.class);


    @GetMapping
    @Retry(name = "account", fallbackMethod = "fallbackMethod")
    public ResponseEntity<ResponseEntity<ResponseDto>> ResponseEntityget(){
        logger.info("Request recebido!");
        var response =
                new RestTemplate().getForEntity("http://localhost:8000/customer-accountooooo",
                        ResponseDto.class);

        return ResponseEntity.ok(response);
    }

    private ResponseEntity fallbackMethod(Exception error){
        return getResponseError("Serviço indisponível", HttpStatus.OK);
    }
}
