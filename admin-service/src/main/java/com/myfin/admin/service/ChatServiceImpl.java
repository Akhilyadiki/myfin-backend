package com.myfin.admin.service;

import com.myfin.admin.dto.ChatMessageRequest;
import com.myfin.admin.entity.ChatMessage;
import com.myfin.admin.entity.Customer;
import com.myfin.admin.exception.NotFoundException;
import com.myfin.admin.repository.ChatMessageRepository;
import com.myfin.admin.repository.CustomerRepository;
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
    public void sendAdminMessage(Long customerId, ChatMessageRequest req) {
        Customer c = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        ChatMessage msg = ChatMessage.builder()
                .customer(c)
                .sender(ChatMessage.Sender.ADMIN)
                .message(req.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();
        chatMessageRepository.save(msg);
    }

    @Override
    public List<ChatMessage> getConversation(Long customerId) {
        Customer c = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return chatMessageRepository.findByCustomerOrderByTimestampAsc(c);
    }
}