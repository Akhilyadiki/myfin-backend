package com.myfin.admin.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg){ super(msg); }
}