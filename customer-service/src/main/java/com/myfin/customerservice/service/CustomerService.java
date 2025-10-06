package com.myfin.customerservice.service;



import com.myfin.customerservice.dto.AccountDto;
import com.myfin.customerservice.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getProfile(String username);
    List<AccountDto> getAccounts(String username);
    AccountDto createAccount(String username, AccountDto req);
}