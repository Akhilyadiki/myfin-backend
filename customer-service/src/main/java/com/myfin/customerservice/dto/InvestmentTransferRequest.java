package com.myfin.customerservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InvestmentTransferRequest {
    @NotBlank private String fromAccountNumber;
    @NotNull private BigDecimal amount;
    @NotBlank private String category; // LOAN, RD, FD
}