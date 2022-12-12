package com.gm2.dev.dto;

import lombok.Data;

@Data
public class TransferResponseDto extends TransactionDto {
    private String accountFrom;
    private String accountTo;
}
