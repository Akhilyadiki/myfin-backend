package com.myfin.admin.controller;

import com.myfin.admin.dto.CustomerCreateUpdateRequest;
import com.myfin.admin.dto.CustomerDto;
import com.myfin.admin.service.CustomerAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/customers")
@RequiredArgsConstructor
public class CustomerAdminController {

    private final CustomerAdminService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerCreateUpdateRequest req){
        return ResponseEntity.ok(customerService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id,
                                              @Valid @RequestBody CustomerCreateUpdateRequest req){
        return ResponseEntity.ok(customerService.update(id, req));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<CustomerDto> activate(@PathVariable Long id){
        return ResponseEntity.ok(customerService.activate(id));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<CustomerDto> deactivate(@PathVariable Long id){
        return ResponseEntity.ok(customerService.deactivate(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id){
        return ResponseEntity.ok(customerService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> list(){
        return ResponseEntity.ok(customerService.list());
    }
}