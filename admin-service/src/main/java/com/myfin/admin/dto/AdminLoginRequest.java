package com.myfin.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminLoginRequest {
    @NotBlank private String username;
    @NotBlank private String password;
}