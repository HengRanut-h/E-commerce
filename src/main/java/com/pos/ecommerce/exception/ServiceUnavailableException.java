package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

// 503 - Service Unavailable
public class ServiceUnavailableException extends BaseException {
    public ServiceUnavailableException(String serviceName) {
        super(
                String.format("Service '%s' is currently unavailable", serviceName),
                "SERVICE_UNAVAILABLE",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}
