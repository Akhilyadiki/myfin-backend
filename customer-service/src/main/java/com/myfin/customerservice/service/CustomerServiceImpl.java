package com.myfin.customerservice.service;


import com.myfin.customerservice.dto.AccountDto;
import com.myfin.customerservice.dto.CustomerDto;
import com.myfin.customerservice.entity.Account;
import com.myfin.customerservice.entity.Customer;
import com.myfin.customerservice.exception.NotFoundException;
import com.myfin.customerservice.repository.AccountRepository;
import com.myfin.customerservice.repository.CustomerRepository;
import com.myfin.customerservice.util.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public CustomerDto getProfile(String username) {
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return Mappers.toDto(c);
    }

    @Override
    public List<AccountDto> getAccounts(String username) {
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return c.getAccounts() == null ? List.of() :
                c.getAccounts().stream().map(Mappers::toDto).toList();
    }

    @Override
    public AccountDto createAccount(String username, AccountDto req) {
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        String accNo = generateAccountNumber();
        Account a = Account.builder()
                .accountNumber(accNo)
                .type(req.getType() == null ? Account.AccountType.SAVINGS : req.getType())
                .balance(req.getBalance() == null ? BigDecimal.ZERO : req.getBalance())
                .customer(c)
                .build();
        accountRepository.save(a);
        return Mappers.toDto(a);
    }

    private String generateAccountNumber() {
        // 12-digit numeric
        String base = String.valueOf((long)(Math.random() * 9_000_000_000_000L) + 1_000_000_000_000L);
        return base.substring(0, 12);
    }
}