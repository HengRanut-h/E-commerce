package com.pos.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

// 422 - Unprocessable Entity (Validation)
@Getter
public class ValidationException extends BaseException {
    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Validation failed", "VALIDATION_FAILED", HttpStatus.UNPROCESSABLE_ENTITY);
        this.errors = errors;
    }

}
