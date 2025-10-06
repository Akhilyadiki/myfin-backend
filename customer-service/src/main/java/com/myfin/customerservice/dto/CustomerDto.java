package com.myfin.customerservice.dto;


import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerDto {
    private Long id;
    private String username;
    private String email;
    private boolean active;
}