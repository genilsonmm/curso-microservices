package com.gm2.dev.controller;

import com.gm2.dev.dto.TransactionRequestDto;
import com.gm2.dev.exceptions.InvalidUserAccountException;
import com.gm2.dev.service.AccountValidationService;
import com.gm2.dev.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends BaseController {

    private final TransactionService service;
    private final AccountValidationService accountValidationService;

    public TransactionController(TransactionService service, AccountValidationService accountValidationService) {
        this.service = service;
        this.accountValidationService = accountValidationService;
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody TransactionRequestDto transactionRequestDto){
        try {
            accountValidationService.verifyAccount(transactionRequestDto.getAccountNumber());
            String message = transactionRequestDto.getOperation() + " registrado com sucesso!";
            return getResponseSuccess(service.save(transactionRequestDto), message, HttpStatus.CREATED);
        }catch(InvalidUserAccountException error){
            return getResponseError(error.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception error){
            return getResponseError(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity statement(@PathVariable String accountNumber){
        try{
            accountValidationService.verifyAccount(accountNumber);
            return getResponseSuccess(service.getAccountStatement(accountNumber), HttpStatus.OK);
        }catch(InvalidUserAccountException error){
            return getResponseError(error.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception error){
            return getResponseError(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
