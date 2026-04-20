package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

// 500 - Internal Server Error
public class InternalServerErrorException extends BaseException{

    public InternalServerErrorException(String message) {
        super(message, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public InternalServerErrorException(String message, Throwable cause) {
        super(message, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
