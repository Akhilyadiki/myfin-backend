package com.myfin.admin.dto;

import com.myfin.admin.entity.Account;
import lombok.*;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountDto {
    private Long id;
    private String accountNumber;
    private Account.AccountType type;
    private BigDecimal balance;
    private Long customerId;
}