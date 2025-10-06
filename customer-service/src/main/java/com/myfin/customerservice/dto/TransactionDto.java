package com.myfin.customerservice.dto;


import com.myfin.customerservice.entity.Transaction;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransactionDto {
    private String transactionId;
    private Transaction.TransactionType type;
    private BigDecimal amount;
    private OffsetDateTime createdAt;
    private String counterpartyAccountNumber;
    private String category;
}