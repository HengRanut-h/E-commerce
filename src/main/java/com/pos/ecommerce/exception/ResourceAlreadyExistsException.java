package com.pos.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends BaseException {

    // 409 - Conflict (e.g., duplicate entry)
    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(
                String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue),
                "RESOURCE_ALREADY_EXISTS",
                HttpStatus.CONFLICT
        );
    }
}
