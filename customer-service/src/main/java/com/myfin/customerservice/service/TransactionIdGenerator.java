package com.myfin.customerservice.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class TransactionIdGenerator {
    private static final SecureRandom RNG = new SecureRandom();

    public String generate() {
        // 16-char base36 random token
        long a = RNG.nextLong();
        long b = RNG.nextLong();
        return (Long.toUnsignedString(a,36) + Long.toUnsignedString(b,36))
                .toUpperCase().replace("-", "").substring(0,16);
    }
}