package com.myfin.customerservice.controller;


import com.myfin.customerservice.dto.ChatMessageRequest;
import com.myfin.customerservice.entity.ChatMessage;
import com.myfin.customerservice.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Void> send(@AuthenticationPrincipal User user,
                                     @Valid @RequestBody ChatMessageRequest req){
        chatService.sendCustomerMessage(user.getUsername(), req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ChatMessage>> conversation(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(chatService.getConversation(user.getUsername()));
    }
}