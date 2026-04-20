package com.pos.ecommerce.infrastructure.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ResponseMeta {

        private String    timestamp;

        @JsonProperty("api_version")
        private String    apiVersion;

        @JsonProperty("processing_time_ms")
        private Long      processingTimeMs;

        private String    locale;
        private String    timezone;
        private String    server;

        public static ResponseMeta now() {
            return ResponseMeta.builder()
                    .timestamp(Instant.now().toString())
                    .apiVersion("v1")
                    .timezone("UTC")
                    .build();
        }
    }


