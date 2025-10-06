package com.myfin.admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    public enum AccountType { SAVINGS, CURRENT }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private AccountType type;

    @Column(nullable=false, precision = 19, scale = 2)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;
}