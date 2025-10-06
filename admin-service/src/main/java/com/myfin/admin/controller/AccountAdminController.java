package com.myfin.admin.controller;

import com.myfin.admin.dto.AccountCreateUpdateRequest;
import com.myfin.admin.dto.AccountDto;
import com.myfin.admin.service.AccountAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/accounts")
@RequiredArgsConstructor
public class AccountAdminController {

    private final AccountAdminService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> create(@Valid @RequestBody AccountCreateUpdateRequest req){
        return ResponseEntity.ok(accountService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable Long id,
                                             @Valid @RequestBody AccountCreateUpdateRequest req){
        return ResponseEntity.ok(accountService.update(id, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable Long id){
        return ResponseEntity.ok(accountService.get(id));
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<AccountDto>> byCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(accountService.listByCustomer(customerId));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> list(){
        return ResponseEntity.ok(accountService.listAll());
    }
}