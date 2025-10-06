package com.myfin.admin.service;

import com.myfin.admin.dto.CustomerCreateUpdateRequest;
import com.myfin.admin.dto.CustomerDto;

import java.util.List;

public interface CustomerAdminService {
    CustomerDto create(CustomerCreateUpdateRequest req);
    CustomerDto update(Long id, CustomerCreateUpdateRequest req);
    CustomerDto activate(Long id);
    CustomerDto deactivate(Long id);
    CustomerDto get(Long id);
    List<CustomerDto> list();
}