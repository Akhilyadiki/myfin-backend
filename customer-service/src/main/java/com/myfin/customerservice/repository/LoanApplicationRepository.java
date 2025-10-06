package com.myfin.customerservice.repository;


import com.myfin.customerservice.entity.LoanApplication;
import com.myfin.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByCustomer(Customer customer);
}