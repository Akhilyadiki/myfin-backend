package com.myfin.admin.service;

import com.myfin.admin.dto.AdminRegisterRequest;
import com.myfin.admin.entity.Admin;
import com.myfin.admin.exception.BadRequestException;
import com.myfin.admin.repository.AdminRepository;
import com.myfin.admin.security.JwtTokenProvider;
import com.myfin.admin.security.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public void register(AdminRegisterRequest request) {
        if (adminRepository.existsByUsername(request.getUsername()))
            throw new BadRequestException("Username already exists");
        if (adminRepository.existsByEmail(request.getEmail()))
            throw new BadRequestException("Email already exists");

        Admin a = Admin.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();
        adminRepository.save(a);
    }

    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenProvider.createToken(username);
    }

    @Override
    public void logout(String token) {
        tokenBlacklistService.revokeToken(token);
    }

    @Override
    public boolean isRevoked(String token) {
        return tokenBlacklistService.isRevoked(token);
    }
}
