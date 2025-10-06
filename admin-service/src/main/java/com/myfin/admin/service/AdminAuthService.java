package com.myfin.admin.service;

import com.myfin.admin.dto.AdminRegisterRequest;

public interface AdminAuthService {
    void register(AdminRegisterRequest request);
    String login(String username, String password);
    void logout(String token);
    boolean isRevoked(String token);
}