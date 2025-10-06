package com.myfin.admin.util;

import com.myfin.admin.dto.AccountDto;
import com.myfin.admin.dto.CustomerDto;
import com.myfin.admin.entity.Account;
import com.myfin.admin.entity.Customer;

public class Mappers {

    public static CustomerDto toDto(Customer c){
        return CustomerDto.builder()
                .id(c.getId())
                .username(c.getUsername())
                .email(c.getEmail())
                .active(c.isActive())
                .build();
    }

    public static AccountDto toDto(Account a){
        return AccountDto.builder()
                .id(a.getId())
                .accountNumber(a.getAccountNumber())
                .type(a.getType())
                .balance(a.getBalance())
                .customerId(a.getCustomer() != null ? a.getCustomer().getId() : null)
                .build();
    }
}