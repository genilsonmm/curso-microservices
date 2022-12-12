package com.gm2.dev.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document(collection = "transfer")
public class Transfer {

    @Id
    private String id;
    @NonNull
    private String accountFrom;
    @NonNull
    private String accountTo;
    @NonNull
    private LocalDateTime dateTime;
    @NonNull
    private BigDecimal value;
}
