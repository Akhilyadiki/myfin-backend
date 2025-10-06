package com.myfin.customerservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmiRequest {
    @NotNull @Min(1)
    private BigDecimal principal;

    @Min(0)
    private double annualInterestRate;

    @Min(1)
    private int tenureMonths;
}
