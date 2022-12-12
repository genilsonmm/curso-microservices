package com.gm2.dev.controller;

import com.gm2.dev.dto.CustomerDto;
import com.gm2.dev.exceptions.InvalidAccountException;
import com.gm2.dev.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-account")
public class CustomerAccountController extends BaseController {

    private final CustomerService service;

    public CustomerAccountController(CustomerService service) { this.service = service; }

    @PostMapping()
    public ResponseEntity post(@RequestBody CustomerDto customerDto){
        try {
            CustomerDto newCustomer = service.save(customerDto);
            return getResponseSuccess(newCustomer, "Conta registrada com sucesso!", HttpStatus.CREATED);
        } catch (Exception error){
            return getResponseError(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getAll(){
        return getResponseSuccess(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{account}")
    public ResponseEntity isValid(@PathVariable String account){
        try {
            return getResponseSuccess(service.findByAccountNumber(account), HttpStatus.OK);
        } catch (InvalidAccountException error){
            return getResponseError(error.getMessage(), HttpStatus.OK);
        }catch (Exception error){
            return getResponseError(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}