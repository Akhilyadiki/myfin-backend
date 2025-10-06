package com.myfin.customerservice.controller;

import com.myfin.customerservice.dto.*;
import com.myfin.customerservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> deposit(@AuthenticationPrincipal User user,
                                                  @Valid @RequestBody DepositWithdrawRequest req){
        return ResponseEntity.ok(accountService.deposit(user.getUsername(), req));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDto> withdraw(@AuthenticationPrincipal User user,
                                                   @Valid @RequestBody DepositWithdrawRequest req){
        return ResponseEntity.ok(accountService.withdraw(user.getUsername(), req));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> transfer(@AuthenticationPrincipal User user,
                                                   @Valid @RequestBody TransferRequest req){
        return ResponseEntity.ok(accountService.transfer(user.getUsername(), req));
    }

    @PostMapping("/invest")
    public ResponseEntity<TransactionDto> invest(@AuthenticationPrincipal User user,
                                                 @Valid @RequestBody InvestmentTransferRequest req){
        return ResponseEntity.ok(accountService.invest(user.getUsername(), req));
    }

    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<List<TransactionDto>> transactions(@AuthenticationPrincipal User user,
                                                             @PathVariable String accountNumber){
        return ResponseEntity.ok(accountService.getTransactions(user.getUsername(), accountNumber));
    }
}