package com.pos.ecommerce.infrastructure.wrapper;

import com.pos.ecommerce.infrastructure.model.response.ApiResponse;
import com.pos.ecommerce.infrastructure.model.response.PagedResponse;

import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Set;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    private static final Set<String> SKIP_PATHS = Set.of(
            "/actuator", "/swagger-ui", "/v3/api-docs", "/error"
    );

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // Skip if already wrapped or is a Spring internal type
        return !returnType.getParameterType().equals(ApiResponse.class)
                && !returnType.getParameterType().equals(PagedResponse.class)
                && !returnType.getParameterType().equals(ResponseEntity.class)
                && returnType.hasMethodAnnotation(WrapResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        String path = request.getURI().getPath();
        if (SKIP_PATHS.stream().anyMatch(path::startsWith)) return body;

        return ApiResponse.success(body);
    }
}

