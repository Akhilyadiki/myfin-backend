package com.myfin.customerservice.controller;


import com.myfin.customerservice.dto.*;
import com.myfin.customerservice.security.JwtTokenProvider;
import com.myfin.customerservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request){
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request){
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