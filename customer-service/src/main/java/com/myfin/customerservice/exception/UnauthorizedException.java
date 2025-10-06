package com.myfin.customerservice.exception;


public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg){ super(msg); }
}
