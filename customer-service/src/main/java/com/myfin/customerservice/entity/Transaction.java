package com.myfin.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {

    public enum TransactionType {
        DEPOSIT, WITHDRAW, TRANSFER, INVESTMENT
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TransactionType type;

    @Column(nullable=false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable=false)
    private OffsetDateTime createdAt;

    // For transfer: the counterparty account (optional)
    private String counterpartyAccountNumber;

    // For investment: LOAN/RD/FD category name
    private String category; // LOAN, RD, FD

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable=false)
    private Account account;
}