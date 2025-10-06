package com.myfin.customerservice.service;



import com.myfin.customerservice.dto.*;

public interface LoanService {
    EmiResponse calculateEmi(EmiRequest req);
    void apply(String username, LoanApplicationRequest req);
}