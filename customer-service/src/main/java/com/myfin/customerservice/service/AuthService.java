package com.myfin.customerservice.service;

import com.myfin.customerservice.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    String login(String username, String password);
    void logout(String token);
    boolean isRevoked(String token);
}