package com.myfin.admin.service;

import com.myfin.admin.dto.ChatMessageRequest;
import com.myfin.admin.entity.ChatMessage;

import java.util.List;

public interface ChatService {
    void sendAdminMessage(Long customerId, ChatMessageRequest req);
    List<ChatMessage> getConversation(Long customerId);
}