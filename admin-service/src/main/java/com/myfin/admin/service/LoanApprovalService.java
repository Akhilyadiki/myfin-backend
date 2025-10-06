package com.myfin.admin.service;

import com.myfin.admin.dto.LoanDecisionRequest;

public interface LoanApprovalService {
    void decide(LoanDecisionRequest req);
}