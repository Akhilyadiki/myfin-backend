package com.myfin.customerservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LoanApplicationRequest {
    @NotNull @Min(1)
    private BigDecimal principal;

    @Min(0)
    private double annualInterestRate;

    @Min(1)
    private int tenureMonths;
}


