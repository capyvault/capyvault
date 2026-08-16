package dev.capyvault.commonweb.trace;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public final class TraceIdFilter
        extends OncePerRequestFilter {

    public static final String TRACE_ID = "traceId";

    public static final String TRACE_HEADER =
            "X-Trace-Id";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String traceId =
                request.getHeader(TRACE_HEADER);

        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID()
                    .toString()
                    .replace("-", "");
        }

        try {

            MDC.put(
                    TRACE_ID,
                    traceId
            );

            response.setHeader(
                    TRACE_HEADER,
                    traceId
            );

            filterChain.doFilter(
                    request,
                    response
            );

        } finally {

            MDC.remove(TRACE_ID);
        }
    }
}