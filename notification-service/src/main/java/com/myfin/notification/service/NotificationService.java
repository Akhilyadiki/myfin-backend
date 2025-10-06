package com.myfin.notification.service;

import com.myfin.notification.dto.BalanceZeroNotificationRequest;

public interface NotificationService {
    void sendBalanceZeroNotification(BalanceZeroNotificationRequest req);
}
