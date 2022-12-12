package com.gm2.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto{

    private String accountFrom;
    private String accountTo;
    private LocalDateTime dateTime;
    private BigDecimal value;
}
