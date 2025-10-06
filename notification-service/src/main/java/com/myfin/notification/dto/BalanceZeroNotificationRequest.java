package com.myfin.notification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BalanceZeroNotificationRequest {
    @NotBlank private String username;
    @NotBlank private String accountNumber;
}
