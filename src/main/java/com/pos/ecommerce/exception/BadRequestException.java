package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

// 400 - Bad Request
public class BadRequestException extends BaseException{
    public BadRequestException(String message) {

        super(message, "BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }
}
