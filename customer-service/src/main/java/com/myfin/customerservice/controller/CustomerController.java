package com.myfin.customerservice.controller;

import com.myfin.customerservice.dto.AccountDto;
import com.myfin.customerservice.dto.CustomerDto;
import com.myfin.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/me")
    public ResponseEntity<CustomerDto> me(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(customerService.getProfile(user.getUsername()));
    }

    @GetMapping("/me/accounts")
    public ResponseEntity<List<AccountDto>> myAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(customerService.getAccounts(user.getUsername()));
    }

    @PostMapping("/me/accounts")
    public ResponseEntity<AccountDto> createAccount(@AuthenticationPrincipal User user,
                                                    @Valid @RequestBody AccountDto req){
        return ResponseEntity.ok(customerService.createAccount(user.getUsername(), req));
    }
}