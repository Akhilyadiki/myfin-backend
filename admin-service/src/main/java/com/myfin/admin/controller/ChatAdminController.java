package com.myfin.admin.controller;

import com.myfin.admin.dto.ChatMessageRequest;
import com.myfin.admin.entity.ChatMessage;
import com.myfin.admin.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/chat")
@RequiredArgsConstructor
public class ChatAdminController {

    private final ChatService chatService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Void> send(@PathVariable Long customerId,
                                     @Valid @RequestBody ChatMessageRequest req){
        chatService.sendAdminMessage(customerId, req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ChatMessage>> conversation(@PathVariable Long customerId){
        return ResponseEntity.ok(chatService.getConversation(customerId));
    }
}