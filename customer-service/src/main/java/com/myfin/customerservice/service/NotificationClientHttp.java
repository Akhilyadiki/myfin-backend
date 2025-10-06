package com.myfin.customerservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationClientHttp implements NotificationClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${notification.base-url:http://localhost:8083}")
    private String notificationBaseUrl;

    @Override
    public void notifyBalanceZero(String customerUsername, String accountNumber) {
        try {
            String url = notificationBaseUrl + "/api/v1/notifications/balance-zero";
            String body = String.format("{\"username\":\"%s\",\"accountNumber\":\"%s\"}", customerUsername, accountNumber);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            restTemplate.postForEntity(url, new HttpEntity<>(body, headers), Void.class);
        } catch (Exception ignored) {
            // Fail-safe: do not break main flow if notification fails
        }
    }
}