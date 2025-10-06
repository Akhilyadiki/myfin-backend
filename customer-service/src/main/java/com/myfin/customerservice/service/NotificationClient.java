package com.myfin.customerservice.service;

public interface NotificationClient {
    void notifyBalanceZero(String customerUsername, String accountNumber);
}