package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {

    // 404 - Resource Not Found
    // pass to contractor baseException
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(
                String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue),
                "RESOURCE_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }
}
