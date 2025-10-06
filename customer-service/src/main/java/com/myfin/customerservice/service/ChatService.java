package com.myfin.customerservice.service;


import com.myfin.customerservice.dto.ChatMessageRequest;
import com.myfin.customerservice.entity.ChatMessage;

import java.util.List;

public interface ChatService {
    void sendCustomerMessage(String username, ChatMessageRequest req);
    List<ChatMessage> getConversation(String username);
}