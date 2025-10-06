package com.myfin.admin.repository;

import com.myfin.admin.entity.Customer;
import com.myfin.admin.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByCustomer(Customer customer);
}