package com.myfin.customerservice.service;

import com.myfin.customerservice.dto.*;
import com.myfin.customerservice.entity.Transaction;

import java.util.List;

public interface AccountService {
    TransactionDto deposit(String username, DepositWithdrawRequest req);
    TransactionDto withdraw(String username, DepositWithdrawRequest req);
    TransactionDto transfer(String username, TransferRequest req);
    TransactionDto invest(String username, InvestmentTransferRequest req);
    List<TransactionDto> getTransactions(String username, String accountNumber);
}