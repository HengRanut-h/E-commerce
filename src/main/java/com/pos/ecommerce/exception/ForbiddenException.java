package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

// 403 - Forbidden
public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message, "FORBIDDEN", HttpStatus.FORBIDDEN);
    }
}
