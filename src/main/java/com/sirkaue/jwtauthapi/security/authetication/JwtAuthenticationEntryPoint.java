package com.sirkaue.jwtauthapi.security.authetication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sirkaue.jwtauthapi.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        ErrorResponse error = new ErrorResponse(request, HttpStatus.UNAUTHORIZED, new Exception("Expired or invalid JWT token."));
        String body = objectMapper.writeValueAsString(error);

        response.getWriter().write(body);
    }
}
