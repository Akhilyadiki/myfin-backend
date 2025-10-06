package com.myfin.admin.service;

import com.myfin.admin.dto.AccountCreateUpdateRequest;
import com.myfin.admin.dto.AccountDto;

import java.util.List;

public interface AccountAdminService {
    AccountDto create(AccountCreateUpdateRequest req);
    AccountDto update(Long id, AccountCreateUpdateRequest req);
    AccountDto get(Long id);
    List<AccountDto> listByCustomer(Long customerId);
    List<AccountDto> listAll();
}