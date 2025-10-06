package com.myfin.customerservice.service;


import com.myfin.customerservice.dto.*;
import com.myfin.customerservice.entity.Customer;
import com.myfin.customerservice.entity.LoanApplication;
import com.myfin.customerservice.exception.NotFoundException;
import com.myfin.customerservice.repository.CustomerRepository;
import com.myfin.customerservice.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final CustomerRepository customerRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    public EmiResponse calculateEmi(EmiRequest req) {
        // EMI = P * r * (1+r)^n / ((1+r)^n - 1)
        BigDecimal P = req.getPrincipal();
        double monthlyRate = req.getAnnualInterestRate() / 12.0 / 100.0;
        int n = req.getTenureMonths();

        BigDecimal emi;
        if (monthlyRate == 0.0) {
            emi = P.divide(BigDecimal.valueOf(n), 2, RoundingMode.HALF_UP);
        } else {
            double r = monthlyRate;
            double factor = Math.pow(1 + r, n);
            double val = P.doubleValue() * r * factor / (factor - 1);
            emi = BigDecimal.valueOf(val).setScale(2, RoundingMode.HALF_UP);
        }
        return EmiResponse.builder().emi(emi).build();
    }

    @Override
    public void apply(String username, LoanApplicationRequest req) {
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        LoanApplication la = LoanApplication.builder()
                .customer(c)
                .principal(req.getPrincipal())
                .annualInterestRate(req.getAnnualInterestRate())
                .tenureMonths(req.getTenureMonths())
                .status(LoanApplication.LoanStatus.PENDING)
                .createdAt(OffsetDateTime.now())
                .build();
        loanApplicationRepository.save(la);
    }
}