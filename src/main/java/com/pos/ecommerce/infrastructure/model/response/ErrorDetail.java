package com.pos.ecommerce.infrastructure.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail {

    private String              code;
    private String              message;
    private String              field;       // for single field errors
    private String              path;        // request path
    private List<FieldError> errors;      // for validation (multiple fields)
    private Map<String, Object> context;     // extra debug info (dev only)

    public static ErrorDetail of(String code, String message) {
        return ErrorDetail.builder().code(code).message(message).build();
    }

    @Data @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class FieldError {
        private String field;
        private Object rejectedValue;
        private String message;
    }
}