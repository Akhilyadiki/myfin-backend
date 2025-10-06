package com.myfin.customerservice.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){ super(msg); }
}
