package com.pos.ecommerce.infrastructure.util;

import com.pos.ecommerce.constant.enums.ResponseCode;
import com.pos.ecommerce.infrastructure.model.response.ApiResponseWrapper;
import com.pos.ecommerce.infrastructure.model.response.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtils {

    private ResponseUtils() {}

    public static <T> ResponseEntity<ApiResponseWrapper<T>> ok(T data) {
        return ResponseEntity.ok(ApiResponseFactory.success(data));
    }

    public static <T> ResponseEntity<ApiResponseWrapper<T>> ok(T data, String message) {
        return ResponseEntity.ok(ApiResponseFactory.success(data, message));
    }

    public static <T> ResponseEntity<ApiResponseWrapper<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseFactory.created(data));
    }

    public static <T> ResponseEntity<ApiResponseWrapper<T>> updated(T data) {
        return ResponseEntity.ok(ApiResponseFactory.updated(data));
    }

    public static ResponseEntity<ApiResponseWrapper<Void>> deleted() {
        return ResponseEntity.ok(ApiResponseFactory.deleted());
    }

    public static <T> ResponseEntity<ApiResponseWrapper<PagedResponse<T>>> paged(Page<T> page) {
        return ResponseEntity.ok(PagedResponse.toApiResponse(page));
    }

    public static <T> ResponseEntity<ApiResponseWrapper<T>> of(ResponseCode code, T data) {
        return ResponseEntity.status(code.getHttpStatus()).body(ApiResponseFactory.of(code, data));
    }

    public static <T> ResponseEntity<ApiResponseWrapper<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}