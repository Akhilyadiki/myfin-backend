package com.myfin.customerservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "loan_applications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LoanApplication {

    public enum LoanStatus { PENDING, APPROVED, DENIED }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, precision = 19, scale = 2)
    private BigDecimal principal;

    @Column(nullable=false)
    private double annualInterestRate;

    @Column(nullable=false)
    private int tenureMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private LoanStatus status;

    @Column(nullable=false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;
}