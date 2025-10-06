package com.myfin.admin.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){ super(msg); }
}