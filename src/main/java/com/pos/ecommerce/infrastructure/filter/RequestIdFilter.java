package com.pos.ecommerce.infrastructure.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@Order(1)
public class RequestIdFilter extends OncePerRequestFilter {

    public static final String  REQUEST_ID_HEADER = "X-Request-ID";
    public static final String  TRACE_ID_HEADER   = "X-Trace-ID";
    private static final String MDC_REQUEST_ID    = "requestId";
    private static final String MDC_TRACE_ID      = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String requestId = Optional
                .ofNullable(request.getHeader(REQUEST_ID_HEADER))
                .orElse(UUID.randomUUID().toString());

        String traceId = Optional
                .ofNullable(request.getHeader(TRACE_ID_HEADER))
                .orElse(UUID.randomUUID().toString());

        // Put in MDC so every log line carries the IDs
        MDC.put(MDC_REQUEST_ID, requestId);
        MDC.put(MDC_TRACE_ID,   traceId);

        // Echo back in response headers
        response.setHeader(REQUEST_ID_HEADER, requestId);
        response.setHeader(TRACE_ID_HEADER,   traceId);

        // Store in request attrs so controllers can read them
        request.setAttribute(MDC_REQUEST_ID, requestId);
        request.setAttribute(MDC_TRACE_ID,   traceId);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}