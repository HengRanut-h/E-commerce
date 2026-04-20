package com.pos.ecommerce.infrastructure.util;

import com.pos.ecommerce.constant.enums.ResponseCode;
import com.pos.ecommerce.infrastructure.model.response.ApiResponse;
import com.pos.ecommerce.infrastructure.model.response.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtils {

    private ResponseUtils() {}

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String message) {
        return ResponseEntity.ok(ApiResponse.success(data, message));
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> updated(T data) {
        return ResponseEntity.ok(ApiResponse.updated(data));
    }

    public static ResponseEntity<ApiResponse<Void>> deleted() {
        return ResponseEntity.ok(ApiResponse.deleted());
    }

    public static <T> ResponseEntity<ApiResponse<PagedResponse<T>>> paged(Page<T> page) {
        return ResponseEntity.ok(PagedResponse.toApiResponse(page));
    }

    public static <T> ResponseEntity<ApiResponse<T>> of(ResponseCode code, T data) {
        return ResponseEntity.status(code.getHttpStatus()).body(ApiResponse.of(code, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}