package com.myfin.customerservice.controller;

import com.myfin.customerservice.dto.TransactionDto;
import com.myfin.customerservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<TransactionDto>> list(@AuthenticationPrincipal User user,
                                                     @PathVariable String accountNumber){
        return ResponseEntity.ok(accountService.getTransactions(user.getUsername(), accountNumber));
    }
}