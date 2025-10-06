package com.myfin.customerservice.repository;

import com.myfin.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}