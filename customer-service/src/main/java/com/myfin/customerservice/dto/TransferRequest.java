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
public class TransferRequest {
    @NotBlank private String fromAccountNumber;
    @NotBlank private String toAccountNumber;
    @NotNull private BigDecimal amount;
}
