package com.myfin.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMessageRequest {
    @NotBlank
    private String message;
}