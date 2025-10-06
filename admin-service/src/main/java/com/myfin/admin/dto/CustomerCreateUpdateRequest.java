package com.myfin.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerCreateUpdateRequest {
    @NotBlank private String username;
    @Email @NotBlank private String email;
    private Boolean active; // optional on create, used on update/activate/deactivate
}