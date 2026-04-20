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
public class ApiResponseWrapper<T> {

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


}