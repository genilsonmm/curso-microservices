package com.gm2.dev.dto;

import com.gm2.dev.util.Operation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDto {

    private String accountNumber;
    private Operation operation;
    private BigDecimal value;
}
