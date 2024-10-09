package com.local.localdemo;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements Filter {
    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // get Correlation ID from request header
        String correlationId = httpRequest.getHeader(CORRELATION_ID_HEADER);

        // use UUID to generate a new one if no CID found
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        // add CID to response header
        httpResponse.setHeader(CORRELATION_ID_HEADER, correlationId);

        // put CID to MDC (for logger)
        MDC.put("correlationId", correlationId);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // clear MDC
            MDC.clear();
        }
    }

    @Override
    public void destroy() {}
}
