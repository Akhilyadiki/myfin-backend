package com.myfin.customerservice.service;

import com.myfin.customerservice.dto.*;
import com.myfin.customerservice.entity.*;
import com.myfin.customerservice.exception.BadRequestException;
import com.myfin.customerservice.exception.NotFoundException;
import com.myfin.customerservice.repository.*;
import com.myfin.customerservice.util.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionIdGenerator transactionIdGenerator;
    private final NotificationClient notificationClient;

    @Override
    @Transactional
    public TransactionDto deposit(String username, DepositWithdrawRequest req) {
        Account a = getOwnedAccount(username, req.getAccountNumber());
        if (req.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        a.setBalance(a.getBalance().add(req.getAmount()));
        accountRepository.save(a);

        Transaction t = recordTransaction(a, Transaction.TransactionType.DEPOSIT, req.getAmount(), null, null);
        return Mappers.toDto(t);
    }

    @Override
    @Transactional
    public TransactionDto withdraw(String username, DepositWithdrawRequest req) {
        Account a = getOwnedAccount(username, req.getAccountNumber());
        if (req.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        if (a.getBalance().compareTo(req.getAmount()) < 0) {
            throw new BadRequestException("Insufficient balance");
        }
        a.setBalance(a.getBalance().subtract(req.getAmount()));
        accountRepository.save(a);

        if (a.getBalance().compareTo(BigDecimal.ZERO) == 0) {
            notificationClient.notifyBalanceZero(a.getCustomer().getUsername(), a.getAccountNumber());
        }

        Transaction t = recordTransaction(a, Transaction.TransactionType.WITHDRAW, req.getAmount(), null, null);
        return Mappers.toDto(t);
    }

    @Override
    @Transactional
    public TransactionDto transfer(String username, TransferRequest req) {
        if (req.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        Account from = getOwnedAccount(username, req.getFromAccountNumber());
        Account to = accountRepository.findByAccountNumber(req.getToAccountNumber())
                .orElseThrow(() -> new NotFoundException("Destination account not found"));

        if (from.getId().equals(to.getId())) throw new BadRequestException("Cannot transfer to same account");
        if (from.getBalance().compareTo(req.getAmount()) < 0) throw new BadRequestException("Insufficient balance");

        from.setBalance(from.getBalance().subtract(req.getAmount()));
        to.setBalance(to.getBalance().add(req.getAmount()));
        accountRepository.save(from);
        accountRepository.save(to);

        if (from.getBalance().compareTo(BigDecimal.ZERO) == 0) {
            notificationClient.notifyBalanceZero(from.getCustomer().getUsername(), from.getAccountNumber());
        }

        Transaction tFrom = recordTransaction(from, Transaction.TransactionType.TRANSFER, req.getAmount(), to.getAccountNumber(), null);
        recordTransaction(to, Transaction.TransactionType.DEPOSIT, req.getAmount(), from.getAccountNumber(), null);

        return Mappers.toDto(tFrom);
    }

    @Override
    @Transactional
    public TransactionDto invest(String username, InvestmentTransferRequest req) {
        String cat = req.getCategory().toUpperCase();
        if (!cat.equals("LOAN") && !cat.equals("RD") && !cat.equals("FD")) {
            throw new BadRequestException("Invalid investment category");
        }
        if (req.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        Account a = getOwnedAccount(username, req.getFromAccountNumber());
        if (a.getBalance().compareTo(req.getAmount()) < 0) {
            throw new BadRequestException("Insufficient balance");
        }

        a.setBalance(a.getBalance().subtract(req.getAmount()));
        accountRepository.save(a);

        if (a.getBalance().compareTo(BigDecimal.ZERO) == 0) {
            notificationClient.notifyBalanceZero(a.getCustomer().getUsername(), a.getAccountNumber());
        }

        Transaction t = recordTransaction(a, Transaction.TransactionType.INVESTMENT, req.getAmount(), null, cat);
        // Future: call respective microservices for RD/FD/Loan repayment allocation
        return Mappers.toDto(t);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> getTransactions(String username, String accountNumber) {
        Account a = getOwnedAccount(username, accountNumber);
        return a.getTransactions() == null ? List.of() :
                a.getTransactions().stream().sorted((x,y)->x.getCreatedAt().compareTo(y.getCreatedAt()))
                        .map(Mappers::toDto).toList();
    }

    private Account getOwnedAccount(String username, String accountNumber){
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        Account a = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        if (!a.getCustomer().getId().equals(c.getId())) throw new BadRequestException("Account does not belong to user");
        return a;
    }

    private Transaction recordTransaction(Account a, Transaction.TransactionType type,
                                          BigDecimal amount, String counterparty, String category) {
        String id;
        do { id = transactionIdGenerator.generate(); }
        while (transactionRepository.existsByTransactionId(id));

        Transaction t = Transaction.builder()
                .transactionId(id)
                .type(type)
                .amount(amount)
                .createdAt(OffsetDateTime.now())
                .counterpartyAccountNumber(counterparty)
                .category(category)
                .account(a)
                .build();
        return transactionRepository.save(t);
    }
}