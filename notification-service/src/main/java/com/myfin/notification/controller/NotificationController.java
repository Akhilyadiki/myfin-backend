package com.myfin.notification.controller;

import com.myfin.notification.dto.BalanceZeroNotificationRequest;
import com.myfin.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/balance-zero")
    public ResponseEntity<Void> balanceZero(@Valid @RequestBody BalanceZeroNotificationRequest req) {
        notificationService.sendBalanceZeroNotification(req);
        return ResponseEntity.ok().build();
    }
}
