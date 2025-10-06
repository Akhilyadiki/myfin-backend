package com.myfin.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoanDecisionRequest {
    @NotNull private Long loanApplicationId;
    @NotNull private Boolean approve; // true -> approve, false -> deny
}