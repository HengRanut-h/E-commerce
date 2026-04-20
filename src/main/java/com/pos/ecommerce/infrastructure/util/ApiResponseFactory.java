package com.pos.ecommerce.infrastructure.util;

import com.pos.ecommerce.constant.enums.ResponseCode;
import com.pos.ecommerce.infrastructure.model.response.ApiResponseWrapper;
import com.pos.ecommerce.infrastructure.model.response.ErrorDetail;
import com.pos.ecommerce.infrastructure.model.response.ResponseMeta;
import org.springframework.http.HttpStatus;

public class ApiResponseFactory {
    // ─────────────────────────────────────────
    // Static factory helpers
    // ─────────────────────────────────────────

    public static <T> ApiResponseWrapper<T> success(T data) {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponseWrapper<T> success(T data, String message) {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.SUCCESS.getCode())
                .message(message)
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponseWrapper<T> created(T data) {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .code(ResponseCode.CREATED.getCode())
                .message(ResponseCode.CREATED.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponseWrapper<T> updated(T data) {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.UPDATED.getCode())
                .message(ResponseCode.UPDATED.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponseWrapper<T> deleted() {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.DELETED.getCode())
                .message(ResponseCode.DELETED.getMessage())
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponseWrapper<T> of(ResponseCode rc, T data) {
        return ApiResponseWrapper.<T>builder()
                .success(rc.getHttpStatus().is2xxSuccessful())
                .statusCode(rc.getHttpStatus().value())
                .code(rc.getCode())
                .message(rc.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponseWrapper<T> error(ResponseCode rc, String detail) {
        return ApiResponseWrapper.<T>builder()
                .success(false)
                .statusCode(rc.getHttpStatus().value())
                .code(rc.getCode())
                .message(rc.getMessage())
                .error(ErrorDetail.of(rc.getCode(), detail))
                .meta(ResponseMeta.now())
                .build();
    }
}
