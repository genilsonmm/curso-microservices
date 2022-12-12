package com.gm2.dev.dto;

import com.gm2.dev.util.Operation;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private LocalDateTime dateTime;
    private Operation operation;
    private BigDecimal value;
}
