//package com.pos.ecommerce.security.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.nio.file.AccessDeniedException;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<Map<String, Object>> handleBadCredentials(BadCredentialsException ex) {
//        return error(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "Invalid email or password");
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex) {
//        return error(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "You don't have permission");
//    }
//
//    @ExceptionHandler(TokenException.class)
//    public ResponseEntity<Map<String, Object>> handleToken(TokenException ex) {
//        return error(HttpStatus.UNAUTHORIZED, "TOKEN_ERROR", ex.getMessage());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors()
//                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("status", 422);
//        body.put("error", "VALIDATION_FAILED");
//        body.put("errors", errors);
//        body.put("timestamp", LocalDateTime.now());
//        return ResponseEntity.unprocessableEntity().body(body);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleAll(Exception ex) {
//        return error(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR", "Unexpected error occurred");
//    }
//
//    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String code, String message) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("status",    status.value());
//        body.put("error",     code);
//        body.put("message",   message);
//        body.put("timestamp", LocalDateTime.now());
//        return ResponseEntity.status(status).body(body);
//    }
//}