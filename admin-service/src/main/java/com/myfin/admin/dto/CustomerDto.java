package com.myfin.admin.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerDto {
    private Long id;
    private String username;
    private String email;
    private boolean active;
}