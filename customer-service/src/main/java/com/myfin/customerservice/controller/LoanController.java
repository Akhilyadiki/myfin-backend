package com.myfin.customerservice.controller;

import com.myfin.customerservice.dto.*;
import com.myfin.customerservice.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/emi")
    public ResponseEntity<EmiResponse> calculate(@Valid @RequestBody EmiRequest req){
        return ResponseEntity.ok(loanService.calculateEmi(req));
    }

    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@AuthenticationPrincipal User user,
                                      @Valid @RequestBody LoanApplicationRequest req){
        loanService.apply(user.getUsername(), req);
        return ResponseEntity.ok().build();
    }
}