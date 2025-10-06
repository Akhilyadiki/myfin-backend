package com.myfin.customerservice.service;

import com.myfin.customerservice.dto.RegisterRequest;
import com.myfin.customerservice.entity.Customer;
import com.myfin.customerservice.exception.BadRequestException;
import com.myfin.customerservice.repository.CustomerRepository;
import com.myfin.customerservice.security.JwtTokenProvider;
import com.myfin.customerservice.security.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public void register(RegisterRequest request) {
        if (customerRepository.existsByUsername(request.getUsername()))
            throw new BadRequestException("Username already exists");
        if (customerRepository.existsByEmail(request.getEmail()))
            throw new BadRequestException("Email already exists");

        Customer c = Customer.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .active(true)
                .build();
        customerRepository.save(c);
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
