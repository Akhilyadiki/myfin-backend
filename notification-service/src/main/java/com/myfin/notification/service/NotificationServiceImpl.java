package com.myfin.notification.service;

import com.myfin.notification.dto.BalanceZeroNotificationRequest;
import com.myfin.notification.entity.NotificationLog;
import com.myfin.notification.repository.NotificationLogRepository;
import com.myfin.notification.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationLogRepository logRepository;
    private final EmailSender emailSender;

    @Override
    public void sendBalanceZeroNotification(BalanceZeroNotificationRequest req) {
        String message = String.format("Dear %s, your account %s has reached a zero balance.",
                req.getUsername(), req.getAccountNumber());

        // Simulate sending email
        emailSender.sendEmail(req.getUsername(), "Balance Alert", message);

        // Log notification
        NotificationLog log = NotificationLog.builder()
                .username(req.getUsername())
                .accountNumber(req.getAccountNumber())
                .message(message)
                .sentAt(OffsetDateTime.now())
                .build();
        logRepository.save(log);
    }
}
