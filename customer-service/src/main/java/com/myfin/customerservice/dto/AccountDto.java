package com.myfin.customerservice.dto;


import com.myfin.customerservice.entity.Account;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountDto {
    private Long id;
    private String accountNumber;
    private Account.AccountType type;
    private BigDecimal balance;
}