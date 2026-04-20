package com.pos.ecommerce.infrastructure.model.response;

import com.pos.ecommerce.constant.enums.ResponseCode;

import java.time.Instant;
import java.util.List;

public class ApiResponseBuilder<T> {

    private ResponseCode responseCode = ResponseCode.SUCCESS;
    private T            data;
    private String       message;
    private String       requestId;
    private String       traceId;
    private Long         processingTimeMs;
    private List<ErrorDetail.FieldError> fieldErrors;

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    public ApiResponseBuilder<T> code(ResponseCode code) {
        this.responseCode = code; return this;
    }

    public ApiResponseBuilder<T> data(T data) {
        this.data = data; return this;
    }

    public ApiResponseBuilder<T> message(String message) {
        this.message = message; return this;
    }

    public ApiResponseBuilder<T> requestId(String requestId) {
        this.requestId = requestId; return this;
    }

    public ApiResponseBuilder<T> traceId(String traceId) {
        this.traceId = traceId; return this;
    }

    public ApiResponseBuilder<T> processingTime(long ms) {
        this.processingTimeMs = ms; return this;
    }

    public ApiResponseBuilder<T> fieldErrors(List<ErrorDetail.FieldError> errors) {
        this.fieldErrors = errors; return this;
    }

    public ApiResponse<T> build() {
        boolean isSuccess = responseCode.getHttpStatus().is2xxSuccessful();

        ErrorDetail error = null;
        if (!isSuccess) {
            error = ErrorDetail.builder()
                    .code(responseCode.getCode())
                    .message(message != null ? message : responseCode.getMessage())
                    .errors(fieldErrors)
                    .build();
        }

        ResponseMeta meta = ResponseMeta.builder()
                .timestamp(Instant.now().toString())
                .apiVersion("v1")
                .processingTimeMs(processingTimeMs)
                .timezone("UTC")
                .build();

        return ApiResponse.<T>builder()
                .requestId(requestId)
                .traceId(traceId)
                .success(isSuccess)
                .statusCode(responseCode.getHttpStatus().value())
                .code(responseCode.getCode())
                .message(message != null ? message : responseCode.getMessage())
                .data(isSuccess ? data : null)
                .error(error)
                .meta(meta)
                .build();
    }
}
