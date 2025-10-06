package com.myfin.admin.service;

import com.myfin.admin.dto.LoanDecisionRequest;
import com.myfin.admin.entity.Customer;
import com.myfin.admin.entity.LoanApplication;
import com.myfin.admin.exception.BadRequestException;
import com.myfin.admin.exception.NotFoundException;
import com.myfin.admin.repository.AccountRepository;
import com.myfin.admin.repository.CustomerRepository;
import com.myfin.admin.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LoanApprovalServiceImpl implements LoanApprovalService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void decide(LoanDecisionRequest req) {
        LoanApplication la = loanApplicationRepository.findById(req.getLoanApplicationId())
                .orElseThrow(() -> new NotFoundException("Loan application not found"));
        Customer customer = la.getCustomer();
        if (customer == null) {
            throw new NotFoundException("Customer not linked to loan application");
        }

        BigDecimal totalBalance = accountRepository.findByCustomerId(customer.getId()).stream()
                .map(a -> a.getBalance() == null ? BigDecimal.ZERO : a.getBalance())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (Boolean.TRUE.equals(req.getApprove())) {
            // Basic rule: approve only if total balance is >= 10% of principal
            BigDecimal threshold = la.getPrincipal().multiply(new BigDecimal("0.10"));
            if (totalBalance.compareTo(threshold) >= 0) {
                la.setStatus(LoanApplication.LoanStatus.APPROVED);
            } else {
                throw new BadRequestException("Insufficient customer balance for approval");
            }
        } else {
            la.setStatus(LoanApplication.LoanStatus.DENIED);
        }
        loanApplicationRepository.save(la);
    }
}