package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

// 429 - Too Many Requests
public class TooManyRequestsException extends BaseException {
    public TooManyRequestsException(String message) {
        super(message, "TOO_MANY_REQUESTS", HttpStatus.TOO_MANY_REQUESTS);
    }
}
