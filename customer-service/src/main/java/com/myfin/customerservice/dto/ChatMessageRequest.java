package com.myfin.customerservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMessageRequest {
    @NotBlank
    private String message;
}