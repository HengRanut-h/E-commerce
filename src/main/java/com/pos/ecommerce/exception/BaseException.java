package com.pos.ecommerce.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

// ── generate contractor by runtimeException to be abstract class ─────────────────────────────────────────
@Getter
public abstract class BaseException extends RuntimeException{

    private final String errorCode;
    private final HttpStatus httpStatus;


    //3 ── Convenience constructors ─────────────────────────────────────────────
    protected BaseException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode  = errorCode;
        this.httpStatus = httpStatus;
    }

    //4 ── Standard constructors ────────────────────────────────────────────────
    protected BaseException(String message, String errorCode, HttpStatus httpStatus,Throwable cause ) {
        super(message, cause);
        this.errorCode  = errorCode;
        this.httpStatus = httpStatus;
    }

    // ── Full-control constructor (for advanced subclass use) ─────────────────
    protected BaseException(String message, Throwable cause, String errorCode, HttpStatus httpStatus ,boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }


    //1
//    protected BaseException(String message) {
//        super(message);
//        this.errorCode  = "INTERNAL_ERROR";        // safe fallback
//        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//    }
//    //2
//    protected BaseException(String message, String errorCode) {
//        super(message);
//        this.errorCode = errorCode;
//        this.httpStatus = HttpStatus.BAD_REQUEST;
//    }


}