package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

// 401 - Unauthorized
public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message, "UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }
}
