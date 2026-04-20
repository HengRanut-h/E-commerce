package com.pos.ecommerce.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // 2xx — Success
    SUCCESS             ("200", "Success",                         HttpStatus.OK),
    CREATED             ("201", "Resource created successfully",   HttpStatus.CREATED),
    UPDATED             ("202", "Resource updated successfully",   HttpStatus.OK),
    DELETED             ("203", "Resource deleted successfully",   HttpStatus.OK),
    NO_CONTENT          ("2004", "No content",                     HttpStatus.NO_CONTENT),

    // 4xx — Client errors
    BAD_REQUEST         ("400", "Bad request",                    HttpStatus.BAD_REQUEST),
    UNAUTHORIZED        ("401", "Unauthorized",                   HttpStatus.UNAUTHORIZED),
    FORBIDDEN           ("403", "Forbidden",                      HttpStatus.FORBIDDEN),
    NOT_FOUND           ("404", "Resource not found",             HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED  ("405", "Method not allowed",             HttpStatus.METHOD_NOT_ALLOWED),
    CONFLICT            ("409", "Resource conflict",              HttpStatus.CONFLICT),
    VALIDATION_FAILED   ("422", "Validation failed",              HttpStatus.UNPROCESSABLE_ENTITY),
    TOO_MANY_REQUESTS   ("429", "Too many requests",              HttpStatus.TOO_MANY_REQUESTS),

    // 5xx — Server errors
    INTERNAL_ERROR      ("500", "Internal server error",          HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE ("503", "Service unavailable",            HttpStatus.SERVICE_UNAVAILABLE),
    GATEWAY_TIMEOUT     ("504", "Gateway timeout",                HttpStatus.GATEWAY_TIMEOUT);

    private final String     code;
    private final String     message;
    private final HttpStatus httpStatus;
}
