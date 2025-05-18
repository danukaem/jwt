package com.assignment.eventManagementSystem.filter;

import io.github.bucket4j.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    int rateLimit =1000;
    int durationInHours =1;

    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.simple(rateLimit, Duration.ofHours(durationInHours)); // 100 requests per 15 minutes
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String identifier = resolveUserOrIp(request);
        Bucket bucket = cache.computeIfAbsent(identifier, k -> createBucket());

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.getWriter().write("Too many requests - try again later.");
        }
    }

    private String resolveUserOrIp(HttpServletRequest request) {
        // Prefer user if authenticated, otherwise use IP address
        String username = null;
        if (request.getUserPrincipal() != null) {
            username = request.getUserPrincipal().getName();
        }
        return (username != null) ? "USER_" + username : "IP_" + request.getRemoteAddr();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/login") || path.equals("/register"); // Skip rate limiting for auth endpoints
    }
}
