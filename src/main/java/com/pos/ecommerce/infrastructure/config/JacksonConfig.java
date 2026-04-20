package com.pos.ecommerce.infrastructure.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {

        return JsonMapper.builder()
                // ✅ Java 8 Date/Time support
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

                // ✅ Remove null fields from JSON
                .serializationInclusion(JsonInclude.Include.NON_NULL)

                // ✅ Ignore unknown fields from frontend
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

                // ✅ Avoid errors for empty objects
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)

                .build();
    }
}