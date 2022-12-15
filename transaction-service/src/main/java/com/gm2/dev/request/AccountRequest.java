package com.gm2.dev.request;

import com.gm2.dev.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service") //, url = "localhost:8000")
public interface AccountRequest {

    @GetMapping("/customer-account/{account}")
    ResponseDto getUserAccount(@PathVariable String account);
}
