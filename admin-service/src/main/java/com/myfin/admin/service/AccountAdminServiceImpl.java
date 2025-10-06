package com.myfin.admin.service;

import com.myfin.admin.dto.AccountCreateUpdateRequest;
import com.myfin.admin.dto.AccountDto;
import com.myfin.admin.entity.Account;
import com.myfin.admin.entity.Customer;
import com.myfin.admin.exception.BadRequestException;
import com.myfin.admin.exception.NotFoundException;
import com.myfin.admin.repository.AccountRepository;
import com.myfin.admin.repository.CustomerRepository;
import com.myfin.admin.util.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountAdminServiceImpl implements AccountAdminService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AccountDto create(AccountCreateUpdateRequest req) {
        Customer customer = customerRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        if (accountRepository.findByAccountNumber(req.getAccountNumber()).isPresent())
            throw new BadRequestException("Account number already exists");

        Account a = Account.builder()
                .accountNumber(req.getAccountNumber())
                .type(req.getType())
                .balance(req.getBalance())
                .customer(customer)
                .build();
        accountRepository.save(a);
        return Mappers.toDto(a);
    }

    @Override
    public AccountDto update(Long id, AccountCreateUpdateRequest req) {
        Account a = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        if (!a.getAccountNumber().equals(req.getAccountNumber())
                && accountRepository.findByAccountNumber(req.getAccountNumber()).isPresent())
            throw new BadRequestException("Account number already exists");

        if (!a.getCustomer().getId().equals(req.getCustomerId())) {
            Customer newOwner = customerRepository.findById(req.getCustomerId())
                    .orElseThrow(() -> new NotFoundException("Customer not found"));
            a.setCustomer(newOwner);
        }
        a.setAccountNumber(req.getAccountNumber());
        a.setType(req.getType());
        a.setBalance(req.getBalance());
        accountRepository.save(a);
        return Mappers.toDto(a);
    }

    @Override
    public AccountDto get(Long id) {
        Account a = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        return Mappers.toDto(a);
    }

    @Override
    public List<AccountDto> listByCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId))
            throw new NotFoundException("Customer not found");
        return accountRepository.findByCustomerId(customerId).stream()
                .map(Mappers::toDto).toList();
    }

    @Override
    public List<AccountDto> listAll() {
        return accountRepository.findAll().stream().map(Mappers::toDto).toList();
    }
}