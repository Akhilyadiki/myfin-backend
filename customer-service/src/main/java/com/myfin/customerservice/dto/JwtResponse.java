package com.myfin.customerservice.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JwtResponse {
    private String token;
    private String tokenType; // "Bearer"
}



