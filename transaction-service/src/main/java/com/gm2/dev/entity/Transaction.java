package com.gm2.dev.entity;

import com.gm2.dev.util.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Document(collection = "transaction")
public class Transaction {

    @Id
    private String id;
    @Field("account_number")
    private String accountNumber;
    private LocalDateTime date;
    private Operation operation;
    private BigDecimal value;
}
