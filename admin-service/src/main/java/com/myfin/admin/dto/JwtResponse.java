package com.myfin.admin.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class JwtResponse {
    private String token;
    private String tokenType; // "Bearer"
}