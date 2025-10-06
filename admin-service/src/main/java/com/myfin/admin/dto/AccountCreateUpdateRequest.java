package com.myfin.admin.dto;

import com.myfin.admin.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountCreateUpdateRequest {
    @NotNull private Long customerId;
    @NotBlank private String accountNumber;
    @NotNull private Account.AccountType type;
    @NotNull private BigDecimal balance;
}