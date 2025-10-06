package com.myfin.customerservice.repository;

import com.myfin.customerservice.entity.ChatMessage;
import com.myfin.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByCustomerOrderByTimestampAsc(Customer customer);
}