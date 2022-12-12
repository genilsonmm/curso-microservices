package com.gm2.dev.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountStatementDto {

    private String accountNumber;
    private List<TransactionDto> transactions;
    private BigDecimal balance;

    public void calculateBalance(){
        this.balance = BigDecimal.ZERO;
        for(TransactionDto transaction : transactions){
            this.balance = balance.add(transaction.getValue());
        }
    }
}
