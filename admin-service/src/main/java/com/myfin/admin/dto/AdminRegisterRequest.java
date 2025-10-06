package com.myfin.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminRegisterRequest {
    @NotBlank private String username;
    @NotBlank private String password;
    @Email @NotBlank private String email;
}