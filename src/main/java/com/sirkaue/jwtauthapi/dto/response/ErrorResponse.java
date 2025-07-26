package com.sirkaue.jwtauthapi.dto.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        String method) {

    public ErrorResponse(HttpServletRequest request, HttpStatus status, Exception message) {
        this(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message.getMessage(),
                request.getRequestURI(),
                request.getMethod());
    }
}
