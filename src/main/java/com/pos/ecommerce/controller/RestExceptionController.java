//package com.pos.ecommerce.controller;
//
//
//
//import com.pos.ecommerce.exception.ResourceNotFoundException;
//import org.jspecify.annotations.Nullable;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//
//// ── custom exception class ─────────────────────────────────────────
//@RestControllerAdvice
//// ───────────────────────────Note───────────────────────────────
////@RestControllerAdvice change error HTML to JSON.
////ResponseEntityExceptionHandler uses all default handlers that spring have
//// ──────────────────────────────────────────────────────────────
//public class RestExceptionController extends ResponseEntityExceptionHandler {
//
//    @Override
//    protected @Nullable ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
//        return BaseBodyResponse.failure(ex.getMessage(), statusCode);
//    }
//
//
//    // ──────────────────────────────────────────────────────────
//    // Handle Status code 500
//    // ──────────────────────────────────────────────────────────
//    @ExceptionHandler(value = {Exception.class, InternalServerErrorException.class})
//    //handler exception and use parameter
//    public ResponseEntity<Object> handleExceptions(Exception ex) {
//        return BaseBodyResponse.failure(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // ──────────────────────────────────────────────────────────
//    // Handle Status code 400
//    // ──────────────────────────────────────────────────────────
//    @ExceptionHandler(value = BadRequestException.class) //handler exception and use parameter
//    public ResponseEntity<Object> handleBadRequestExceptions(Exception ex) {
//        return BaseBodyResponse.failure(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    // ──────────────────────────────────────────────────────────
//    // Handle Status code 404
//    // ──────────────────────────────────────────────────────────
//    @ExceptionHandler(value = ResourceNotFoundException.class) //handler exception and use parameter
//    public ResponseEntity<Object> handleNotFoundExceptions(Exception ex) {
//        return BaseBodyResponse.failure(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    // ──────────────────────────────────────────────────────────
//    // Handle Status code 409
//    // ──────────────────────────────────────────────────────────
//    @ExceptionHandler(value = ConflictException.class) //handler exception and use parameter
//    public ResponseEntity<Object> handleConflictExceptions(Exception ex) {
//        return BaseBodyResponse.failure(ex.getMessage(), HttpStatus.CONFLICT);
//    }
//
//}
