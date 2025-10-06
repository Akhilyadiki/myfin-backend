package com.myfin.admin.service;

import com.myfin.admin.dto.CustomerCreateUpdateRequest;
import com.myfin.admin.dto.CustomerDto;
import com.myfin.admin.entity.Customer;
import com.myfin.admin.exception.BadRequestException;
import com.myfin.admin.exception.NotFoundException;
import com.myfin.admin.repository.CustomerRepository;
import com.myfin.admin.util.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerAdminServiceImpl implements CustomerAdminService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto create(CustomerCreateUpdateRequest req) {
        if (customerRepository.existsByUsername(req.getUsername()))
            throw new BadRequestException("Username already exists");
        if (customerRepository.existsByEmail(req.getEmail()))
            throw new BadRequestException("Email already exists");

        Customer c = Customer.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .active(req.getActive() == null ? true : req.getActive())
                .build();
        customerRepository.save(c);
        return Mappers.toDto(c);
    }

    @Override
    public CustomerDto update(Long id, CustomerCreateUpdateRequest req) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        if (!c.getUsername().equals(req.getUsername()) && customerRepository.existsByUsername(req.getUsername()))
            throw new BadRequestException("Username already exists");
        if (!c.getEmail().equals(req.getEmail()) && customerRepository.existsByEmail(req.getEmail()))
            throw new BadRequestException("Email already exists");

        c.setUsername(req.getUsername());
        c.setEmail(req.getEmail());
        if (req.getActive() != null) c.setActive(req.getActive());
        customerRepository.save(c);
        return Mappers.toDto(c);
    }

    @Override
    public CustomerDto activate(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        c.setActive(true);
        customerRepository.save(c);
        return Mappers.toDto(c);
    }

    @Override
    public CustomerDto deactivate(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        c.setActive(false);
        customerRepository.save(c);
        return Mappers.toDto(c);
    }

    @Override
    public CustomerDto get(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return Mappers.toDto(c);
    }

    @Override
    public List<CustomerDto> list() {
        return customerRepository.findAll().stream().map(Mappers::toDto).toList();
    }
}