package com.myfin.customerservice.service;

import com.myfin.customerservice.dto.ChatMessageRequest;
import com.myfin.customerservice.entity.ChatMessage;
import com.myfin.customerservice.entity.Customer;
import com.myfin.customerservice.exception.NotFoundException;
import com.myfin.customerservice.repository.ChatMessageRepository;
import com.myfin.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void sendCustomerMessage(String username, ChatMessageRequest req) {
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        ChatMessage msg = ChatMessage.builder()
                .customer(c)
                .sender(ChatMessage.Sender.CUSTOMER)
                .message(req.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();
        chatMessageRepository.save(msg);
    }

    @Override
    public List<ChatMessage> getConversation(String username) {
        Customer c = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return chatMessageRepository.findByCustomerOrderByTimestampAsc(c);
    }
}