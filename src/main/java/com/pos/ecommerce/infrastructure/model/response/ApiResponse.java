package com.pos.ecommerce.infrastructure.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pos.ecommerce.constant.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

// ApiResponse.java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    // ── Identity ──────────────────────────────
    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("trace_id")
    private String traceId;

    // ── Status ────────────────────────────────
    private boolean success;

    @JsonProperty("status_code")
    private int statusCode;

    private String code;       // e.g. "200", "4004"

    private String message;

    // ── Payload ───────────────────────────────
    private T data;

    // ── Error detail (null on success) ────────
    private ErrorDetail error;

    // ── Metadata ──────────────────────────────
    private ResponseMeta meta;

    // ─────────────────────────────────────────
    // Static factory helpers
    // ─────────────────────────────────────────

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.SUCCESS.getCode())
                .message(message)
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponse<T> created(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .code(ResponseCode.CREATED.getCode())
                .message(ResponseCode.CREATED.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponse<T> updated(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.UPDATED.getCode())
                .message(ResponseCode.UPDATED.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponse<T> deleted() {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .code(ResponseCode.DELETED.getCode())
                .message(ResponseCode.DELETED.getMessage())
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponse<T> of(ResponseCode rc, T data) {
        return ApiResponse.<T>builder()
                .success(rc.getHttpStatus().is2xxSuccessful())
                .statusCode(rc.getHttpStatus().value())
                .code(rc.getCode())
                .message(rc.getMessage())
                .data(data)
                .meta(ResponseMeta.now())
                .build();
    }

    public static <T> ApiResponse<T> error(ResponseCode rc, String detail) {
        return ApiResponse.<T>builder()
                .success(false)
                .statusCode(rc.getHttpStatus().value())
                .code(rc.getCode())
                .message(rc.getMessage())
                .error(ErrorDetail.of(rc.getCode(), detail))
                .meta(ResponseMeta.now())
                .build();
    }
}