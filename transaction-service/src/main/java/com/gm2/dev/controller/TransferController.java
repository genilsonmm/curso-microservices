package com.gm2.dev.controller;

import com.gm2.dev.dto.AccountStatementDto;
import com.gm2.dev.dto.TransferRequestDto;
import com.gm2.dev.service.AccountValidationService;
import com.gm2.dev.service.BaseService;
import com.gm2.dev.service.TransactionService;
import com.gm2.dev.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController extends BaseController {

    private final TransferService transferService;
    private final TransactionService transactionService;
    private final AccountValidationService accountValidationService;

    public TransferController(
            TransferService transferService,
            TransactionService transactionService,
            AccountValidationService accountValidationService){

        this.transferService = transferService;
        this.transactionService = transactionService;
        this.accountValidationService = accountValidationService;
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody TransferRequestDto transferRequestDto) {
        try {
            accountValidationService
                    .accountsAreValid(transferRequestDto.getAccountFrom(), transferRequestDto.getAccountTo());
            return getResponseEntity(transferRequestDto);
        } catch (Exception error) {
            return getResponseError(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity getAllByAccountNumber(@PathVariable String accountNumber){

        var transferHistory = transferService.getAllByAccountNumber(accountNumber);

        if(transferHistory.size() > 0){
            return getResponseSuccess(transferHistory, HttpStatus.OK);
        }

        return getResponseError("A conta informada não possui transferências.", HttpStatus.OK);
    }

    private ResponseEntity getResponseEntity(TransferRequestDto transferRequestDto){
        AccountStatementDto accountStatementDto = transactionService
                .getAccountStatement(transferRequestDto.getAccountFrom());

        int currentBalance = accountStatementDto.getBalance().compareTo(transferRequestDto.getValue());

        if(currentBalance == 1 || currentBalance == 0){
            var response = transferService.save(transferRequestDto);
            return getResponseSuccess(response, HttpStatus.CREATED);
        } else {
            return getResponseError("Saldo insuficiente!", HttpStatus.OK);
        }
    }
}
