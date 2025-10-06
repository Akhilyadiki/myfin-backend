package com.myfin.admin.controller;

import com.myfin.admin.dto.*;
import com.myfin.admin.service.AdminAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody AdminRegisterRequest request){
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody AdminLoginRequest request){
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(JwtResponse.builder().token(token).tokenType("Bearer").build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader){
        String token = authHeader != null && authHeader.startsWith("Bearer ") ? authHeader.substring(7) : null;
        if (token != null) authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}