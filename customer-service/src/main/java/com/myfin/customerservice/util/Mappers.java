package com.myfin.customerservice.util;

import com.myfin.customerservice.dto.*;
import com.myfin.customerservice.entity.*;

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
                .build();
    }

    public static TransactionDto toDto(Transaction t){
        return TransactionDto.builder()
                .transactionId(t.getTransactionId())
                .type(t.getType())
                .amount(t.getAmount())
                .createdAt(t.getCreatedAt())
                .counterpartyAccountNumber(t.getCounterpartyAccountNumber())
                .category(t.getCategory())
                .build();
    }
}