package com.myfin.customerservice.repository;


import com.myfin.customerservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    boolean existsByTransactionId(String transactionId);
}