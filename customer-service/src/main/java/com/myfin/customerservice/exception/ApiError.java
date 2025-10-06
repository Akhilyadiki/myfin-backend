package com.myfin.customerservice.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiError {
    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}