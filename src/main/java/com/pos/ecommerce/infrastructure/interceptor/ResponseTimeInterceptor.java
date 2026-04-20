package com.pos.ecommerce.infrastructure.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ResponseTimeInterceptor implements HandlerInterceptor {

    private static final String START_ATTR = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        request.setAttribute(START_ATTR, System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest  request,
                                HttpServletResponse response,
                                Object              handler,
                                Exception           ex) {
        Long start = (Long) request.getAttribute(START_ATTR);
        if (start != null) {
            long elapsed = System.currentTimeMillis() - start;
            response.setHeader("X-Response-Time-Ms", String.valueOf(elapsed));
        }
    }
}