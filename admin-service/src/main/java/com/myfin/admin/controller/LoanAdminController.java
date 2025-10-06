package com.myfin.admin.controller;

import com.myfin.admin.dto.LoanDecisionRequest;
import com.myfin.admin.service.LoanApprovalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/loans")
@RequiredArgsConstructor
public class LoanAdminController {

    private final LoanApprovalService loanApprovalService;

    @PostMapping("/decide")
    public ResponseEntity<Void> decide(@Valid @RequestBody LoanDecisionRequest req){
        loanApprovalService.decide(req);
        return ResponseEntity.ok().build();
    }
}