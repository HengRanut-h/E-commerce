package com.pos.ecommerce.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // 2xx — Success
    SUCCESS             ("2000", "Success",                         HttpStatus.OK),
    CREATED             ("2001", "Resource created successfully",   HttpStatus.CREATED),
    UPDATED             ("2002", "Resource updated successfully",   HttpStatus.OK),
    DELETED             ("2003", "Resource deleted successfully",   HttpStatus.OK),
    NO_CONTENT          ("2004", "No content",                     HttpStatus.NO_CONTENT),

    // 4xx — Client errors
    BAD_REQUEST         ("4000", "Bad request",                    HttpStatus.BAD_REQUEST),
    UNAUTHORIZED        ("4001", "Unauthorized",                   HttpStatus.UNAUTHORIZED),
    FORBIDDEN           ("4003", "Forbidden",                      HttpStatus.FORBIDDEN),
    NOT_FOUND           ("4004", "Resource not found",             HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED  ("4005", "Method not allowed",             HttpStatus.METHOD_NOT_ALLOWED),
    CONFLICT            ("4009", "Resource conflict",              HttpStatus.CONFLICT),
    VALIDATION_FAILED   ("4022", "Validation failed",              HttpStatus.UNPROCESSABLE_ENTITY),
    TOO_MANY_REQUESTS   ("4029", "Too many requests",              HttpStatus.TOO_MANY_REQUESTS),

    // 5xx — Server errors
    INTERNAL_ERROR      ("5000", "Internal server error",          HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE ("5003", "Service unavailable",            HttpStatus.SERVICE_UNAVAILABLE),
    GATEWAY_TIMEOUT     ("5004", "Gateway timeout",                HttpStatus.GATEWAY_TIMEOUT);

    private final String     code;
    private final String     message;
    private final HttpStatus httpStatus;
}
