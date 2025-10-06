package com.myfin.customerservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DepositWithdrawRequest {
    @NotBlank
    private String accountNumber;

    @NotNull
    private BigDecimal amount;
}

