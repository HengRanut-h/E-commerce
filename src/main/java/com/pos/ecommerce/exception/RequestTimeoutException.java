package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

// 408 - Request Timeout
public class RequestTimeoutException extends BaseException {
    public RequestTimeoutException(String message) {
        super(message, "REQUEST_TIMEOUT", HttpStatus.REQUEST_TIMEOUT);
    }
}
